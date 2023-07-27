package com.example.test_task_android;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserAdapter.OnUserClickListener {

    private RecyclerView recyclerViewUsers;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            userList.add(new User("User " + i, 20 + i));
        }

        setContentView(R.layout.main_activity);

        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter(this, userList);
        userAdapter.setOnUserClickListener(this);
        recyclerViewUsers.setAdapter(userAdapter);
    }

    public void onUserClick(User user) {
        UserDetailFragment userDetailFragment = UserDetailFragment.newInstance(user);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, userDetailFragment)
                .addToBackStack(null)
                .commit();
    }
}