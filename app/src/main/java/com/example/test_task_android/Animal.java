package com.example.test_task_android;

public class Animal {
    private String name;
    private String species;
    private int age;
    private String imageUrl;



    public Animal(String name, String species, int age, String imageUrl) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }
    public String getSpecies() {
        return species;
    }
    public int getAge() {
        return age;
    }
    public String getImageUrl() {
        return imageUrl;
    }
}
