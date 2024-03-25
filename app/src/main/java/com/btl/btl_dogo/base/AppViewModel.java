package com.btl.btl_dogo.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppViewModel extends ViewModel {
    public MutableLiveData<Boolean> isFocusSerch = new MutableLiveData<>(false);


    public MutableLiveData<Boolean> isAddComment= new MutableLiveData<>(false);
}
