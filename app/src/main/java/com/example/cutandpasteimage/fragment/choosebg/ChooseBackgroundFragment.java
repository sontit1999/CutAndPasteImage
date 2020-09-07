package com.example.cutandpasteimage.fragment.choosebg;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cutandpasteimage.MainActivity;
import com.example.cutandpasteimage.R;
import com.example.cutandpasteimage.base.BaseFragment;
import com.example.cutandpasteimage.callback.ImageCallback;
import com.example.cutandpasteimage.databinding.FragChooseBackgroundBinding;
import com.example.cutandpasteimage.model.PictureFacer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ChooseBackgroundFragment extends BaseFragment<FragChooseBackgroundBinding,ChooseBackGroundViewModel> {
    boolean isTheme = true;
    String pathImage = null;
    boolean returnOrnot = false;
    MainActivity activity ;
    @Override
    public Class<ChooseBackGroundViewModel> getViewmodel() {
        return ChooseBackGroundViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_choose_background;
    }

    @Override
    public void setBindingViewmodel() {
        // get bundle
        Bundle bundle = getArguments();
        returnOrnot =  bundle.getBoolean("return",false);


        binding.setViewmodel(viewmodel);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void ViewCreated() {

          setUprecyclerView();
          event();
          viewmodel.getArrPhoto().observe(this, new Observer<ArrayList<PictureFacer>>() {
              @Override
              public void onChanged(ArrayList<PictureFacer> pictureFacers) {
                  if(isTheme){
                      viewmodel.imageAssetAdapter.setList(pictureFacers);
                      binding.rvPhoto.setAdapter(viewmodel.imageAssetAdapter);
                  }else{
                      viewmodel.imageAdapter.setList(pictureFacers);
                      binding.rvPhoto.setAdapter(viewmodel.imageAdapter);
                  }
              }
          });
          if(isTheme){
              viewmodel.getPhotoFromAsset(getContext());
              binding.btnAnhcuaban.setBackground(getResources().getDrawable(R.drawable.bg_anhcuaban));
              binding.btnChudeanh.setBackground(getResources().getDrawable(R.drawable.bg_chudeanh));
          }else{
              viewmodel.getPhotoFromGallery(getActivity());
              binding.btnAnhcuaban.setBackground(getResources().getDrawable(R.drawable.bg_chudeanh));
              binding.btnChudeanh.setBackground(getResources().getDrawable(R.drawable.bg_anhcuaban));
          }
          viewmodel.imageAdapter.setCallback(new ImageCallback() {
              @Override
              public void onClickImage(PictureFacer pictureFacer) {
                  Toast.makeText(getActivity(), "Click :" + pictureFacer.getPicturePath(), Toast.LENGTH_SHORT).show();
                  Bundle bundle = new Bundle();
               //   bundle.putString("path",pictureFacer.getPicturePath());

                  // change path bg
                  activity.getMainViewModel().changeBG(pictureFacer.getPicturePath());

                  bundle.putString("type","image");
                  if(returnOrnot){
                       getControler().popBackStack();
                  }else{
                      getControler().navigate(R.id.action_chooseBackgroundFragment_to_pasteFragment,bundle);
                  }

              }
          });
          viewmodel.imageAssetAdapter.setCallback(new ImageCallback() {
              @Override
              public void onClickImage(PictureFacer pictureFacer) {
                  Toast.makeText(getActivity(), "Click :" + pictureFacer.getPicturePath(), Toast.LENGTH_SHORT).show();
                  Bundle bundle = new Bundle();
               //   bundle.putString("path",pictureFacer.getPicturePath());
                  // change path bg
                  activity.getMainViewModel().changeBG(pictureFacer.getPicturePath());
                  bundle.putString("type","assest");
                  if(returnOrnot){
                      getControler().popBackStack();
                  }else{
                      getControler().navigate(R.id.action_chooseBackgroundFragment_to_pasteFragment,bundle);
                  }

              }
          });
    }

    private void event() {

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getControler().popBackStack();
            }
        });
        binding.btnAnhcuaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewmodel.getPhotoFromGallery(getActivity());
                isTheme = false;
                binding.btnAnhcuaban.setBackground(getResources().getDrawable(R.drawable.bg_chudeanh));
                binding.btnChudeanh.setBackground(getResources().getDrawable(R.drawable.bg_anhcuaban));
            }
        });
       binding.btnChudeanh.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               viewmodel.getPhotoFromAsset(getContext());
               isTheme = true;
               binding.btnAnhcuaban.setBackground(getResources().getDrawable(R.drawable.bg_anhcuaban));
               binding.btnChudeanh.setBackground(getResources().getDrawable(R.drawable.bg_chudeanh));
           }
       });
    }

    private void setUprecyclerView() {
        binding.rvPhoto.setHasFixedSize(true);
        binding.rvPhoto.setLayoutManager(new GridLayoutManager(getContext(),3));
        binding.rvPhoto.setAdapter(viewmodel.imageAdapter);
    }

}
