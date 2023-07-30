package com.example.test_task_android;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AnimalFragment extends Fragment {
    private List<Animal> animalList = new ArrayList<>();
    private AnimalAdapter animalAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.animal_fragment, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        animalAdapter = new AnimalAdapter(animalList);
        recyclerView.setAdapter(animalAdapter);

        fetchAnimals();

        return rootView;
    }

    private void fetchAnimals() {
        Context context = getContext();
        if (context == null) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://zoo-animal-api.herokuapp.com/animals/rand/10");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            response.append(line);
                        }
                        bufferedReader.close();

                        JSONArray jsonArray = new JSONArray(response.toString());

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                animalAdapter.notifyDataSetChanged();
                                Log.d("AnimalFragment", "Data fetched successfully.");
                            }
                        });

                    } else {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Failed to fetch data from API", Toast.LENGTH_SHORT).show();
                                Log.e("AnimalFragment", "Failed to fetch data from API. Response code: " + responseCode);
                            }
                        });
                    }

                    connection.disconnect();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    Log.e("AnimalFragment", "Error fetching data from API: " + e.getMessage());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Log.e("AnimalFragment", "NullPointerException: " + e.getMessage());
                }
            }
        }).start();
    }
}
