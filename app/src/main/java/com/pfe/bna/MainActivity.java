package com.pfe.bna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.pfe.bna.core.Connection;
import com.pfe.bna.core.ConnectionStartHandler;
import com.pfe.bna.core.ConnectionStopHandler;
import com.pfe.bna.core.ConnectionSuccessHandler;
import com.pfe.bna.core.FailEventHandler;
import com.pfe.bna.fragments.Splash;
import com.pfe.bna.model.Customer;
import com.pfe.bna.utils.BNAUtils;
import com.pfe.bna.utils.ProgressLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static LinearLayout alertHolder;
    private static LinearLayout alertContainer;
    private static LinearLayout secondLevel;
    public static ProgressLoader progressLoader;
    public static Customer customer;
    public static JSONArray towns = new JSONArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertContainer = findViewById(R.id.app_alert_container);
        alertHolder = findViewById(R.id.app_alert_holder);
        secondLevel = findViewById(R.id.second_level_container);
        progressLoader = new ProgressLoader(MainActivity.this);
        progressLoader.setHolder(secondLevel);
        alertContainer.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View view, View view1) {
                if (alertContainer.getChildCount() == 0) {
                    alertContainer.setVisibility(View.GONE);
                    alertHolder.setVisibility(View.GONE);
                } else {
                    alertContainer.setVisibility(View.VISIBLE);
                    alertHolder.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onChildViewRemoved(View view, View view1) {
                if (alertContainer.getChildCount() == 0) {
                    alertContainer.setVisibility(View.GONE);
                    alertHolder.setVisibility(View.GONE);
                } else {
                    alertContainer.setVisibility(View.VISIBLE);
                    alertHolder.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    public static LinearLayout getAlertChanel() {
        return  alertContainer;
    }
}