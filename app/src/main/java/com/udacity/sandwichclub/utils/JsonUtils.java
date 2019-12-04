package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_ALSO_KNOWS_AS = "alsoKnownAs";
    public static final String KEY_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try{
            JSONObject json_obj = new JSONObject(json);
            JSONObject json_names = null;
            List<String> alsoKnownAs_lst = new ArrayList<String>();
            List<String> ingredients_lst = new ArrayList<String>();

            Sandwich sandwich = new Sandwich();
            json_names = new JSONObject(json_obj.getString(KEY_NAME));
            sandwich.setMainName(json_names.getString(KEY_MAIN_NAME));
            sandwich.setPlaceOfOrigin(json_obj.getString(KEY_PLACE_OF_ORIGIN));
            sandwich.setDescription(json_obj.getString(KEY_DESCRIPTION));
            sandwich.setImage(json_obj.getString(KEY_IMAGE));

            String[] alsoKnownAs_arr = json_names.getString(KEY_ALSO_KNOWS_AS).replace("[","").replace("]","").split(",");
            String[] ingredients_arr = json_obj.getString(KEY_INGREDIENTS).replace("[","").replace("]","").split(",");

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
