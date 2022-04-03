package com.pfe.bna.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.pfe.bna.core.ParametrizedCallBack;
import com.pfe.bna.utils.BNAlert;

import org.json.JSONObject;

import java.util.HashMap;

public class Home extends Fragment {
    /**
     * private variable to hold the profile image object.
     */
    private ImageView profileImage;
    /**
     * private variable to hold the profile name object.
     */
    private TextView profileUserName;
    /**
     * private variable to hold the user RIB text view object.
     */
    private TextView rib;
    /**
     * private variable to hold the user agency text view object.
     */
    private TextView agency;
    /**
     * private variable to hold the notification button object.
     */
    private ImageButton notificationButton;
    /**
     * private variable to hold the notification count text view object.
     */
    private TextView notificationCount;
    /**
     * private variable to hold the logout button object.
     */
    private ImageButton logoutBtn;
    /**
     * private variable to hold the service request button object.
     */
    private Button serviceRequestBtn;
    /**
     * private variable to hold the BNA services button object.
     */
    private Button ourServicesBtn;
    /**
     * private variable to hold the history button object.
     */
    private Button historyBtn;
    /**
     * private variable to hold the contact button object.
     */
    private Button contactsBtn;
    /**
     * private variable to hold the profile button object.
     */
    private Button profileBtn;
    /**
     * private variable to hold the parameters button object.
     */
    private Button parametersBtn;
    /**
     * private variable to hold the notification holder object.
     */
    private LinearLayout notificationHolder;
    /**
     * private variable to hold the ticket info holder object.
     */
    private LinearLayout ticketInfoHolder;
    int time;
    /**
     * private variable to hold the navigation controller object.
     */
    NavController navController;
    /**
     * private variable to hold the verified badge view object.
     */
    private View verifiedBadge;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * Create instance of nav controller to be used on navigation between fragments
         */
        navController = Navigation.findNavController(view);
        /**
         * get profile image view object.
         */
        profileImage = view.findViewById(R.id.profile_image);
        /**
         * get the verified badge view object.
         */
        verifiedBadge = view.findViewById(R.id.verified_badge);
        /**
         * get profile user name text view object.
         */
        profileUserName = view.findViewById(R.id.profile_username);
        /**
         * get user RIB text view object.
         */
        rib = view.findViewById(R.id.rib_label);
        /**
         * get agency text view object.
         */
        agency = view.findViewById(R.id.agency_label);
        /**
         * get notification button object.
         */
        notificationButton = view.findViewById(R.id.notification_bell);
        /**
         * get notification count text view object.
         */
        notificationCount = view.findViewById(R.id.notfication_count);
        /**
         * get logout button object.
         */
        logoutBtn = view.findViewById(R.id.logout_btn);
        /**
         * get service request button object.
         */
        serviceRequestBtn = view.findViewById(R.id.service_req_btn);
        /**
         * get our services button object.
         */
        ourServicesBtn = view.findViewById(R.id.our_services_btn);
        /**
         * get history button object.
         */
        historyBtn = view.findViewById(R.id.history_button);
        /**
         * get contacts button object.
         */
        contactsBtn = view.findViewById(R.id.contacts_button);
        /**
         * get profile button object.
         */
        profileBtn = view.findViewById(R.id.profile_button);
        /**
         * get parameters button object.
         */
        parametersBtn = view.findViewById(R.id.parameters_button);
        /**
         * get notification layout holder object.
         */
        notificationHolder = view.findViewById(R.id.notification_holder);
        /**
         * get ticket info holder object.
         */
        ticketInfoHolder = view.findViewById(R.id.ticketinfo_holder);
        /**
         * Handle the click event on service request button.
         */
        serviceRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // TODO implement the click event handler here...
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                View ticketInfo = layoutInflater.inflate(R.layout.ticket_info_template, null, false);
                ticketInfoHolder.addView(ticketInfo);
            }
        });
        /**
         * Handle the click event on our services button.
         */
        ourServicesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // TODO implement the click event handler here...
            }
        });
        /**
         * Handle the click event on history button.
         */
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // TODO implement the click event handler here...
                try {
                    Log.d("history btn"," history btn event listener start");
                    new AlertBuilder(getContext()).setTitle("Warning Alert")
                            .setMessage(getString(R.string.app_ipmsum_text_exemple)).setHandler(new AlertCallback() {
                        @Override
                        public void apply(int order) {
                            if (order == AlertButton.OK) {
                                new AlertBuilder(getContext()).setTitle("information").setMessage("You clicked the ok button")
                                        .rebuildButtons().setButton(AlertButton.OK).getInfoAlert();
                            } else if (order == AlertButton.CANCEL) {
                                new AlertBuilder(getContext()).setTitle("information").setMessage("You clicked the cancel button")
                                        .rebuildButtons().setButton(AlertButton.OK).getInfoAlert();
                            }
                        }
                    }).build();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        /**
         * Handle the click event on contacts button.
         */
        contactsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // TODO implement the click event handler here...
                MainActivity.progressLoader.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.progressLoader.hide();
                    }
                }, 5000);
            }
        });
        profileImage.setOnClickListener(e-> {

        });
        /**
         * Handle the click event on profile button.
         */
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // TODO implement the click event handler here...
            }
        });
        /**
         * Handle the click event on parameters button.
         */
        parametersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // TODO implement the click event handler here...
            }
        });
        /**
         * Handle the click event on notification button.
         */
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // TODO implement the click event handler here...
            }
        });
        /**
         * Handle the click event on logout button.
         */
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // TODO implement the click event handler here...
            }
        });
        /**
         * Handle the click event on profile image.
         */
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             // TODO implement the click event handler here...
            }
        });
    }

}