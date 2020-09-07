package com.example.cutandpasteimage.fragment.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cutandpasteimage.R;
import com.example.cutandpasteimage.adapter.ImageAdapter;
import com.example.cutandpasteimage.adapter.ImageAssetAdapter;
import com.example.cutandpasteimage.callback.ImageCallback;
import com.example.cutandpasteimage.databinding.DialogChangBgBinding;
import com.example.cutandpasteimage.model.PictureFacer;
import com.example.cutandpasteimage.utils.ImageFilesUtils;

import java.util.ArrayList;

public class DialogChooseBackground extends DialogFragment {
    DialogChangBgBinding binding;
    ImageAssetAdapter imageAdapter = new ImageAssetAdapter();

    public void setListener(dialogCallback listener) {
        this.listener = listener;
    }

    dialogCallback listener = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("sontit","on createview dialog");
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_chang_bg,container,false);
        return binding.getRoot();
    }

    @Override
    public int getTheme() {
        return R.style.FullScreenDialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUprecyclerView();
        imageAdapter.setList(getPhotoFromAsset(getContext()));
        imageAdapter.setCallback(new ImageCallback() {
            @Override
            public void onClickImage(PictureFacer pictureFacer) {
                Toast.makeText(getActivity(), "xxx: " + pictureFacer.getPicturePath(), Toast.LENGTH_SHORT).show();
               if(listener!=null){
                   listener.onCLickImage(pictureFacer.getPicturePath());
               }
            }
        });
    }
    private void setUprecyclerView() {
        binding.rvPhoto.setHasFixedSize(true);
        binding.rvPhoto.setLayoutManager(new GridLayoutManager(getContext(),3));
        binding.rvPhoto.setAdapter(imageAdapter);
    }
    public ArrayList<PictureFacer> getPhotoFromAsset(Context context){
        ArrayList<PictureFacer> list = new ArrayList<>();
        for (String i: ImageFilesUtils.getAssetsImagesFromFoder(context,"bg")) {
            PictureFacer pictureFacer = new PictureFacer();
            pictureFacer.setPicturePath(i);
            list.add(pictureFacer);
        }
        return list;
    }
    public interface dialogCallback {
        void onCLickImage(String pathBG);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("sontit","on destroy dialog");
    }
}
