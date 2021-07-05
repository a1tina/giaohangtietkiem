package com.example.ghtk.fragment;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.ghtk.QC1Activity;
import com.example.ghtk.R;


public class ThirdFragment_1 extends Fragment {
    private AppCompatButton acbNoti;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third_1, container, false);
        acbNoti = view.findViewById(R.id.acb_noti);

        acbNoti.setOnClickListener(v -> {
            NotificationManager manager = (NotificationManager) getActivity().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

            //Action for button YES in notification
            Intent intent1 = new Intent(getActivity(), QC1Activity.class);
            intent1.putExtra("yes", true);
            intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent1 = PendingIntent.getActivity(getContext(), 0, intent1, PendingIntent.FLAG_ONE_SHOT);

            //Action for button NO in notification
            Intent intent2 = new Intent(getActivity(), QC1Activity.class);
            intent2.putExtra("no", false);
            intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(getContext(), 1, intent2, PendingIntent.FLAG_ONE_SHOT);

            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    getContext(), getString(R.string.app_name)
            );
            builder.setContentTitle("Request");
            builder.setContentText("Are you sure you want to accept request?");
            builder.setSmallIcon(R.drawable.ic_noti);
            builder.setSound(uri);
            builder.setAutoCancel(true);
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
            builder.addAction(R.drawable.ic_launcher_foreground, "Yes", pendingIntent1);
            builder.addAction(R.drawable.ic_launcher_foreground, "No", pendingIntent2);
            manager.notify(1, builder.build());
        });


        return view;
    }
}