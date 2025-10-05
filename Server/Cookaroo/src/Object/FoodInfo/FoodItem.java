package Object.FoodInfo;

import java.util.ArrayList;

public class FoodItem {
	private String name,image,codicep;
    private float prezzo,avg_valutazione;
    private ArrayList<Ingredient> ingredientArrayList;
    public FoodItem(String name,float prezzo,String image,float avg_valutazione,String codicep,ArrayList<Ingredient> ingredientArrayList){
        this.name=name;
        this.prezzo=prezzo;
        this.image=image;
        this.avg_valutazione=avg_valutazione;
        this.codicep=codicep;
        this.ingredientArrayList=ingredientArrayList;
    }
}
