package com.example.cutandpasteimage.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cutandpasteimage.R;
import com.example.cutandpasteimage.base.BaseFragment;
import com.example.cutandpasteimage.databinding.FragHomeBinding;

public class HomeFragment extends BaseFragment<FragHomeBinding,HomeViewModel> {
    boolean isFirsrt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirsrt = true;
    }

    @Override
    public Class<HomeViewModel> getViewmodel() {
        return HomeViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_home;
    }

    @Override
    public void setBindingViewmodel() {
       binding.setViewmodel(viewmodel);

    }

    @Override
    public void ViewCreated() {
        if(isFirsrt){
            binding.SplashView.setVisibility(View.VISIBLE);
            isFirsrt = false;
        }
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 binding.SplashView.setVisibility(View.GONE);
             }
         },3000);
         event();
    }

    private void event() {
        binding.btnThuvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getControler().navigate(R.id.action_homeFragment_to_libraryFragment);
            }
        });
       binding.btnGhepanh.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Bundle bundle = new Bundle();
               bundle.putBoolean("return",false);
               getControler().navigate(R.id.action_homeFragment_to_chooseBackgroundFragment,bundle);
           }
       });
    }

}
