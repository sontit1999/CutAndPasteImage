package com.example.cutandpasteimage;

import androidx.lifecycle.MutableLiveData;

import com.example.cutandpasteimage.base.BaseViewmodel;

public class MainViewModel extends BaseViewmodel {
    MutableLiveData<String> pathBG = new MutableLiveData<>();

    public MutableLiveData<String> getPathBG() {
        return pathBG;
    }

    public void setPathBG(MutableLiveData<String> pathBG) {
        this.pathBG = pathBG;
    }
    public void changeBG(String pathBG){
        this.pathBG.postValue(pathBG);
    }
}
