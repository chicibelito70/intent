package com.flintcore.tarea_intent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.flintcore.tarea_intent.layout.ShareImageView;
import com.flintcore.tarea_intent.util.ImageManager;

public class ShareRandomViewActivity extends ShareImageView {

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setActivityResources(savedInstanceState,
                R.layout.activity_share_view, R.id.info_fragment_space);

        setImageToImageView();
    }



    @Override
    public void setImageToImageView() {
        this.imageView = findViewById(R.id.image_shareable);
        manager = ImageManager.getManager();
        manager.getImage(this.imageView);
    }
}