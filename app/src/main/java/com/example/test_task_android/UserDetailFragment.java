package com.example.test_task_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserDetailFragment extends Fragment {
    private static final String ARG_USER = "arg_user";
    private User user;

    public static UserDetailFragment newInstance(User user) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(ARG_USER);
        }

        TextView txtName = view.findViewById(R.id.txtNameDetail);
        TextView txtAge = view.findViewById(R.id.txtAgeDetail);
        TextView txtIsStudent = view.findViewById(R.id.txtIsStudentDetail);

        txtName.setText(user.getName());
        txtAge.setText(String.valueOf(user.getAge()));
        txtIsStudent.setText(user.isStudent() ? "Student" : "Not a student");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_detail_fragment, container, false);
    }
}