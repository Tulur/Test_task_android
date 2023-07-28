package com.example.test_task_android;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_add_user) {
            UserAddFragment userAddFragment = new UserAddFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, userAddFragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}