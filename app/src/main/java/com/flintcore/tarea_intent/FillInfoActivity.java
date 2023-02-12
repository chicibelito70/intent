package com.flintcore.tarea_intent;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.flintcore.tarea_intent.info.UserInfo;
import com.flintcore.tarea_intent.layout.LayoutActivity;

import java.util.Objects;
import java.util.Random;

public class FillInfoActivity extends LayoutActivity {

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.setActivityResources(savedInstanceState, R.layout.activity_fill_info, -1);

        EditText nameView = findViewById(R.id.name_edit);
        RadioGroup genderPicker = findViewById(R.id.genderPicker);
        UserInfo userInfo = UserInfo.getInstance();

        if (!UserInfo.isNew()) {
            nameView.setText(userInfo.getName());
            int index = userInfo.getGender().startsWith("M") ? 0 : 1;
            int idSelected = genderPicker.getChildAt(index).getId();
            genderPicker.check(idSelected);
        }

        findViewById(R.id.btn_save).setOnClickListener(v -> {
            String name = nameView.getText().toString();
            userInfo.setName(name);

            RadioButton radio_button_selected = findViewById(genderPicker.getCheckedRadioButtonId());
            if (Objects.isNull(radio_button_selected)) {
                radio_button_selected = ((RadioButton) genderPicker.getChildAt(0));
            }

            String genderSelected = radio_button_selected.getText().toString();
            userInfo.setGender(genderSelected);

            setResult(RESULT_OK);
            finish();
        });

    }
}