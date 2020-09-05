package com.example.cutandpasteimage.fragment.library;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;

import com.example.cutandpasteimage.adapter.ImageAdapter;
import com.example.cutandpasteimage.base.BaseViewmodel;
import com.example.cutandpasteimage.model.PictureFacer;
import com.example.cutandpasteimage.utils.ImageFilesUtils;

import java.util.ArrayList;

public class LibraryViewModel extends BaseViewmodel {
    MutableLiveData<ArrayList<PictureFacer>> arrPicture = new MutableLiveData<>();
    ImageAdapter imageAdapter = new ImageAdapter();
    public MutableLiveData<ArrayList<PictureFacer>> getArrPicture() {
        return arrPicture;
    }

    public void setArrPicture(MutableLiveData<ArrayList<PictureFacer>> arrPicture) {
        this.arrPicture = arrPicture;
    }
    public void getImage(Activity activity){
        ArrayList<PictureFacer> list = new ArrayList<>();
        for (String i: ImageFilesUtils.getAllShownImagesPath(activity)) {
             PictureFacer pictureFacer = new PictureFacer();
             pictureFacer.setPicturePath(i);
             list.add(pictureFacer);
        }
        arrPicture.postValue(list);
    }
}
