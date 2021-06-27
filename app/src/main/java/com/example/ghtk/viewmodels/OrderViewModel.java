package com.example.ghtk.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {
    MutableLiveData<String> mutableLiveData;
    public OrderViewModel(){
        mutableLiveData = new MutableLiveData<>();
    }
    public void setText(String s){
        mutableLiveData.setValue(s);
    }

    public MutableLiveData<String> getText(){
        return mutableLiveData;
    }
}
