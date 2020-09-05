package com.example.cutandpasteimage.adapter;

import com.example.cutandpasteimage.BR;
import com.example.cutandpasteimage.R;
import com.example.cutandpasteimage.base.BaseAdapter;
import com.example.cutandpasteimage.base.CBAdapter;
import com.example.cutandpasteimage.callback.ImageCallback;
import com.example.cutandpasteimage.databinding.ItemImageBinding;
import com.example.cutandpasteimage.model.PictureFacer;

public class ImageAdapter extends BaseAdapter<PictureFacer,ItemImageBinding> {
    ImageCallback callback;
    @Override
    public int getLayoutId() {
        return R.layout.item_image;
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

    public void setCallback(ImageCallback callback) {
        this.callback = callback;
    }
}
