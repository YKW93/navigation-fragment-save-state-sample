package com.android.bottomnavigationtest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.bottomnavigationtest.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DummyFragment extends Fragment {

    static DummyFragment newInstance(String param) {
        DummyFragment fragment = new DummyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", param);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dummy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.tv_dummy_title);

        textView.setText(this.getArguments() != null ? this.getArguments().getString("title") : "데이터 없음");
    }
}
