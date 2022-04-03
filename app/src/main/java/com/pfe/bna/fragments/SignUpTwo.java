package com.pfe.bna.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
import com.pfe.bna.core.SimpleAdapter;
import com.pfe.bna.utils.BNAUtils;
import com.pfe.bna.utils.BNAlert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignUpTwo extends Fragment {
    /**
     * array adapter to set agencies list.
     */
    ListAdapter agencyAdapter = null;
    /**
     * array adapter to set towns list
     */
    private ListAdapter townsAdapter = null;
    /**
     * array adapter to set bank list.
     */
    private SimpleAdapter banksAdapter = null;
    /**
     * private variable to hold the RIB edit text.
     */
    private EditText rib;
    /**
     * private variable to hold the not customer checkbox.
     */
    private CheckBox notCustomer;
    /**
     * private variable to hold the bank label object.
     */
    private TextView bankLabel;
    /**
     * private variable to hold the agency label object.
     */
    private TextView agencyLabel;
    /**
     * private variable to hold the spinner contains the list of banks.
     */
    private Spinner bank;
    /**
     * private variable to hold the spinner contains the list of towns.
     */
    private Spinner towns;
    /**
     * private variable to hold the list of bank agency.
     */
    private Spinner agency;
    /**
     * private variable to hold the male radio button.
     */
    private RadioButton male;
    /**
     * private variable to hold female radio button.
     */
    private RadioButton female;
    /**
     * private variable to hold the gender radio button group.
     */
    private RadioGroup genderGroup;
    /**
     * private variable to hold birthday edit text.
     */
    private EditText birthday;
    /**
     * private variable to hold the next button object.
     */
    private Button nextButton;
    /**
     * private variable to hold the open calender button object.
     */
    private ImageView openCalenderBtn;
    /**
     * private variable to hold the image button of not bna customer label.
     */
    private ImageView noCustomerHelp;
    /**
     * private variable to hold the town help.
     */
    private ImageView townHelp;
    /**
     * private variable to hold the agency help.
     */
    private ImageView agencyHelp;
    /**
     * private variable to hold the previous button object.
     */
    private Button prevButton;
    /**
     * private variable to hold the navigation controller.
     */
    private NavController navController;
    /**
     * private list to hold the not valid fields.
     */
    private List<View> notValidFields = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * Create instance of nav controller to be used on navigation between fragments
         */
        navController = Navigation.findNavController(view);
        /**
         * get the rib edit text object.
         */
        rib = view.findViewById(R.id.signup_rib);
        /**
         * get the birthday edit text object.
         */
        birthday = view.findViewById(R.id.signup_birthday);
        /**
         * get the not BNA customer checkbox.
         */
        notCustomer = view.findViewById(R.id.not_bna_customer);
        /**
         * get the bank label object.
         */
        bankLabel = view.findViewById(R.id.bank_label);
        /**
         * get the agency label object.
         */
        agencyLabel = view.findViewById(R.id.agency_label);
        /**
         * get the bank list object.
         */
        bank = view.findViewById(R.id.signup_bank_spinner);
        /**
         * get the bank list object.
         */
        towns = view.findViewById(R.id.signup_town_spinner);
        /**
         * get the agency list object.
         */
        agency = view.findViewById(R.id.signup_agency_spinner);
        /**
         * get the male radio button object.
         */
        male = view.findViewById(R.id.signup_male_radio);
        /**
         * get the female radio button object
         */
        female = view.findViewById(R.id.signup_female_radio);
        /**
         * get the gender radio button group
         */
        genderGroup = view.findViewById(R.id.gender_radio_group);
        /**
         * get the next button object.
         */
        nextButton = view.findViewById(R.id.signup_two_next_btn);
        /**
         * get the previous button object.
         */
        prevButton = view.findViewById(R.id.signup_two_previous_btn);
        /**
         * get the open calender button object.
         */
        openCalenderBtn = view.findViewById(R.id.open_calendar_btn);
        /**
         * get the help button object.
         */
        noCustomerHelp = view.findViewById(R.id.not_bna_customer_help);
        /**
         * get the help button object.
         */
        townHelp = view.findViewById(R.id.twon_label_help);
        /**
         * get the help button object.
         */
        agencyHelp = view.findViewById(R.id.agency_label_help);
        noCustomerHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHelp(1);
            }
        });
        townHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHelp(2);
            }
        });
        agencyHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHelp(3);
            }
        });
        rib.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                reset(rib);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /**
         * Handle the not BNA customer checkbox change event.
         */
        notCustomer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                /**
                 * change the visibility of bank label.
                 */
                if (bankLabel.getVisibility() == View.GONE) {
                    bankLabel.setVisibility(View.VISIBLE);
                } else {
                    bankLabel.setVisibility(View.GONE);
                }
                /**
                 * change the visibility of bank spinner.
                 */
                if (bank.getVisibility() == View.GONE) {
                    bank.setVisibility(View.VISIBLE);
                } else {
                    bank.setVisibility(View.GONE);
                }
                System.out.println("b = " + checked + " notCustomer.isChecked() = "+ notCustomer.isChecked());
                MainActivity.customer.setBnaCustomer(!checked);
            }
        });
        System.out.println("Signup two -> MainActivity.towns = " + MainActivity.towns);
        townsAdapter = new ListAdapter(getContext(), MainActivity.towns);
        banksAdapter = new SimpleAdapter(getContext(), R.array.tunisian_banks);
        towns.setAdapter(townsAdapter);
        bank.setAdapter(banksAdapter);
        /**
         * handle the next button click event.
         */
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validate step three fields.
                validateFields();
            }
        });
        /**
         * handle the previous button click event.
         */
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.signupone_graph_id);
            }
        });
        /**
         * handle the long click event handler on birthday edit text.
         */
        openCalenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                CalendarView calender = new CalendarView(getContext());
                calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        Date dt = BNAUtils.parseDate(dayOfMonth + "/" + (month + 1) + "/" + year);
                        String dateString = BNAUtils.formatDate(dt, "yyyy/MM/dd");
                        birthday.setText(dateString);
                        alertDialog.dismiss();
                    }
                });
                alertDialog.setView(calender);
                alertDialog.show();
            }
        });
        towns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout background = view.findViewById(R.id.stripped_item_layout);
                background.setBackgroundColor(Color.TRANSPARENT);
                if (i > 0) {
                    // set customer town
                    try {
                        System.out.println("town ==> " + new JSONObject(adapterView.getItemAtPosition(i).toString()).getString("text").toString());
                        MainActivity.customer.setTown(new JSONObject(adapterView.getItemAtPosition(i).toString()).getString("text").toString());
                        reset(towns);
                        agencyLabel.setVisibility(View.VISIBLE);
                        agency.setVisibility(View.VISIBLE);
                        agencyHelp.setVisibility(View.VISIBLE);
                        // load agencies in the selected town
                        loadAgencies(adapterView.getItemAtPosition(i).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    agencyLabel.setVisibility(View.GONE);
                    agency.setVisibility(View.GONE);
                    agencyHelp.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null) {
                    reset(bank);
                    LinearLayout background = view.findViewById(R.id.stripped_item_layout);
                    background.setBackgroundColor(Color.TRANSPARENT);
                    if (i > 0) {
                        System.out.println("bank ==> " + adapterView.getItemAtPosition(i).toString());
                        // set customer bank
                        MainActivity.customer.setBank(adapterView.getItemAtPosition(i).toString());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        agency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout background = view.findViewById(R.id.stripped_item_layout);
                background.setBackgroundColor(Color.TRANSPARENT);
                // set customer agency
                try {
                    if (i > 0) {
                        System.out.println("agency ==> " + new JSONObject(adapterView.getItemAtPosition(i).toString()).getString("text").toString());
                        MainActivity.customer.setAgency(new JSONObject(adapterView.getItemAtPosition(i).toString()).getString("text").toString());
                        reset(agency);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                switch (index) {
                    case 0:
                        MainActivity.customer.setGender("Male");
                        break;
                    case 1:
                        MainActivity.customer.setGender("Female");
                        break;
                }
            }
        });
    }

    /**
     * method to show help message.
     *
     * @param helpId
     */
    private void showHelp(int helpId) {
        String message = "";
        if (helpId == 1) {
            message = getString(R.string.not_bna_customer_help);
        } else if (helpId == 2) {
            message = getString(R.string.bna_town_help);
        } else if (helpId == 3) {
            message = getString(R.string.bna_agency_help);
        }
        new AlertBuilder(getContext())
                .setTitle(getString(R.string.help_label))
                .setMessage(message)
                .rebuildButtons().setButton(AlertButton.OK)
                .getInfoAlert();
    }

    /**
     * method to load agencies.
     *
     * @param town
     */
    private void loadAgencies(String town) {
        System.out.println("loadAgencies -> town param = " + town);
        try {
            // variable to hold town id.
            String townId = new JSONObject(town).getString("value");
            // open connection.
            Connection.getBuilder(getContext())
                    .setOnStart(new ConnectionStartHandler() {
                        @Override
                        public void exec() {
                            // show loader
                            MainActivity.progressLoader.show();
                        }
                    })
                    .setOnStop(new ConnectionStopHandler() {
                        @Override
                        public void exec() {
                            // hide loader.
                            MainActivity.progressLoader.hide();
                        }
                    })
                    .setOnSuccess(new ConnectionSuccessHandler() {
                        @Override
                        public void onReceive(JSONObject response) {
                            System.out.println("loadAgencies -> response = " + response);
                            JSONReader jsonReader = new JSONReader(response);
                            JSONArray agencies = jsonReader.getSpinnerItems("agencies");
                            agencyAdapter = new ListAdapter(getContext(), agencies);
                            agency.setAdapter(agencyAdapter);
                            System.out.println("loadAgencies -> agencies = " + agencies);
                            if (!MainActivity.customer.getAgency().isEmpty()) {
                                agency.setSelection(agencyAdapter.getPosition(MainActivity.customer.getAgency()));
                            }

                        }
                    })
                    .setOnFail(new FailEventHandler() {
                        @Override
                        public void onReceive(VolleyError error) {
                            BNAUtils.logError(getContext(), error);
                        }
                    })
                    .setUrl(BNAUtils.getBaseUrl() + "/register/agencies")
                    .setParam("lang", "fr")
                    .setParam("name", townId)
                    .build().start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * method used to validate fileds
     */
    private void validateFields() {
        //variable to hold the alert object
        BNAlert bnAlert = null;
        // check if name is not empty.
        if (rib.getText().toString().isEmpty()) {
            // check if name view already exist.
            if (notValidFields.indexOf(rib) == -1) {
                notValidFields.add(rib);
            }
        }
        // check if family name is not empty.
        if (towns.getSelectedItemPosition() == 0) {
            // check if family name view already exist.
            if (towns.getSelectedItemPosition() == 0) {
                notValidFields.add(towns);
            }
        }
        // check if family name is not empty.
        if (agency.getSelectedItemPosition() == 0) {
            // check if family name view already exist.
            if (agency.getSelectedItemPosition() == 0) {
                notValidFields.add(agency);
            }
        }
        if (notCustomer.isChecked()) {
            // check if cin is not empty.
            if (bank.getSelectedItemPosition() == 0) {
                // check if family name view already exist.
                if (notValidFields.indexOf(bank) == -1) {
                    notValidFields.add(bank);
                }
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
        } else {
            // set customer RIB
            MainActivity.customer.setRib(rib.getText().toString());
            // set Customer birthday.
            MainActivity.customer.setBirthday(birthday.getText().toString());
            // navigate to next step.
            navController.navigate(R.id.signupthree_graph_id);
            System.out.println("customer = " + MainActivity.customer.toString());
        }
    }

    /**
     * method to initialize the field after validation
     */
    private void reset(View field) {
        field.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.rounded_edit_text));
        nextButton.setEnabled(notValidFields.size() == 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("MainActivity.customer.toString() = " + MainActivity.customer.toString());
        /**
         * initialize the signup fields
         */
        init();
    }

    /**
     * method to initialize the sign up view
     */
    private void init() {
        if (MainActivity.customer != null) {
            if (!MainActivity.customer.getRib().equals("")) {
                rib.setText(MainActivity.customer.getRib());
            }
            if (!MainActivity.customer.getBnaCustomer()) {
                notCustomer.setChecked(true);
                bank.setVisibility(View.VISIBLE);
                bankLabel.setVisibility(View.VISIBLE);
            } else {
                notCustomer.setChecked(false);
                bank.setVisibility(View.GONE);
                bankLabel.setVisibility(View.GONE);
            }
            if (!MainActivity.customer.getBank().isEmpty()) {
                bank.setSelection(banksAdapter.getPosition(MainActivity.customer.getBank()));
            }
            if (!MainActivity.customer.getTown().isEmpty()) {
                towns.setSelection(townsAdapter.getPosition(MainActivity.customer.getTown()));
                // load agencies.
                loadAgencies("{value: '"+ MainActivity.customer.getTown().toLowerCase() +"'}");
            }
            if (MainActivity.customer.getGender().toUpperCase().equals("FEMALE")) {
                male.setChecked(false);
                female.setChecked(true);
            } else {
                male.setChecked(true);
                female.setChecked(false);
            }
            if (!MainActivity.customer.getBirthday().isEmpty()) {
                birthday.setText(MainActivity.customer.getBirthday());
            }
        }
    }
}