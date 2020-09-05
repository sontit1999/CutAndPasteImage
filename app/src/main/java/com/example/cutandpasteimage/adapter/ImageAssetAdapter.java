package com.example.cutandpasteimage.adapter;

import androidx.databinding.library.baseAdapters.BR;

import com.example.cutandpasteimage.R;
import com.example.cutandpasteimage.base.BaseAdapter;
import com.example.cutandpasteimage.base.CBAdapter;
import com.example.cutandpasteimage.callback.ImageCallback;
import com.example.cutandpasteimage.databinding.ItemAssestimageBinding;
import com.example.cutandpasteimage.model.PictureFacer;

public class ImageAssetAdapter extends BaseAdapter<PictureFacer, ItemAssestimageBinding> {
    public void setCallback(ImageCallback callback) {
        this.callback = callback;
    }

    ImageCallback callback;
    @Override
    public int getLayoutId() {
        return R.layout.item_assestimage;
    }

    @Override
    public int getIdVariable() {
        return BR.image;
    }

    @Override
    public int getIdVariableOnclick() {
        return BR.callback;
    }

    @Override
    public CBAdapter getOnclick() {
        return callback;
    }
}
