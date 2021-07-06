package com.example.ghtk.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {
    MutableLiveData<String> mutableLiveData;
    public OrderViewModel(){
        mutableLiveData = new MutableLiveData<>();
    }
    public void setData(String s){
        mutableLiveData.setValue(s);
    }

    public MutableLiveData<String> getData(){
        return mutableLiveData;
    }
}
