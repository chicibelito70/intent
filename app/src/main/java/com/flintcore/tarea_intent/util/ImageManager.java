package com.flintcore.tarea_intent.util;

import android.widget.ImageView;

import com.flintcore.tarea_intent.R;
import com.flintcore.tarea_intent.info.UserInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ImageManager {

    private static ImageManager manager;

    private List<Integer> images;
    private int lastImageSelected;

    private ImageManager() {
        this.lastImageSelected = -1;
        this.images = new ArrayList<>();
        this.images.add(R.drawable.superacion_men_1);
        this.images.add(R.drawable.superacion_men_2);
        this.images.add(R.drawable.superacion_men_3);
        this.images.add(R.drawable.superacion_men_4);
        this.images.add(R.drawable.superacion_men_5);
        this.images.add(R.drawable.superacion_women_1);
        this.images.add(R.drawable.superacion_women_2);
        this.images.add(R.drawable.superacion_women_3);
        this.images.add(R.drawable.superacion_women_4);
        this.images.add(R.drawable.superacion_women_5);
    }

    public static ImageManager getManager() {
        if (Objects.isNull(manager)) {
            manager = new ImageManager();
        }

        return manager;
    }

    public void getImage(ImageView viewer) {
        getImageOfDay();

        viewer.setImageResource(lastImageSelected);
    }

    private void getImageOfDay() {
        if (this.lastImageSelected != -1) {
            return;
        }

        int imageSelected = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) % 5 +
                (UserInfo.getInstance().getGender().startsWith("M") ? 0 : 5);

        lastImageSelected = this.images.get(imageSelected);
    }

    public void getRandomImage(ImageView viewer) {
        this.getImageOfDay();
        boolean maleBoolean = UserInfo.getInstance().getGender().startsWith("M");
        int startRange = (maleBoolean ? 5 : 10);
        int endRange = (maleBoolean ? 0 : 5);
        int imageSelected = lastImageSelected;

        while (this.lastImageSelected == imageSelected) {
            imageSelected = (new Random().nextInt(startRange) + endRange) % 10;
        }

        viewer.setImageResource(imageSelected);
    }

}
