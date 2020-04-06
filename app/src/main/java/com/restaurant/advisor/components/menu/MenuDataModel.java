package com.restaurant.advisor.components.menu;

import org.json.JSONObject;

public class MenuDataModel {
    public String id;
    public String name;
    public String price;
    public String section;
    public int positionInSection;

    public MenuDataModel(JSONObject jsonObject, String section, int positionInSection) {
        this.section = section;
        this.positionInSection = positionInSection;
        id = jsonObject.optString("ID");
        name = jsonObject.optString("Name");
        price = jsonObject.optString("Price");

    }
}
