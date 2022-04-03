package com.pfe.bna.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.pfe.bna.MainActivity;
import com.pfe.bna.R;
import com.pfe.bna.core.Connection;
import com.pfe.bna.core.ConnectionStartHandler;
import com.pfe.bna.core.ConnectionStopHandler;
import com.pfe.bna.core.ConnectionSuccessHandler;
import com.pfe.bna.core.FailEventHandler;
import com.pfe.bna.core.JSONReader;
import com.pfe.bna.utils.BNAUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Locale;

public class Splash extends Fragment {
    /**
     * Text view object to show the copyright information.
     */
    private TextView copyrightText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * Create instance of nav controller to be used on navigation between fragments
         */
        NavController navController = Navigation.findNavController(view);
        /**
         * get the text view object instance.
         */
        copyrightText = view.findViewById(R.id.copyright_text);
        /**
         * set copyright text programmatically to set the current year dynamically.
         */
        copyrightText.setText(BNAUtils.substitute(getString(R.string.copyright_text), "$date", Calendar.getInstance().get(Calendar.YEAR)+""));
        /**
         * Handler object to set delay before skip the splash screen.
         */
        Handler handler = new Handler();
        /**
         * get application data
         */
        Connection.getBuilder(getContext())
                .setOnStart(new ConnectionStartHandler() {
                    @Override
                    public void exec() {

                    }
                })
                .setOnStop(new ConnectionStopHandler() {
                    @Override
                    public void exec() {

                    }
                })
                .setOnSuccess(new ConnectionSuccessHandler() {
                    @Override
                    public void onReceive(JSONObject response) {
                        System.out.println("response = " + response);
                        JSONReader jsonReader  = new JSONReader(response);
                        JSONArray towns = jsonReader.getSpinnerItems("towns");
                        MainActivity.towns = towns;
                        System.out.println("towns = " + towns);
                        navController.navigate(R.id.login_graph_id);
                    }
                })
                .setOnFail(new FailEventHandler() {
                    @Override
                    public void onReceive(VolleyError error) {
                        error.printStackTrace();
                        navController.navigate(R.id.login_graph_id);
                    }
                })
                .setUrl(BNAUtils.getBaseUrl()+"/main/init")
                .setParam("lang", Locale.getDefault().getLanguage())
                .build().start();
    }
}