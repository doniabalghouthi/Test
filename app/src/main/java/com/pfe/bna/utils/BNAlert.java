package com.pfe.bna.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.pfe.bna.R;
import com.pfe.bna.core.AlertButton;
import com.pfe.bna.core.AlertCallback;

import java.util.List;

public class BNAlert {
    private AlertCallback onAction;
    private View alert;
    private String okLabel;
    private String yesLabel;
    private String noLabel;
    private String cancelLabel;
    public BNAlert(Context context, String title, String message, int icon,  int... buttons) {
       create(context, title, message, icon, buttons);
    }

    public BNAlert() {
    }

    public BNAlert create(Context context, String title, String message, int icon, int... buttons) {
        try {
            Log.d("BNAlert.create","create method start!");
            // get teh layout inflater to inflate custom alert layout.
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            // inflate the alert layout.
            alert = layoutInflater.inflate(R.layout.bna_alert_layout, null, false);
            Log.d("BNAlert.create","alert layout inflated alert:"+ alert.hashCode());
            // get the alert icon view
            ImageView alertIcon = alert.findViewById(R.id.app_alert_icon);
            // get the alert title
            TextView alertTitle = alert.findViewById(R.id.app_alert_title);
            // get the alert close button.
            ImageView alertCloseBtn = alert.findViewById(R.id.app_alert_close_btn);
            // get the alert message.
            TextView alertMessage = alert.findViewById(R.id.app_alert_message);
            // get the alert button holder
            LinearLayout alertButtonHolder = alert.findViewById(R.id.app_alert_buttons_holder);
            // get the alert message holder
            ConstraintLayout alertMessageHolder = alert.findViewById(R.id.app_alert_body);
            // set default icon as info icon
            if (icon!=-1) {
                // set the alert icon.
                alertIcon.setImageResource(icon);
            }
            // set the alert title.
            alertTitle.setText(title);
            // set the alert message
            alertMessage.setText(message);
            Button alertBtn;
            if (okLabel == null) {
                okLabel = context.getString(R.string.app_alert_ok_btn);
            }
            if (yesLabel==null) {
                yesLabel = context.getString(R.string.app_alert_yes_button);
            }
            if (noLabel==null) {
                noLabel = context.getString(R.string.app_alert_no_button);
            }
            if (cancelLabel==null) {
                cancelLabel = context.getString(R.string.app_alert_cancel_button);
            }
            // get the alert buttons
            for (int i = 0; i < buttons.length; i++) {
                alertBtn = new Button(context);
                alertBtn.setBackground(context.getDrawable(R.drawable.alert_btn_shape));
                switch (buttons[i]) {
                    case AlertButton.OK: {
                        alertBtn.setText(okLabel);
                        break;
                    }
                    case AlertButton.YES: {
                        alertBtn.setText(yesLabel);
                        break;
                    }
                    case AlertButton.NO: {
                        alertBtn.setText(noLabel);
                        break;
                    }
                    case AlertButton.CANCEL: {
                        alertBtn.setText(cancelLabel);
                        alertBtn.setBackground(context.getDrawable(R.drawable.alert_negative_button));
                        break;
                    }
                }
                alertBtn.setAllCaps(false);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.0f
                );
                params.setMargins(8,0, 8, 0);
                alertButtonHolder.addView(alertBtn, params);
                Log.d("BNAlert.create","looping alert buttons, buttons(" + i + ") = " + buttons[i]);
                // set click event listener for each button.
                int flag = i;
                alertBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // check if the action callback is not null.
                        if (onAction != null) {
                            Log.d("BNAlert.create","buttons(" + flag + ") clicked buttons flag = " + buttons[flag]);
                            onAction.apply(buttons[flag]);
                        }
                        // dismiss the alert.
                        dismiss();
                    }
                });
            }
            // set the click event handler to alert close button.
            alertCloseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // close the alert
                    dismiss();
                }
            });
            Log.d("BNAlert.create","create method end!");
        } catch (Exception exception) {
            Log.e("BNAlert.create", exception.getMessage());
        }
        return this;
    }

    private void dismiss() {
        ViewGroup parent =  ((ViewGroup) alert.getParent());
        parent.removeView(alert);
    }

    public AlertCallback getOnAction() {
        return onAction;
    }

    public void setOnAction(AlertCallback onAction) {
        this.onAction = onAction;
    }

    public View getView() {
        return alert;
    }

    public String getOkLabel() {
        return okLabel;
    }

    public void setOkLabel(String okLabel) {
        this.okLabel = okLabel;
    }

    public String getYesLabel() {
        return yesLabel;
    }

    public void setYesLabel(String yesLabel) {
        this.yesLabel = yesLabel;
    }

    public String getNoLabel() {
        return noLabel;
    }

    public void setNoLabel(String noLabel) {
        this.noLabel = noLabel;
    }

    public String getCancelLabel() {
        return cancelLabel;
    }

    public void setCancelLabel(String cancelLabel) {
        this.cancelLabel = cancelLabel;
    }
}
