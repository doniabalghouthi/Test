package com.pfe.bna.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.pfe.bna.MainActivity;
import com.pfe.bna.R;
import com.pfe.bna.core.Connection;
import com.pfe.bna.core.ConnectionStartHandler;
import com.pfe.bna.core.ConnectionStopHandler;
import com.pfe.bna.core.ConnectionSuccessHandler;
import com.pfe.bna.core.FailEventHandler;
import com.pfe.bna.model.Customer;

import org.json.JSONObject;

public class Login extends Fragment {
    /**
     * private variable to hold the login button.
     */
    private Button loginBtn;
    /**
     * private variable to hold the sign up button object.
     */
    private Button signUpBtn;
    /**
     * private variable to hold the username edit text object.
     */
    private EditText username;
    /**
     * private variable to hold the password edit text object.
     */
    private EditText password;
    /**
     * private variable to hold the remember me object.
     */
    private CheckBox rememberMe;
    /**
     * private variable to hold the login progress bar.
     */
    private ProgressBar loginProgress;
    /**
     * private variable to hold the navigation controller object.
     */
    NavController navController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * Create instance of nav controller to be used on navigation between fragments
         */
        navController = Navigation.findNavController(view);
        /**
         * get the login button object instance.
         */
        loginBtn = view.findViewById(R.id.login_btn);
        /**
         * get the sign up button object instance.
         */
        signUpBtn = view.findViewById(R.id.signup_btn);
        /**
         * get the user name edit text instance.
         */
        username = view.findViewById(R.id.username_edittext);
        /**
         * get the password edit text instance.
         */
        password = view.findViewById(R.id.password_edittext);
        /**
         * get the remember me checkbox instance.
         */
        rememberMe = view.findViewById(R.id.remember_me_checkbox);
        /**
         * get the login progress bar instance.
         */
        loginProgress = view.findViewById(R.id.login_progress_bar);
        /**
         * handle the login action.
         */
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticate(username.getText().toString(), password.getText().toString());
            }
        });
        /**
         * Handle the sign up action
         */
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new customer object.
                MainActivity.customer = new Customer();
                // navigate to first fragment of sign up process.
                navController.navigate(R.id.signupone_graph_id);
            }
        });
    }

    /**
     * This method is used to authenticate user.
     * @param username
     * @param password
     */
    private boolean authenticate(String username, String password) {
        // variable to hold the authentication result.
        boolean authenticated = false;
        try {
            Connection.getBuilder(getContext()).setUrl("http://192.168.1.13:8080/login")
                    .setParam("p1", "v1")
                    .setParam("p2", "v2")
                    .setParam("p3", "v3")
                    .setOnSuccess(new ConnectionSuccessHandler() {
                        @Override
                        public void onReceive(JSONObject response) {
                            navController.navigate(R.id.home_graph_id);
                            System.out.println("response = " + response);
                        }
                    })
                    .setOnStop(new ConnectionStopHandler() {
                        @Override
                        public void exec() {
                            System.out.println("connection end!");
                            loginProgress.setVisibility(View.GONE);
                        }
                    })
                    .setOnStart(new ConnectionStartHandler() {
                        @Override
                        public void exec() {
                            System.out.println("connection started!");
                            loginProgress.setVisibility(View.VISIBLE);
                        }
                    })
                    .setOnFail(new FailEventHandler() {
                        @Override
                        public void onReceive(VolleyError error) {
                            error.printStackTrace();
                            System.out.println("cause ==> "+error.getCause());
                            navController.navigate(R.id.home_graph_id);
//                                Log.e("connection failed", error.getMessage());
                        }
                    })
                    .build().start();
        } catch (Exception exception) {
            Log.e("authenticate method", exception.getMessage());
        }
        return authenticated;
    }
}