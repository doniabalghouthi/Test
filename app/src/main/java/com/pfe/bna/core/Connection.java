package com.pfe.bna.core;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Connection {
    /**
     * Call back function to be set by the communication caller
     * to perform some businesses when the communication starting.
     */
    private ConnectionStartHandler onStart;
    /**
     * Callback function to be set by the communication caller to
     * perform some activities when the response received successfully
     * from the server.
     * This method receive the response object as json within an object passed
     * as parameter.
     */
    private ConnectionSuccessHandler onSuccess;
    /**
     * Call back function to be set by the communication caller
     * to perform some businesses when the communication end.
     */
    private ConnectionStopHandler onStop;
    /**
     * Call back function to be set by the communication caller
     * to perform some businesses when the communication end with error.
     */
    private FailEventHandler onFail;
    /**
     * private variable to hold the connection api path.
     */
    private String url;
    /**
     * private variable to hold the connection method.
     */
    private int method = HttpMethod.POST;
    /**
     * Hashmap to store request body params.
     */
    private HashMap<String, String> params = new HashMap<>();
    /**
     * private variable to hold the connection thread context.
     */
    private Context context;
    /**
     * method called after initializing the connection to start
     * the request thread.
     */
    public void start() {
        try {
            // creating a new variable for volley request queue
            RequestQueue queue = Volley.newRequestQueue(context);
            /**
             * Call a string request method to post the data to server API.
             */
            StringRequest request = new StringRequest(method, url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String res) {
                    try {
                        /**
                         * parse response data to json object in order to extract
                         * parameters.
                         */
                        JSONObject response = new JSONObject(res);
                        /**
                         * Check if on success callback is defined then execute it.
                         */
                        if (onSuccess!=null) {
                            // execute on success listener.
                            onSuccess.onReceive(response);
                        }
                        /**
                         * Check if on stop callback is defined then execute it.
                         */
                        if (onStop!=null) {
                            // execute on stop listener.
                            onStop.exec();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    /**
                     * Check on fail callback if not null the execute it.
                     */
                    if (onFail!=null) {
                        // execute on fail callback method.
                        onFail.onReceive(error);
                    }
                    /**
                     * Call the on stop listener when the request end with error.
                     */
                    if (onStop!=null) {
                        // execute on stop method.
                        onStop.exec();
                    }
                }
            }) {
                /**
                 * override method getParams to put request body parameters.
                 * @return
                 */
                @Override
                protected Map<String, String> getParams() {
                    // hashmap to hold the request params.
                    HashMap<String, String> params = Connection.this.getParams();
                    // set content type to json.
                    params.put("Content-Type", "application/json; charset=utf-8");
                    // returning params.
                    return params;
                }
            };
            /**
             * Check the on Start callback existence if it's ok then execute
             * it.
             */
            if (onStart!=null) {
                // execute on start listener.
                onStart.exec();
            }
            // start the volley request.
            queue.add(request);
        } catch (Exception exception) {
            Log.e("Connection start", exception.getMessage());
        }
    }

    /**
     * Connection constructor.
     * @param context
     */
    public Connection(Context context) {
        this.context = context;
    }

    /**
     * On start listener setter.
     * @param onStart
     */
    public void setOnStart(ConnectionStartHandler onStart) {
        this.onStart = onStart;
    }

    /**
     * on success listener setter.
     * @param onSuccess
     */
    public void setOnSuccess(ConnectionSuccessHandler onSuccess) {
        this.onSuccess = onSuccess;
    }

    /**
     * on stop listener setter.
     * @param onStop
     */
    public void setOnStop(ConnectionStopHandler onStop) {
        this.onStop = onStop;
    }

    /**
     * on fail listener setter.
     * @param onFail
     */
    public void setOnFail(FailEventHandler onFail) {
        this.onFail = onFail;
    }

    /**
     * Url getter.
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * url setter.
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * request method getter.
     * @return
     */
    public int getMethod() {
        return method;
    }

    /**
     * request method setter.
     * @param method
     */
    public void setMethod(int method) {
        this.method = method;
    }

    /**
     * request params getter.
     * @return
     */
    public HashMap<String, String> getParams() {
        return params;
    }

    /**
     * request params setter.
     * @param params
     */
    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    /**
     * static method to get the connection builder.
     * @param context
     * @return
     */
    public static ConnectionBuilder getBuilder(Context context) {
        return  new ConnectionBuilder(context);
    }
}
