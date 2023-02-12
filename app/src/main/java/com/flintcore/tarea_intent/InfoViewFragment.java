package com.flintcore.tarea_intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.flintcore.tarea_intent.info.UserInfo;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoViewFragment extends Fragment {

    private TextView nameTextView;
    private TextView genderTextView;

    public InfoViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.nameTextView = getView().findViewById(R.id.nameTxt);
        this.genderTextView = getView().findViewById(R.id.genderTxt);
        String name = UserInfo.getInstance().getName();
        String gender = UserInfo.getInstance().getGender();

        if (Objects.nonNull(name)) {
            this.nameTextView.setText(name);
            this.genderTextView.setText(gender);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_view, container, false);
    }
}