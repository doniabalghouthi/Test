package com.pfe.bna.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONReader {
    /**
     * private variable to hold the json response.
     */
    private JSONObject response;

    /**
     * JSONReader constructor
     * @param response
     */
    public JSONReader(JSONObject response) {
        this.response = response;
    }

    public JSONObject getSingletons() throws JSONException {
        return response.getJSONObject("singletons");
    }

    public JSONArray getSpinnerItems(String spinnerId) {
        JSONArray items = null;
        try {
            JSONArray spinners = response.getJSONArray("spinners");
            for (int i = 0; i < spinners.length(); i++) {
                if (spinners.getJSONObject(i).getString("spinnerId").equals(spinnerId)) {
                    items = spinners.getJSONObject(i).getJSONArray("items");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }
}
