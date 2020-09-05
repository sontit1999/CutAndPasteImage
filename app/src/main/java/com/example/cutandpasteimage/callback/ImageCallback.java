package com.example.cutandpasteimage.callback;

import com.example.cutandpasteimage.base.CBAdapter;
import com.example.cutandpasteimage.model.PictureFacer;

public interface ImageCallback extends CBAdapter {
    void onClickImage(PictureFacer pictureFacer);
}
