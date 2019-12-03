package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try{
            JSONObject json_obj = new JSONObject(json);
            JSONObject json_names = null;
            List<String> alsoKnownAs_lst = new ArrayList<String>();
            List<String> ingredients_lst = new ArrayList<String>();

            Sandwich sandwich = new Sandwich();
            json_names = new JSONObject(json_obj.getString("name"));
            sandwich.setMainName(json_names.getString("mainName"));
            sandwich.setPlaceOfOrigin(json_obj.getString("placeOfOrigin"));
            sandwich.setDescription(json_obj.getString("description"));
            sandwich.setImage(json_obj.getString("image"));

            String[] alsoKnownAs_arr = json_names.getString("alsoKnownAs").replace("[","").replace("]","").split(",");
            String[] ingredients_arr = json_obj.getString("ingredients").replace("[","").replace("]","").split(",");

            for (String item : alsoKnownAs_arr){
                alsoKnownAs_lst.add(item);
            }

            for (String item : ingredients_arr){
                ingredients_lst.add(item);
            }

            sandwich.setAlsoKnownAs(alsoKnownAs_lst);
            sandwich.setIngredients(ingredients_lst);

            return  sandwich;
        }catch (Throwable t ){
            return null;
        }
    }
}
