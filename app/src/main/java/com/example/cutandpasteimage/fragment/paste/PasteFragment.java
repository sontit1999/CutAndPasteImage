package com.example.cutandpasteimage.fragment.paste;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.example.cutandpasteimage.MainActivity;
import com.example.cutandpasteimage.R;
import com.example.cutandpasteimage.base.BaseFragment;
import com.example.cutandpasteimage.callback.HelloIconEvent;
import com.example.cutandpasteimage.databinding.FragPasteBinding;
import com.example.cutandpasteimage.fragment.dialog.DialogChooseBackground;
import com.example.cutandpasteimage.utils.Constant;
import com.example.cutandpasteimage.utils.ImageFilesUtils;
import com.xiaopo.flying.sticker.BitmapStickerIcon;
import com.xiaopo.flying.sticker.DeleteIconEvent;
import com.xiaopo.flying.sticker.DrawableSticker;
import com.xiaopo.flying.sticker.FlipHorizontallyEvent;
import com.xiaopo.flying.sticker.Sticker;
import com.xiaopo.flying.sticker.StickerIconEvent;
import com.xiaopo.flying.sticker.StickerView;
import com.xiaopo.flying.sticker.TextSticker;
import com.xiaopo.flying.sticker.ZoomIconEvent;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;

public class PasteFragment extends BaseFragment<FragPasteBinding,PasteViewModel> {
    String pathBackGround = null;
    Bundle bundle = null;
    String typeBG = null;
    private static final String TAG = "sontit";
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
         bundle = getArguments();
         if(bundle!=null){
          //   pathBackGround = bundle.getString("path","null");
             typeBG = bundle.getString("type","xxx");
         }else{

         }

          binding.setViewmodel(viewmodel);

    }
    public Drawable loadDrawableFromAssets(Context context, String path)
    {
        InputStream stream = null;
        try
        {
            stream = context.getAssets().open(path);
            return Drawable.createFromStream(stream, null);
        }
        catch (Exception ignored) {} finally
        {
            try
            {
                if(stream != null)
                {
                    stream.close();
                }
            } catch (Exception ignored) {}
        }
        return null;
    }
    public Bitmap getBitmapFromAssets(String fileName) {
        AssetManager assetManager = getContext().getAssets();
        Bitmap bitmap = null;
        InputStream istr = null;
        try {
            istr = assetManager.open(fileName);
            bitmap = BitmapFactory.decodeStream(istr);
            istr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        return bitmap;
    }
    @Override
    public void ViewCreated() {

        // observer background
        MainActivity activity = (MainActivity) getActivity();
        activity.getMainViewModel().getPathBG().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(typeBG.equals("image")){
                    Bitmap bmImg = BitmapFactory.decodeFile(s);
                    binding.ivBackground.setImageBitmap(bmImg);
                }else{
                    Bitmap bmImg = getBitmapFromAssets(s);
                    binding.ivBackground.setImageBitmap(bmImg);
                }
            }
        });
        customIcon();
        event();

    }

    private void customIcon() {
        //default icon layout
        //the event you can custom
        BitmapStickerIcon deleteIcon = new BitmapStickerIcon(ContextCompat.getDrawable(getContext(),
                com.xiaopo.flying.sticker.R.drawable.sticker_ic_close_white_18dp),
                BitmapStickerIcon.LEFT_TOP);
        deleteIcon.setIconEvent(new DeleteIconEvent());

        BitmapStickerIcon zoomIcon = new BitmapStickerIcon(ContextCompat.getDrawable(getContext(),
                com.xiaopo.flying.sticker.R.drawable.sticker_ic_scale_white_18dp),
                BitmapStickerIcon.RIGHT_BOTOM);
        zoomIcon.setIconEvent(new ZoomIconEvent());

        BitmapStickerIcon flipIcon = new BitmapStickerIcon(ContextCompat.getDrawable(getContext(),
                com.xiaopo.flying.sticker.R.drawable.sticker_ic_flip_white_18dp),
                BitmapStickerIcon.RIGHT_TOP);
        flipIcon.setIconEvent(new FlipHorizontallyEvent());

        BitmapStickerIcon heartIcon =
                new BitmapStickerIcon(ContextCompat.getDrawable(getContext(), R.drawable.ic_eraser),
                        BitmapStickerIcon.LEFT_BOTTOM);
        heartIcon.setIconEvent(new StickerIconEvent() {
            @Override
            public void onActionDown(StickerView stickerView, MotionEvent event) {
                Toast.makeText(getActivity(), "Easer", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onActionMove(StickerView stickerView, MotionEvent event) {

            }

            @Override
            public void onActionUp(StickerView stickerView, MotionEvent event) {

            }
        });

        binding.stickerView.setIcons(Arrays.asList(deleteIcon, zoomIcon, flipIcon, heartIcon));
        binding.stickerView.setBackgroundColor(Color.WHITE);
        binding.stickerView.setLocked(false);
        binding.stickerView.setConstrained(true);
    }

    private void event() {
           binding.btnCuttuedimage.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   final DialogChooseBackground dialogCutted = new DialogChooseBackground();
                   dialogCutted.setListener(new DialogChooseBackground.dialogCallback() {
                       @Override
                       public void onCLickImage(String pathBG) {
                           //Toast.makeText(getActivity(), "Paste fragment nhận dc : " + pathBG, Toast.LENGTH_SHORT).show();
                           binding.stickerView.addSticker(new DrawableSticker(loadDrawableFromAssets(getContext(),pathBG)));
                           dialogCutted.dismiss();
                       }
                   });
                   dialogCutted.show(getChildFragmentManager(),dialogCutted.getTag());


               }
           });
           binding.btnCutnew.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

               }
           });
            binding.btnChangeBG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final DialogChooseBackground dialogChooseBackground = new DialogChooseBackground();
                    dialogChooseBackground.setListener(new DialogChooseBackground.dialogCallback() {
                        @Override
                        public void onCLickImage(String pathBG) {
                            //Toast.makeText(getActivity(), "Paste fragment nhận dc : " + pathBG, Toast.LENGTH_SHORT).show();
                            Bitmap bmImg = getBitmapFromAssets(pathBG);
                            binding.ivBackground.setImageBitmap(bmImg);
                            dialogChooseBackground.dismiss();
                        }
                    });
                    dialogChooseBackground.show(getChildFragmentManager(),dialogChooseBackground.getTag());

