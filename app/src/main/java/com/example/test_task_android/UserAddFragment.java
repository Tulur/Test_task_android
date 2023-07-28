package com.example.test_task_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserAddFragment extends Fragment {
    private EditText editTextName;
    private EditText editTextAge;
    private DatePicker datePicker;
    private Button buttonSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_user, container, false);

        editTextName = rootView.findViewById(R.id.editTextName);
        editTextAge = rootView.findViewById(R.id.editTextAge);
        datePicker = rootView.findViewById(R.id.datePicker);
        buttonSave = rootView.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                int age = Integer.parseInt(editTextAge.getText().toString());
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                User newUser = new User(name, age);
                requireActivity().getSupportFragmentManager().popBackStack();
                showUserAddedMessage();
            }
        });

        return rootView;
    }

    private void showUserAddedMessage() {
        Toast.makeText(getContext(), "User added successfully", Toast.LENGTH_SHORT).show();
    }
}
