package com.example.ghtk.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.ghtk.R;
import com.example.ghtk.api.ApiClient;
import com.example.ghtk.models.Warehouse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapFragment extends Fragment {
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    ImageButton ibMyLocation;
    Location[] locations;
    Button bWarehouse;
    GoogleMap map;
    double currLat = 0, currLong = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ibMyLocation = view.findViewById(R.id.ib_mylocation);
        bWarehouse = view.findViewById(R.id.b_find_warehouse);

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        //get current location
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


        //Tìm các kho hàng cạnh đây
        //Do không có credit card để thanh toán API key tìm nearby places nên app sẽ crash khi tìm kho.
        //Sẽ hoạt động nếu mua API từ Google
        bWarehouse.setOnClickListener(v -> {
            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                    "?location=" + currLat + "," + currLong + //location lat & lng
                    "&radius=5000" + //nearby radius
                    "&types=" + "warehouse" +
                    "&sensor=true" +
                    "&key=" + getResources().getString(R.string.google_map_key);

            new PlaceTask().execute(url);
        });



        ibMyLocation.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            }
        });

        //get location onclick
        supportMapFragment.getMapAsync(googleMap -> googleMap.setOnMapClickListener(latLng -> {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(latLng.latitude + " : " + latLng.longitude);
            googleMap.clear();
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
            googleMap.addMarker(markerOptions);
        }));

        return view;
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    //Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng)
                                    .title("My location");
                            googleMap.clear();
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });
    }

    private Location[] getWarehouseLocation() {
        Call<List<Warehouse>> call = ApiClient.getInstance().getApi().getWarehouse();
        call.enqueue(new Callback<List<Warehouse>>() {
            @Override
            public void onResponse(Call<List<Warehouse>> call, Response<List<Warehouse>> response) {
                int i = response.body().size();
                int temp = 0;
                locations = new Location[i];
                for (Warehouse warehouse : response.body()) {
                    locations[temp].setLongitude(Double.parseDouble(warehouse.getKinhdo()));
                    locations[temp].setLatitude(Double.parseDouble(warehouse.getVido()));
                    temp++;
                }
            }

            @Override
            public void onFailure(Call<List<Warehouse>> call, Throwable t) {

            }
        });
        return locations;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

    private class PlaceTask extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            new ParserTask().execute(s);
        }
    }

    private String downloadUrl(String string) throws IOException {
        URL url = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder builder = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null){
            builder.append(line);
        }
        String data = builder.toString();
        reader.close();
        return data;
    }

    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>>{
        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            JsonParser2 jsonParser2 = new JsonParser2();
            List<HashMap<String, String>> mapList = null;
            JSONObject object = null;
            try {
                object = new JSONObject(strings[0]);
                mapList = jsonParser2.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return mapList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            map.clear();
            for (int i = 0; i < hashMaps.size(); i++){
                HashMap<String, String> hashMapList = hashMaps.get(i);
                double lat = Double.parseDouble(hashMapList.get("lat"));
                double lng = Double.parseDouble(hashMapList.get("lng"));
                String name = hashMapList.get("name");
                LatLng latLng = new LatLng(lat, lng);
                MarkerOptions options = new MarkerOptions();
                options.position(latLng);
                options.title(name);
                map.addMarker(options);
            }
        }
    }
}