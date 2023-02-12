package com.flintcore.tarea_intent.layout;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.flintcore.tarea_intent.R;
import com.flintcore.tarea_intent.util.ImageManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public abstract class ShareImageView extends LayoutActivity {

    protected ImageView imageView;
    protected Button btn_share;
    protected ImageManager manager;

    /**Para aplicar boton atras en vista*/
    @Override
    protected void setActivityResources(Bundle savedInstanceState, int id_layout, int id_inner_layout) {
        super.setActivityResources(savedInstanceState, id_layout, id_inner_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setImageToImageView();
        this.setShareAction();
    }

    public abstract void setImageToImageView();

    protected AlertDialog getAlertDialog() {
        return new AlertDialog.Builder(this)
                .setNeutralButton(getString(R.string.cancel_alert),
                        (dialogInterface, i) -> dialogInterface.cancel()).show();
    }

    protected void setShareAction() {
        this.btn_share = findViewById(R.id.btn_share_img);
        this.btn_share.setOnClickListener(v -> {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            Intent sendIntent = new Intent(Intent.ACTION_SEND);

            // Always use string resources for UI text.
            String title = getResources().getString(R.string.share_message);

            sendIntent.setType("image/*");

            Bitmap bitmapToSend = ((BitmapDrawable) this.imageView.getDrawable()).getBitmap();

            Uri imageUri = saveImageExternal(bitmapToSend);

            sendIntent.putExtra(Intent.EXTRA_STREAM, imageUri);

            //Create intent to show the chooser dialog
            Intent chooser = Intent.createChooser(sendIntent, title);

            // Verify the original intent will resolve to at least one activity
            startActivity(chooser);
        });
    }

    @NonNull
    private File getImageFile() {

        return new File(titleSuperationImage());
    }

    private Uri saveImageExternal(Bitmap image) {
        //TODO - Should be processed in another thread
        Uri uri = null;
        try {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "to-share.png");
            FileOutputStream stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 90, stream);
            stream.close();
            uri = Uri.fromFile(file);
        } catch (IOException e) {
        }
        return uri;
    }

    @NonNull
    private String titleSuperationImage() {
        String idRef = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        return "images/superacion/".concat("superacion n").concat(idRef).concat(".png");
    }
}
