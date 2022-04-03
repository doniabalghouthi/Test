package com.pfe.bna.core;

import org.json.JSONObject;

public interface ParametrizedCallBack {
    /**
     * abstract method to be fired by the caller.
     * we must implement this method to perform the
     * functional programming using arrow function.
     * this method used when we need to pass some data.
     */
    public void onReceive(JSONObject response);
}
