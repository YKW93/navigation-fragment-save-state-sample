package com.android.bottomnavigationtest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.bottomnavigationtest.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class SettingFragment extends Fragment {
    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button button = view.findViewById(R.id.btn_add_fragment);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    int count = getChildFragmentManager().getBackStackEntryCount() + 1;
                    DummyFragment dummyFragment = DummyFragment.newInstance("Setting" + count);
                    getChildFragmentManager().beginTransaction().add(R.id.container_setting, dummyFragment).addToBackStack(null).commit();
                }
            }
        });
    }
}
