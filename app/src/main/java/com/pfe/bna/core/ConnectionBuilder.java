package com.pfe.bna.core;

import android.content.Context;

import java.util.HashMap;
import java.util.function.Function;

/**
 * Builder pattern class to build connection object.
 */
public class ConnectionBuilder {
    /**
     * private variable to hold the connection object.
     */
    private Connection connection;
    /**
     * private variable to hold the request context.
     */
    private Context context;

    /**
     * Connection builder constructor.
     * @param context
     */
    public ConnectionBuilder(Context context) {
        this.context = context;
        connection = new Connection(context);
    }

    /**
     * build method used to build the connection object.
     * @return
     */
    public Connection build() {
        return connection;
    }

    /**
     * method used to set the on start listener.
     * @param onStartHandler
     * @return
     */
    public ConnectionBuilder setOnStart(ConnectionStartHandler onStartHandler) {
        // set connection on start listener.
        connection.setOnStart(onStartHandler);
        // return the connection builder object.
        return this;
    }

    /**
     * method used to set on success listener.
     * @param onSuccessHandler
     * @return
     */
    public ConnectionBuilder setOnSuccess(ConnectionSuccessHandler onSuccessHandler) {
        // set the on success listener to the connection object.
        connection.setOnSuccess(onSuccessHandler);
        // return the connection builder object.
        return this;
    }

    /**
     * method used to set the on stop listener.
     * @param onStopHandler
     * @return
     */
    public ConnectionBuilder setOnStop(ConnectionStopHandler onStopHandler) {
        // set the on stop listener to the connection object.
        connection.setOnStop(onStopHandler);
        // return the connection builder object.
        return this;
    }

    /**
     * This method is used to set the fail event listener for the connection
     * object.
     * @param onFailHandler
     * @return
     */
    public ConnectionBuilder setOnFail(FailEventHandler onFailHandler) {
        // set the fail event listener to the connection object.
        connection.setOnFail(onFailHandler);
        // return the connection builder object.
        return this;
    }

    /**
     * Method used to set the connection method by default it
     * use the post method.
     * @param httpMethod
     * @return
     */
    public ConnectionBuilder useMethod(int httpMethod) {
        // set the http method to the connection object.
        connection.setMethod(httpMethod);
        // return the connection builder object.
        return this;
    }

    /**
     * method used to set the api url to the connection object.
     * @param url
     * @return
     */
    public ConnectionBuilder setUrl(String url) {
        // set the api url to the connection object.
        connection.setUrl(url);
        // return the connection builder object.
        return this;
    }

    /**
     * method used to set a key value pair to be send as parameter in the
     * connection request.
     * @param key
     * @param value
     * @return
     */
    public ConnectionBuilder setParam(String key, String value) {
        // set the key value pair to the connection object.
        connection.getParams().put(key, value);
        // return the connection builder object.
        return this;
    }

    /**
     * method used to set a Hashmap of parameters to be send in the request
     * as parameter.
     * @param params
     * @return
     */
    public ConnectionBuilder setParams(HashMap<String, String> params) {
        // set the hashmap to the connection object.
        connection.setParams(params);
        // return the connection builder object.
        return this;
    }
}
