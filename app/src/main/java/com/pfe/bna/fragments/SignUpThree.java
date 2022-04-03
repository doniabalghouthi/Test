package com.pfe.bna.fragments;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.pfe.bna.MainActivity;
import com.pfe.bna.R;
import com.pfe.bna.core.AlertBuilder;
import com.pfe.bna.core.AlertButton;
import com.pfe.bna.core.AlertCallback;
import com.pfe.bna.core.Connection;
import com.pfe.bna.core.ConnectionStartHandler;
import com.pfe.bna.core.ConnectionStopHandler;
import com.pfe.bna.core.ConnectionSuccessHandler;
import com.pfe.bna.core.FailEventHandler;
import com.pfe.bna.core.JSONReader;
import com.pfe.bna.core.ListAdapter;
import com.pfe.bna.utils.BNAUtils;
import com.pfe.bna.utils.BNAlert;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SignUpThree extends Fragment {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    /**
     * private variable to hold the user name edit text.
     */
    private EditText username;
    /**
     * private variable to hold the password edit text.
     */
    private EditText password;
    /**
     * private variable to hold the confirm password edit text.
     */
    private EditText confirmPassword;
    /**
     * private variable to hold the vaccine pass image.
     */
    private ImageView vaccinePass;
    /**
     * private variable to hold the button that take a photo.
     */
    private ImageButton takePhotoBtn;
    /**
     * private variable to hold the button that attach the vaccin pass.
     */
    private ImageButton attacheBtn;
    /**
     * private variable to hold the register button object.
     */
    private Button registerBtn;
    /**
     * private variable to hold the previous button object.
     */
    private Button previousBtn;
    /**
     * private variable to hold the login button object.
     */
    private Button loginBtn;
    /**
     * private variable to hold the button loader progess bar.
     */
    private ProgressBar signupLoader;
    /**
     * private list to hold the not valid fields.
     */
    private List<View> notValidFields = new ArrayList<>();
    /**
     * private variable to hold the navigation controller.
     */
    private NavController navController;
    /**
     * flag to control the show/hide of password
     */
    private boolean passwordShown = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * Create instance of nav controller to be used on navigation between fragments
         */
        navController = Navigation.findNavController(view);
        /**
         * get the user name object.
         */
        username = view.findViewById(R.id.signup_username);
        /**
         * get the password edit text object.
         */
        password = view.findViewById(R.id.signup_password);
        /**
         * get the confirm password edit text object.
         */
        confirmPassword = view.findViewById(R.id.signup_confirm_password);
        /**
         * get tge take photo button object.
         */
        takePhotoBtn = view.findViewById(R.id.take_photo_btn);
        /**
         * get the attache button object.
         */
        attacheBtn = view.findViewById(R.id.attache_button);
        /**
         * get the vaccine pass holder object.
         */
        vaccinePass = view.findViewById(R.id.vaccine_pass_attachement);
        /**
         * get the register button object.
         */
        registerBtn = view.findViewById(R.id.register_button);
        /**
         * get the login button object.
         */
        loginBtn = view.findViewById(R.id.signup_login_btn);
        /**
         * get the login button object.
         */
        previousBtn = view.findViewById(R.id.signup_three_previous_btn);
        /**
         * get the sign up loader progress bar.
         */
        signupLoader = view.findViewById(R.id.signup_btn_loader);
        /**
         * handle the register button click event.
         */
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // perform the registration.
                performRegistration();
            }
        });
        /**
         * handle the login button click event.
         */
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.login_graph_id);
            }
        });
        /**
         * handle the login button click event.
         */
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.signuptwo_graph_id);
            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                reset(username);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                reset(password);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        confirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                reset(confirmPassword);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passwordVisibility(password);
        passwordVisibility(confirmPassword);
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        attacheBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("requestCode = " + requestCode);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            System.out.println("imageBitmap = " + imageBitmap.toString());
            vaccinePass.setImageBitmap(imageBitmap);
        }
    }
    /**
     * method to initialize the field after validation
     */
    private void reset(View field) {
        field.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rounded_edit_text));
