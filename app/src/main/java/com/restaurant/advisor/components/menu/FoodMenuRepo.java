package com.restaurant.advisor.components.menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FoodMenuRepo {

    static final String MENUS = "{ \"menus\": [ { \"ID\": \"I1\", \"FoodType\": \"Indian\", \"MealType\": \"Main\", \"Name\": \"Chicken 65 \", \"Price\": \"9.99\" }, { \"ID\": \"I2\", \"FoodType\": \"Indian\", \"MealType\": \"Main\", \"Name\": \"Paneer tikka (veg.)\", \"Price\": \"9.99\" }, { \"ID\": \"I3\", \"FoodType\": \"Indian\", \"MealType\": \"Appetizers\", \"Name\": \"Gobi manchurian \", \"Price\": \"4.99\" }, { \"ID\": \"I4\", \"FoodType\": \"Indian\", \"MealType\": \"Appetizers\", \"Name\": \"Samosa\", \"Price\": \"4.99\" }, { \"ID\": \"I5\", \"FoodType\": \"Indian\", \"MealType\": \"Beverages\", \"Name\": \"Chai \", \"Price\": \"4.99\" }, { \"ID\": \"I6\", \"FoodType\": \"Indian\", \"MealType\": \"Beverages\", \"Name\": \"Lassi\", \"Price\": \"7.99\" }, { \"ID\": \"I7\", \"FoodType\": \"Indian\", \"MealType\": \"Appetizers\", \"Name\": \"Idli\", \"Price\": \"7.99\" }, { \"ID\": \"I8\", \"FoodType\": \"Indian\", \"MealType\": \"Appetizers\", \"Name\": \"Medu wada\", \"Price\": \"7.99\" }, { \"ID\": \"I9\", \"FoodType\": \"Thai\", \"MealType\": \"Appetizers\", \"Name\": \"Mixed vegetables tofu (veg.)\", \"Price\": \"9\" }, { \"ID\": \"I10\", \"FoodType\": \"Thai\", \"MealType\": \"Appetizers\", \"Name\": \"Chicke Tikka Masala\", \"Price\": \"9\" }, { \"ID\": \"T1\", \"FoodType\": \"Thai\", \"MealType\": \"Appetizers\", \"Name\": \"Pad thai* (gf)\", \"Price\": \"9\" }, { \"ID\": \"T2\", \"FoodType\": \"Thai\", \"MealType\": \"Appetizers\", \"Name\": \"Spring Roll\", \"Price\": \"9\" }, { \"ID\": \"T3\", \"FoodType\": \"Thai\", \"MealType\": \"Appetizers\", \"Name\": \"Param* (gf)\", \"Price\": \"9\" }, { \"ID\": \"T4\", \"FoodType\": \"Thai\", \"MealType\": \"Main\", \"Name\": \"Thai Fried Rice\", \"Price\": \"9\" }, { \"ID\": \"T5\", \"FoodType\": \"Thai\", \"MealType\": \"Main\", \"Name\": \"Spicy Thai Basil Fried Rice(veg.)\", \"Price\": \"9\" }, { \"ID\": \"T6\", \"FoodType\": \"Thai\", \"MealType\": \"Main\", \"Name\": \"Cashew nut chicken*\", \"Price\": \"12\" }, { \"ID\": \"T7\", \"FoodType\": \"Thai\", \"MealType\": \"Main\", \"Name\": \"Sweet & sour pork\", \"Price\": \"12\" }, { \"ID\": \"T8\", \"FoodType\": \"Thai\", \"MealType\": \"Main\", \"Name\": \"Red curry chicken*\", \"Price\": \"12\" }, { \"ID\": \"T9\", \"FoodType\": \"Thai\", \"MealType\": \"Beverages\", \"Name\": \"Thai Tea\", \"Price\": \"4.99\" }, { \"ID\": \"T10\", \"FoodType\": \"Thai\", \"MealType\": \"Beverages\", \"Name\": \"Hot Tea/Coffee\", \"Price\": \"4.99\" }, { \"ID\": \"C1\", \"FoodType\": \"Carribean\", \"MealType\": \"Appetizers\", \"Name\": \"Coconut fried shrimp\", \"Price\": \"14\" }, { \"ID\": \"C2\", \"FoodType\": \"Carribean\", \"MealType\": \"Appetizers\", \"Name\": \"Jerk chicken wings\", \"Price\": \"12\" }, { \"ID\": \"C3\", \"FoodType\": \"Carribean\", \"MealType\": \"Main\", \"Name\": \"Mni beef and chicken patties\", \"Price\": \"11.5\" }, { \"ID\": \"C4\", \"FoodType\": \"Carribean\", \"MealType\": \"Main\", \"Name\": \"Jerk pork bites (gf)\", \"Price\": \"11\" }, { \"ID\": \"C5\", \"FoodType\": \"Carribean\", \"MealType\": \"Main\", \"Name\": \"Curried crab flatbread\", \"Price\": \"15\" }, { \"ID\": \"C6\", \"FoodType\": \"Carribean\", \"MealType\": \"Main\", \"Name\": \"Jerk Chicken - Dark Meat (GF)\", \"Price\": \"15\" }, { \"ID\": \"C7\", \"FoodType\": \"Carribean\", \"MealType\": \"Main\", \"Name\": \"Jerk Chicken - White Meat (GF)\", \"Price\": \"16\" }, { \"ID\": \"C8\", \"FoodType\": \"Carribean\", \"MealType\": \"Main\", \"Name\": \"BBQ 5 Piece Pork Spare Ribs\", \"Price\": \"17\" }, { \"ID\": \"C9\", \"FoodType\": \"Carribean\", \"MealType\": \"Beverages\", \"Name\": \"Cold Drinks\", \"Price\": \"19\" }, { \"ID\": \"C10\", \"FoodType\": \"Carribean\", \"MealType\": \"Beverages\", \"Name\": \"Coffee\", \"Price\": \"15\" }, { \"ID\": \"C11\", \"FoodType\": \"Carribean\", \"MealType\": \"Dessert\", \"Name\": \"Warm sweet potato pudding\", \"Price\": \"8\" }, { \"ID\": \"C12\", \"FoodType\": \"Carribean\", \"MealType\": \"Dessert\", \"Name\": \"Key lime pie\", \"Price\": \"8\" }, { \"ID\": \"M1\", \"FoodType\": \"Mexican\", \"MealType\": \"Appetizers\", \"Name\": \"Quesadilla Chicken or Beef \", \"Price\": \"9.95\" }, { \"ID\": \"M2\", \"FoodType\": \"Mexican\", \"MealType\": \"Appetizers\", \"Name\": \"El Patio Street Tacos Platter\", \"Price\": \"12.95\" }, { \"ID\": \"M3\", \"FoodType\": \"Mexican\", \"MealType\": \"Appetizers\", \"Name\": \"Momias\", \"Price\": \"12.95\" }, { \"ID\": \"M4\", \"FoodType\": \"Mexican\", \"MealType\": \"Appetizers\", \"Name\": \"Buffalo Chicken Wings \", \"Price\": \"10.95\" }, { \"ID\": \"M5\", \"FoodType\": \"Mexican\", \"MealType\": \"Main\", \"Name\": \"Chicharrones Botana\", \"Price\": \"11.95\" }, { \"ID\": \"M6\", \"FoodType\": \"Mexican\", \"MealType\": \"Main\", \"Name\": \"Chicharrones con Salsa\", \"Price\": \"16.95\" }, { \"ID\": \"M7\", \"FoodType\": \"Mexican\", \"MealType\": \"Main\", \"Name\": \"Steak Ranchero\", \"Price\": \"16.95\" }, { \"ID\": \"M8\", \"FoodType\": \"Mexican\", \"MealType\": \"Main\", \"Name\": \"Carne Asada\", \"Price\": \"18.95\" }, { \"ID\": \"M9\", \"FoodType\": \"Mexican\", \"MealType\": \"Main\", \"Name\": \"Tacos Al Pastor Platter\", \"Price\": \"16.95\" }, { \"ID\": \"M10\", \"FoodType\": \"Mexican\", \"MealType\": \"Main\", \"Name\": \"Pollo Asado\", \"Price\": \"17.95\" }, { \"ID\": \"M11\", \"FoodType\": \"Mexican\", \"MealType\": \"Beverages\", \"Name\": \"Paloma\", \"Price\": \"6\" }, { \"ID\": \"M12\", \"FoodType\": \"Mexican\", \"MealType\": \"Beverages\", \"Name\": \"Michelada\", \"Price\": \"6\" }, { \"ID\": \"M13\", \"FoodType\": \"Mexican\", \"MealType\": \"Dessert\", \"Name\": \"Pineapple upside down cake\", \"Price\": \"8\" }, { \"ID\": \"M14\", \"FoodType\": \"Mexican\", \"MealType\": \"Dessert\", \"Name\": \"Ice cream selection\", \"Price\": \"8\" }, { \"ID\": \"K1\", \"FoodType\": \"Korean\", \"MealType\": \"Appetizers\", \"Name\": \"Seolleongtang (ox bone soup)\", \"Price\": \"11\" }, { \"ID\": \"K2\", \"FoodType\": \"Korean\", \"MealType\": \"Appetizers\", \"Name\": \"Samgyetang (ginseng chicken soup)\", \"Price\": \"11\" }, { \"ID\": \"K3\", \"FoodType\": \"Korean\", \"MealType\": \"Appetizers\", \"Name\": \"Hoeddeok (sweet syrupy pancakes)\", \"Price\": \"12\" }, { \"ID\": \"K4\", \"FoodType\": \"Korean\", \"MealType\": \"Appetizers\", \"Name\": \"Daegujeon (Breaded cod fillets)\", \"Price\": \"12\" }, { \"ID\": \"K5\", \"FoodType\": \"Korean\", \"MealType\": \"Main\", \"Name\": \"Japchae (stir-fried noodles)\", \"Price\": \"20\" }, { \"ID\": \"K6\", \"FoodType\": \"Korean\", \"MealType\": \"Main\", \"Name\": \"Sundubu-jjigae\", \"Price\": \"20\" }, { \"ID\": \"K7\", \"FoodType\": \"Korean\", \"MealType\": \"Main\", \"Name\": \"Bibimbap (mixed rice)\", \"Price\": \"18\" }, { \"ID\": \"K8\", \"FoodType\": \"Korean\", \"MealType\": \"Main\", \"Name\": \"Bulgogi (marinated beef barbecue)\", \"Price\": \"18\" }, { \"ID\": \"K9\", \"FoodType\": \"Korean\", \"MealType\": \"Main\", \"Name\": \"Samgyeopsal\", \"Price\": \"18\" }, { \"ID\": \"K10\", \"FoodType\": \"Korean\", \"MealType\": \"Main\", \"Name\": \"Pork Bulgogi (Daeji Bulgogi\", \"Price\": \"16\" }, { \"ID\": \"K11\", \"FoodType\": \"Korean\", \"MealType\": \"Beverages\", \"Name\": \"Bokbunja ju \", \"Price\": \"8\" }, { \"ID\": \"K12\", \"FoodType\": \"Korean\", \"MealType\": \"Beverages\", \"Name\": \"Omija Tea\", \"Price\": \"8\" }, { \"ID\": \"K13\", \"FoodType\": \"Korean\", \"MealType\": \"Dessert\", \"Name\": \"Jeonggwa\", \"Price\": \"10\" }, { \"ID\": \"K14\", \"FoodType\": \"Korean\", \"MealType\": \"Dessert\", \"Name\": \"Dasik\", \"Price\": \"10\" }, { \"ID\": \"V1\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Appetizers\", \"Name\": \"Bún chả cá\", \"Price\": \"11\" }, { \"ID\": \"V2\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Appetizers\", \"Price\": \"11\" }, { \"ID\": \"V3\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Appetizers\", \"Name\": \"Bánh hỏi\", \"Price\": \"12\" }, { \"ID\": \"V4\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Appetizers\", \"Name\": \"Bánh canh\", \"Price\": \"12\" }, { \"ID\": \"V5\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Main\", \"Name\": \"Cao lầu\", \"Price\": \"20\" }, { \"ID\": \"V6\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Main\", \"Name\": \"Bún riêu\", \"Price\": \"20\" }, { \"ID\": \"V7\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Main\", \"Name\": \"Phở\", \"Price\": \"18\" }, { \"ID\": \"V8\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Main\", \"Name\": \"Mì Quảng\", \"Price\": \"18\" }, { \"ID\": \"V9\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Main\", \"Name\": \"Rice vermicelli\", \"Price\": \"18\" }, { \"ID\": \"V10\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Main\", \"Name\": \"Hủ tiếu\", \"Price\": \"16\" }, { \"ID\": \"V11\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Beverages\", \"Name\": \"Nuoc mia(Sugar Cane Juice)\", \"Price\": \"8\" }, { \"ID\": \"V12\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Beverages\", \"Name\": \"Nuoc dua(Fresh coconut water)\", \"Price\": \"8\" }, { \"ID\": \"V13\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Dessert\", \"Name\": \"Chè Bắp(Sweet corn pudding)\", \"Price\": \"10\" }, { \"ID\": \"V14\", \"FoodType\": \"Vietnamise\", \"MealType\": \"Dessert\", \"Name\": \"Bánh Xoai(Mango sweet cakes)\", \"Price\": \"10\" } ] }";

    public List<MenuDataModel> getFoodMenu() {
        List<MenuDataModel> menuDataModels = new ArrayList<>();
        try {
            JSONObject mainObject = new JSONObject(MENUS);
            JSONObject menu = mainObject.optJSONObject("menu");
            Iterator<String> foodTypes = menu.keys();
            while (foodTypes.hasNext()) {
                String foodType = foodTypes.next();
                JSONArray itemsPerSection = menu.getJSONArray(foodType);
                for (int i = 0; i < itemsPerSection.length(); i++) {
                    menuDataModels.add(new MenuDataModel(itemsPerSection.optJSONObject(i), foodType, i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return menuDataModels;
    }

    public List<MenuDataModel> getFoodMenus(String foodTypes) {
          List<MenuDataModel> menuDataModels = new ArrayList<>();
        Map<String, List<JSONObject>> mData = new HashMap<>();
        try {
            JSONObject mainObject = new JSONObject(MENUS);
            JSONArray menu = mainObject.optJSONArray("menus");
            // Iterator<String> foodTypes = menu.keys();
            int i = 0;
            while (i < menu.length()) {
                JSONObject foodType = menu.optJSONObject(i);
                if (foodType.getString("FoodType").equals(foodTypes)) {
                    // menuDataModels.add(new MenuDataModel(foodType, foodType.getString("MealType"), i));
                    String key = foodType.getString("MealType");
                    List<JSONObject> data = mData.get(key);
                    if (data == null) {
                        data = new ArrayList<>();
                    }
                    data.add(foodType);
                    mData.put(key, data);
                }
                i++;
            }

            if(mData.size() == 0){
                i = 0;
                List<JSONObject> data1 = null;
                while (i < menu.length()) {
                    JSONObject foodType = menu.optJSONObject(i);
                       // menuDataModels.add(new MenuDataModel(foodType, foodType.getString("MealType"), i));
                        String key = foodType.getString("MealType");
                        if (mData.get(key) == null) {
                            mData.put(key, new ArrayList<JSONObject>());
                        }
                        data1 = mData.get(key);
                        data1.add(foodType);
//                        mData.put(key, data1);
                    i++;
                    }

            }
            Set<String> mealTypes = mData.keySet();
            for(String mealType : mealTypes){
                List<JSONObject> meals = mData.get(mealType);
                for(int j = 0 ; j < meals.size() ; j++){
                    menuDataModels.add(new MenuDataModel(meals.get(j),mealType , j));
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return menuDataModels;
    }
}
