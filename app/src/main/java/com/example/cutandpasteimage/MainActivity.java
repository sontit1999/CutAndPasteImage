package com.example.cutandpasteimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cutandpasteimage.base.BaseActivity;
import com.example.cutandpasteimage.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewModel> {

    @Override
    public Class<MainViewModel> getViewmodel() {
        return MainViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
    }
}