//        signupLoader.setEnabled(notValidFields.size() == 0);
    }
    /**
     * private method to oppen connecction to server abnd perform
     * the user subscription.
     * This method check also the mandatory information.
     */
    private void performRegistration() {
        try {
            Log.d("performRegistration", "performRegistration method START.");
            validateFields();
            Log.d("performRegistration", "performRegistration method END.");
        } catch (Exception exception) {
            Log.e("performRegistration", exception.getMessage());
        }
    }
    /**
     * method used to validate fileds
     */
    private void validateFields() {
        //variable to hold the alert object
        BNAlert bnAlert = null;
        // check if username is not empty.
        if (username.getText().toString().isEmpty()) {
            // check if username view already exist.
            if (!notValidFields.contains(username)) {
                notValidFields.add(username);
            }
        }
        // check if password is not empty.
        if (password.getText().toString().isEmpty()) {
            // check if password view already exist.
            if (!notValidFields.contains(password)) {
                notValidFields.add(password);
            }
        }
        // check if confirm password is not empty.
        if (confirmPassword.getText().toString().isEmpty()) {
            // check if confirm password view already exist.
            if (!notValidFields.contains(confirmPassword)) {
                notValidFields.add(confirmPassword);
            }
        }

        if (notValidFields.size() > 0) {
            bnAlert = new AlertBuilder(getContext()).setTitle(getString(R.string.mondator_fileds_alert_title))
                    .setMessage(getString(R.string.mondator_fileds_alert_message)).rebuildButtons().setButton(AlertButton.OK)
                    .setHandler(new AlertCallback() {
                        @Override
                        public void apply(int order) {
                            notValidFields.forEach(field -> {
                                field.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.invalid_field_shape));
                            });
                            // set the focus to the first invalid field
                            notValidFields.get(0).requestFocus();
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(notValidFields.get(0), InputMethodManager.SHOW_IMPLICIT);
                            // initialize not valid field list.
                            notValidFields = new ArrayList<>();
                        }
                    }).getWarnAlert();
        } else if(!password.getText().toString().equals(confirmPassword.getText().toString())) {
            bnAlert = new AlertBuilder(getContext()).setTitle(getString(R.string.mondator_fileds_alert_title))
                    .setMessage(getString(R.string.password_mismatch_message)).rebuildButtons().setButton(AlertButton.OK)
                    .setHandler(new AlertCallback() {
                        @Override
                        public void apply(int order) {
                            confirmPassword.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.invalid_field_shape));
                            // set the focus to the first invalid field
                            confirmPassword.requestFocus();
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.showSoftInput(confirmPassword, InputMethodManager.SHOW_IMPLICIT);
                        }
                    }).getWarnAlert();
        } else {
            signupLoader.setVisibility(View.VISIBLE);
            registerBtn.setEnabled(false);
            // set customer user name
            MainActivity.customer.setUsername(username.getText().toString());
            // set Customer password.
            MainActivity.customer.setPassword(password.getText().toString());
            // register customer.
            register();
            System.out.println("customer = " + MainActivity.customer.toString());
        }
    }

    /**
     * method make a http request to register the customer.
     */
    private void register() {
        // open connection.
        Connection.getBuilder(getContext())
                .setOnStart(new ConnectionStartHandler() {
                    @Override
                    public void exec() {
                        // show loader
                        signupLoader.setVisibility(View.VISIBLE);
                        // disable the signup button
                        registerBtn.setEnabled(false);
                    }
                })
                .setOnStop(new ConnectionStopHandler() {
                    @Override
                    public void exec() {
                        // hide loader.
                        signupLoader.setVisibility(View.GONE);
                    }
                })
                .setOnSuccess(new ConnectionSuccessHandler() {
                    @Override
                    public void onReceive(JSONObject response) {
                        // enable login button.
                        loginBtn.setEnabled(true);
                        // disable previous button.
                        previousBtn.setEnabled(false);
                    }
                })
                .setOnFail(new FailEventHandler() {
                    @Override
                    public void onReceive(VolleyError error) {
                        BNAUtils.logError(getContext(), error);
                        // show loader
                        signupLoader.setVisibility(View.GONE);
                        // disable the signup button
                        registerBtn.setEnabled(true);
                    }
                })
                .setUrl(BNAUtils.getBaseUrl() + "/register/subscribe")
                .setParams(MainActivity.customer.toParams())
                .build().start();
    }
    /**
     * method to show/hide the password.
     * @param field
     */
    @SuppressLint("ClickableViewAccessibility")
    private void passwordVisibility(EditText field) {
        field.setOnTouchListener((view, motionEvent) -> {
            final int DRAWABLE_RIGHT = 2;
            if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if(motionEvent.getRawX() >= (field.getRight() - field.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    if (passwordShown) {
                        field.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                        field.setTransformationMethod(new PasswordTransformationMethod());
                    } else {
                        field.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                        field.setTransformationMethod(null);
                    }
                    // invert flag
                    passwordShown=!passwordShown;
                    return true;
                }
            }
            return false;
        });
    }
}