package com.example.cutandpasteimage.fragment.library;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cutandpasteimage.R;
import com.example.cutandpasteimage.base.BaseFragment;
import com.example.cutandpasteimage.callback.ImageCallback;
import com.example.cutandpasteimage.databinding.FragLibraryBinding;
import com.example.cutandpasteimage.model.ImageFolder;
import com.example.cutandpasteimage.model.PictureFacer;
import com.example.cutandpasteimage.utils.ImageFilesUtils;

import java.util.ArrayList;

public class LibraryFragment extends BaseFragment<FragLibraryBinding,LibraryViewModel> {
    @Override
    public Class<LibraryViewModel> getViewmodel() {
        return LibraryViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_library;
    }

    @Override
    public void setBindingViewmodel() {
       binding.setViewmodel(viewmodel);
    }

    @Override
    public void ViewCreated() {
        Log.d("son","on viewCreate");
        setupRecyclerviewImage();
        viewmodel.getArrPicture().observe(this, new Observer<ArrayList<PictureFacer>>() {
            @Override
            public void onChanged(ArrayList<PictureFacer> pictureFacers) {
                viewmodel.imageAdapter.setList(pictureFacers);
                Log.d("son","on changeData");
            }
        });
        event();
        viewmodel.imageAdapter.setCallback(new ImageCallback() {
            @Override
            public void onClickImage(PictureFacer pictureFacer) {
                Toast.makeText(getActivity(), "Click image path: " + pictureFacer.getPicturePath(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerviewImage() {
        binding.rvLibrary.setHasFixedSize(true);
        binding.rvLibrary.setLayoutManager(new GridLayoutManager(getContext(),3));
        binding.rvLibrary.setAdapter(viewmodel.imageAdapter);
    }

    private void event() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getControler().popBackStack();
            }
        });
        viewmodel.getImage(getContext());

    }

}