//                    Bundle bundle = new Bundle();
//                    bundle.putBoolean("return",true);
//                    getControler().navigate(R.id.action_pasteFragment_to_chooseBackgroundFragment,bundle);
                }
            });

            binding.stickerView.setOnStickerOperationListener(new StickerView.OnStickerOperationListener() {
            @Override
            public void onStickerAdded(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerAdded");
            }

            @Override
            public void onStickerClicked(@NonNull Sticker sticker) {
                //stickerView.removeAllSticker();
                if (sticker instanceof TextSticker) {
                    ((TextSticker) sticker).setTextColor(Color.RED);
                    binding.stickerView.replace(sticker);
                    binding.stickerView.invalidate();
                }
                Log.d(TAG, "onStickerClicked");
            }

            @Override
            public void onStickerDeleted(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerDeleted");
            }

            @Override
            public void onStickerDragFinished(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerDragFinished");
            }

            @Override
            public void onStickerTouchedDown(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerTouchedDown");
            }

            @Override
            public void onStickerZoomFinished(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerZoomFinished");
            }

            @Override
            public void onStickerFlipped(@NonNull Sticker sticker) {
                Log.d(TAG, "onStickerFlipped");
            }

            @Override
            public void onStickerDoubleTapped(@NonNull Sticker sticker) {
                Log.d(TAG, "onDoubleTapped: double tap will be with two click");
            }
        });

        binding.tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Đã lưu", Toast.LENGTH_SHORT).show();
                File files =  createFile("image");
                binding.stickerView.save(files);
            }
        });
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getControler().popBackStack();
            }
        });
        binding.tvGhepanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private File createFile(String nameFile) {
        String root = Environment.getExternalStorageDirectory().toString();
        Log.d("sontit","root :" + root);
        File myDir = new File(root + "/" + Constant.forderLibray);
        if (!myDir.exists()) {
            myDir.mkdirs();
            Log.d("sontit","tạo forder");
        }
        Random generator = new Random();
        int n = 100000;
        n = generator.nextInt(n);
        String fname = nameFile + n +".jpg";
        File file = new File (myDir, fname);
        return file;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("sontit","ondestroyview paste");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("sontit","oncreateview paste");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
