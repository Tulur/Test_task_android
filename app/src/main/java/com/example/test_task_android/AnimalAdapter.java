package com.example.test_task_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private List<Animal> animalList;

    public AnimalAdapter(List<Animal> animalList) {
        this.animalList = animalList;
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_item, parent, false);
        return new AnimalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animalList.get(position);
        holder.txtName.setText(animal.getName());
        holder.txtSpecies.setText(animal.getSpecies());
        holder.txtAge.setText(String.valueOf(animal.getAge()));
        Glide.with(holder.itemView.getContext())
                .load(animal.getImageUrl())
                .into(holder.imgAnimal);
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public class AnimalViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtSpecies;
        private TextView txtAge;
        private ImageView imgAnimal;

        public AnimalViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtSpecies = itemView.findViewById(R.id.txtSpecies);
            txtAge = itemView.findViewById(R.id.txtAge);
            imgAnimal = itemView.findViewById(R.id.imgAnimal);
        }
    }
}
