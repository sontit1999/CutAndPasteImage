package com.example.cutandpasteimage.fragment.library;

import android.view.View;

import com.example.cutandpasteimage.R;
import com.example.cutandpasteimage.base.BaseFragment;
import com.example.cutandpasteimage.databinding.FragLibraryBinding;

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
         event();
    }

    private void event() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getControler().popBackStack();
            }
        });
    }
}
