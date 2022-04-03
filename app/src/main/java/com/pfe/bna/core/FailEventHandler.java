package com.pfe.bna.core;

import com.android.volley.VolleyError;

/**
 * abstract class to be used in the connection implementation.
 */
public abstract class FailEventHandler {
    /**
     * abstract method onReceive called when the connection end with error
     * Volley object instantiate an object with VolleyError type and passe it
     * on parameter to call back function.
     * @param error
     */
    public abstract void onReceive(VolleyError error);
}
