package com.flintcore.tarea_intent;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleRegistry;

import com.flintcore.tarea_intent.info.UserInfo;
import com.flintcore.tarea_intent.layout.LayoutActivity;

public class MainActivity extends LayoutActivity {

    private Button btn_info;
    private Button btn_message;
    private Button btn_message_random;
    private ActivityResultCallback<ActivityResult> activityResultActivityResultCallback;
    private ActivityResultContracts.StartActivityForResult contract;
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setActivityResources(savedInstanceState, R.layout.activity_main, R.id.info_fragment_space);

        setIntentForSetInfo();

        this.btn_info = findViewById(R.id.btn_fill_info);
        this.btn_info.setOnClickListener(v -> {
            intentActivityResultLauncher.launch(createIntent(FillInfoActivity.class));
        });

        this.btn_message = findViewById(R.id.btn_message_view);

        this.btn_message.setOnClickListener(v -> {
            if (notUserRegistered()) {
                return;
            }

            startActivity(createIntent(ShareViewActivity.class));

        });

        this.btn_message_random = findViewById(R.id.btn_message_random_view);
        this.btn_message_random.setOnClickListener(v -> {
            if (notUserRegistered()) {
                return;
            }

            startActivity(createIntent(ShareRandomViewActivity.class));
        });

    }

    private boolean notUserRegistered() {
        if (UserInfo.isNew()) {

            TextView view = new TextView(this);
            view.setText(R.string.info_emtpy_user);
            view.setTextSize(35f);

            new AlertDialog.Builder(this)
                    .setView(view)
                    .setNeutralButton(getString(R.string.cancel_alert), (dialogInterface, i) -> {
                        dialogInterface.cancel();
                    }).show();

            return true;
        }
        return false;
    }

    private void setIntentForSetInfo() {
        //        TODO Look this to start Activity Result
        contract = new ActivityResultContracts.StartActivityForResult();

        activityResultActivityResultCallback = result -> {
            if (result.getResultCode() == RESULT_OK) {
                ((TextView) findViewById(R.id.nameTxt))
                        .setText(UserInfo.getInstance().getName());

                ((TextView) findViewById(R.id.genderTxt))
                        .setText(UserInfo.getInstance().getGender());
            }
        };

        intentActivityResultLauncher = registerForActivityResult(
                contract,
                activityResultActivityResultCallback);

        //        TODO END Look this to start Activity Result
    }
}