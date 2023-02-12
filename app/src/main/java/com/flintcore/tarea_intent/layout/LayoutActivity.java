package com.flintcore.tarea_intent.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.flintcore.tarea_intent.InfoViewFragment;
import com.flintcore.tarea_intent.R;

import java.net.IDN;

public abstract class LayoutActivity extends AppCompatActivity {

    protected void onCreateBundle(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    protected void setActivityResources(Bundle savedInstanceState, int id_layout, int id_inner_layout) {
        this.onCreateBundle(savedInstanceState);
        super.setContentView(id_layout);

//        Adding info fragment view
        if (id_inner_layout > 1) {
            InfoViewFragment infoFragment = new InfoViewFragment();

            this.appendDefaultView(infoFragment, R.id.info_fragment_space);
        }
    }

    protected void appendDefaultView(InfoViewFragment infoFragment, @IdRes int id_view) {
        FragmentTransaction fragmentTransaction = super.getSupportFragmentManager()
                .beginTransaction();

        fragmentTransaction.add(id_view, infoFragment, null);

        fragmentTransaction.commit();
    }

    protected Intent createIntent(Class<? extends LayoutActivity> c) {
        return new Intent(this, c);
    }
}
