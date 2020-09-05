package com.example.cutandpasteimage.fragment.paste;

import android.os.Bundle;
import android.widget.Toast;

import com.example.cutandpasteimage.R;
import com.example.cutandpasteimage.base.BaseFragment;
import com.example.cutandpasteimage.databinding.FragPasteBinding;

public class PasteFragment extends BaseFragment<FragPasteBinding,PasteViewModel> {
    String pathBackGround = null;
    @Override
    public Class<PasteViewModel> getViewmodel() {
        return PasteViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_paste;
    }

    @Override
    public void setBindingViewmodel() {
          // get bundle
         Bundle bundle = getArguments();
         if(bundle!=null){
             pathBackGround = bundle.getString("path","null");
             Toast.makeText(getActivity(), "PathImage nhận dc : "  + pathBackGround, Toast.LENGTH_SHORT).show();
         }else{
             Toast.makeText(getActivity(), "Khoogn nhận dc path Image", Toast.LENGTH_SHORT).show();
         }
          binding.setViewmodel(viewmodel);
    }

    @Override
    public void ViewCreated() {
           event();
    }

    private void event() {
    }
}
