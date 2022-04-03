package com.pfe.bna.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.pfe.bna.MainActivity;
import com.pfe.bna.R;
import com.pfe.bna.core.AlertBuilder;
import com.pfe.bna.core.AlertButton;
import com.pfe.bna.core.AlertCallback;
import com.pfe.bna.model.Customer;
import com.pfe.bna.utils.BNAlert;

import java.util.ArrayList;
import java.util.List;

public class SignUpOne extends Fragment {
    /**
     * private variable to hold the name edit text.
     */
    private EditText name;
    /**
     * private variable to hold the family name edit text.
     */
    private EditText familyName;
    /**
     * private variable to hold the cin edit text.
     */
    private EditText cin;
    /**
     * private variable to hold the full address edit text.
     */
    private EditText fullAddress;
    /**
     * private variable to hold the mobile edit text
     */
    private EditText mobile;
    /**
     * private variable to hold the next button.
     */
    private Button nextButton;
    /**
     * private variable to hold the previous button.
     */
    private Button previousBtn;
    /**
     * private list to hold the not valid fields.
     */
    private List<EditText> notValidFields = new ArrayList<>();
    /**
     * private variable to hold the navigation controller.
     */
    private NavController navController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_one, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * initialize the signup fields
         */
        init();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * Create instance of nav controller to be used on navigation between fragments
         */
        navController = Navigation.findNavController(view);
        /**
         * get the sign up name edit text instance.
         */
        name = view.findViewById(R.id.signup_name);
        /**
         * get the family name edit text object.
         */
        familyName = view.findViewById(R.id.signup_family_name);
        /**
         * get the cin edit text object.
         */
        cin = view.findViewById(R.id.signup_confirm_password);
        /**
         * get the full address edit text object.
         */
        fullAddress = view.findViewById(R.id.signup_full_address);
        /**
         * get the mobile edit text object.
         */
        mobile = view.findViewById(R.id.signup_mobile);
        /**
         * get the next button object.
         */
        nextButton = view.findViewById(R.id.register_button);
        /**
         * get the previous button object.
         */
        previousBtn = view.findViewById(R.id.signup_one_previous_btn);
        /**
         * handle the click event on next button.
         */
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set name string
                MainActivity.customer.setName(name.getText().toString());
                // set the family name string
                MainActivity.customer.setFamilyName(familyName.getText().toString());
                // set full address string
                MainActivity.customer.setFullAddress(fullAddress.getText().toString());
                // set the cin string.
                MainActivity.customer.setCin(cin.getText().toString());
                // set the mobile string
                MainActivity.customer.setMobile(mobile.getText().toString());
                /**
                 * navigate to next sign up step.
                 */
                navigateToNext();
            }
        });
        /**
         * Handle previous button click event handler
         */
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to login page.
                navController.navigate(R.id.login_graph_id);
            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                reset(name);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        familyName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                reset(familyName);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        cin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                reset(cin);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * method to initialize the field after validation
     */
    private void reset(EditText field) {
        field.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rounded_edit_text));
        if (notValidFields.size()==0) {
            nextButton.setEnabled(true);
        } else {
            nextButton.setEnabled(false);
        }
    }
    /**
     * method to initialize the sign up view
     */
    private void init() {
        if (MainActivity.customer != null) {
            if (!MainActivity.customer.getName().equals("")) {
                name.setText(MainActivity.customer.getName());
            }
            if (!MainActivity.customer.getFamilyName().equals("")) {
                familyName.setText(MainActivity.customer.getFamilyName());
            }
            if (!MainActivity.customer.getCin().equals("")) {
                cin.setText(MainActivity.customer.getCin());
            }
            if (!MainActivity.customer.getFullAddress().equals("")) {
                fullAddress.setText(MainActivity.customer.getFullAddress());
            }
            if (!MainActivity.customer.getMobile().equals("")) {
                mobile.setText(MainActivity.customer.getMobile());
            }
        }
    }

    /**
     * private method to check data filled by the user and
     * control the mandatory fields then navigate to next step
     * if all is right.
     */
    private void navigateToNext() {
        try {
            Log.d("navigateToNext", "navigateToNext START.");
            validateFields();
            Log.d("navigateToNext", "navigateToNext END.");
        } catch (Exception exception) {
            Log.e("navigateToNext", exception.getMessage());
        }
    }

    /**
     * private method to validate the signup fields.
     */
    private void validateFields() {
        //variable to hold the alert object
        BNAlert bnAlert = null;
        // check if name is not empty.
        if (MainActivity.customer.getName().isEmpty()) {
            // check if name view already exist.
            if (notValidFields.indexOf(name) == -1) {
                notValidFields.add(name);
            }
        }
        // check if family name is not empty.
        if (MainActivity.customer.getFamilyName().isEmpty()) {
            // check if family name view already exist.
            if (notValidFields.indexOf(familyName) == -1) {
                notValidFields.add(familyName);
            }
        }
        // check if cin is not empty.
        if (MainActivity.customer.getCin().isEmpty()) {
            // check if family name view already exist.
            if (notValidFields.indexOf(cin) == -1) {
                notValidFields.add(cin);
            }
        }

        if (notValidFields.size() > 0) {
            bnAlert = new AlertBuilder(getContext()).setTitle(getString(R.string.mondator_fileds_alert_title))
                    .setMessage(getString(R.string.mondator_fileds_alert_message)).rebuildButtons().setButton(AlertButton.OK)
                    .setHandler(new AlertCallback() {
                        @Override
                        public void apply(int order) {
                            notValidFields.forEach(field-> {
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
        } else {
            // navigate to next step.
            navController.navigate(R.id.signuptwo_graph_id);
        }
    }
}