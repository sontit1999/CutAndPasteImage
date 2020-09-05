package com.example.cutandpasteimage.fragment.choosebg;

import android.app.Activity;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.cutandpasteimage.adapter.ImageAdapter;
import com.example.cutandpasteimage.adapter.ImageAssetAdapter;
import com.example.cutandpasteimage.base.BaseViewmodel;
import com.example.cutandpasteimage.model.PictureFacer;
import com.example.cutandpasteimage.utils.ImageFilesUtils;

import java.util.ArrayList;

public class ChooseBackGroundViewModel extends BaseViewmodel {
    MutableLiveData<ArrayList<PictureFacer>> arrPhoto = new MutableLiveData<>();
    ImageAdapter imageAdapter = new ImageAdapter();
    ImageAssetAdapter imageAssetAdapter = new ImageAssetAdapter();
    public MutableLiveData<ArrayList<PictureFacer>> getArrPhoto() {
        return arrPhoto;
    }

    public void setArrPhoto(MutableLiveData<ArrayList<PictureFacer>> arrPhoto) {
        this.arrPhoto = arrPhoto;
    }
    public void getPhotoFromAsset(Context context){
        ArrayList<PictureFacer> list = new ArrayList<>();
        for (String i: ImageFilesUtils.getAssetsImagesFromFoder(context,"bg")) {
            PictureFacer pictureFacer = new PictureFacer();
            pictureFacer.setPicturePath(i);
            list.add(pictureFacer);
        }
        arrPhoto.postValue(list);
    }
    public void getPhotoFromGallery(Activity activity){
        ArrayList<PictureFacer> list = new ArrayList<>();
        for (String i: ImageFilesUtils.getAllShownImagesPath(activity)) {
            PictureFacer pictureFacer = new PictureFacer();
            pictureFacer.setPicturePath(i);
            list.add(pictureFacer);
        }
        arrPhoto.postValue(list);
    }
}
