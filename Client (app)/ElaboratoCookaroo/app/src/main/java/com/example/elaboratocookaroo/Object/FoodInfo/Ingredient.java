package com.example.elaboratocookaroo.Object.FoodInfo;

public class Ingredient {
    private String name;
    private Boolean allergeni;
    public Ingredient(String name,Boolean allergeni){
        this.name=name;
        this.allergeni=allergeni;
    }

    public String getName() {
        return name;
    }

    public Boolean getAllergeni() {
        return allergeni;
    }
}
