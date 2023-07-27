package com.example.test_task_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private List<User> userList;
    private OnUserClickListener onUserClickListener;
    private Map<Integer, Boolean> userStates = new HashMap<>();

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        for (int i = 0; i < userList.size(); i++) {
            userStates.put(i, false);
        }
    }

    public interface OnUserClickListener {
        void onUserClick(User user);
    }

    public void setOnUserClickListener(OnUserClickListener listener) {
        this.onUserClickListener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.user_item_list, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.txtName.setText(user.getName());
        holder.txtAge.setText(String.valueOf(user.getAge()));
        holder.switchIsStudent.setChecked(userStates.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtAge;
        private Switch switchIsStudent;

        public UserViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtNameItem);
            txtAge = itemView.findViewById(R.id.txtAgeItem);
            switchIsStudent = itemView.findViewById(R.id.switchIsStudentItem);

            switchIsStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        userStates.put(position, isChecked);
                        saveUserStatesToSharedPreferences(userStates);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onUserClickListener != null) {
                        onUserClickListener.onUserClick(userList.get(position));
                    }
                }
            });
        }
    }

    private void saveUserStatesToSharedPreferences(Map<Integer, Boolean> userStates) {}
}