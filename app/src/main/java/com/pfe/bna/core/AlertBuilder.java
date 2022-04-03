package com.pfe.bna.core;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pfe.bna.MainActivity;
import com.pfe.bna.R;
import com.pfe.bna.utils.BNAlert;

import java.util.ArrayList;
import java.util.List;

public class AlertBuilder {
    private BNAlert bnAlert;
    private Context context;
    private String alertTitle;
    private String alertMessage;
    private int alertIcon;
    private ViewGroup alertChannel = MainActivity.getAlertChanel();
    private  int[] buttons;
    private AlertCallback alertAction;
    private LinearLayout.LayoutParams layoutParams;
    private Animation animation;
    private String okLabel;
    private String yesLabel;
    private String noLabel;
    private String cancelLabel;
    public AlertBuilder(Context context) {
        this.context = context;
        if (buttons == null) {
            buttons = new int[]{AlertButton.OK, AlertButton.CANCEL};
        }
        bnAlert = new BNAlert();
    }
    public AlertBuilder toChannel(ViewGroup channel) {
        alertChannel = channel;
        Log.e("AlertBuilder.toChanel", "Implement the hide/show logic for channel");
        return this;
    }
    public AlertBuilder setOkLabel(String okLabel) {
        this.okLabel = okLabel;
     return this;
    }
    public AlertBuilder setYesLabel(String yesLabel) {
        this.yesLabel = yesLabel;
     return this;
    }
    public AlertBuilder setNoLabel(String noLabel) {
        this.noLabel = noLabel;
     return this;
    }
    public AlertBuilder setCancelLabel(String cancelLabel) {
        this.cancelLabel = cancelLabel;
     return this;
    }
    public AlertBuilder setTitle(String title) {
        alertTitle = title;
        return this;
    }
    public AlertBuilder setMessage(String message) {
        alertMessage = message;
        return this;
    }
    public AlertBuilder setIcon(int icon) {
        alertIcon = icon;
        return this;
    }
    public AlertBuilder setButton(int button) {
        int[] newButtons = new int[buttons.length + 1];
        newButtons[0] = button;
        for (int i = 0; i < buttons.length; i++) {
            newButtons[i+1] = buttons[i];
        }
        buttons = newButtons;
        return this;
    }
    public AlertBuilder animate(int anim) {
        animation = AnimationUtils.loadAnimation(context, anim);
        return this;
    }
    public AlertBuilder setHandler(AlertCallback handler) {
        alertAction = handler;
        return this;
    }
    public AlertBuilder rebuildButtons() {
        buttons = new int[]{};
        return this;
    }
    public AlertBuilder setLayoutParams(LinearLayout.LayoutParams params) {
        layoutParams = params;
        return this;
    }
    public BNAlert build() {
        BNAlert rtn = bnAlert;
        try {
            bnAlert.setOnAction(alertAction);
            if (okLabel!=null) {
                bnAlert.setOkLabel(okLabel);
            }
            if (noLabel!=null) {
                bnAlert.setNoLabel(noLabel);
            }
            if (yesLabel!=null) {
                bnAlert.setYesLabel(yesLabel);
            }
            if (cancelLabel!=null) {
                bnAlert.setCancelLabel(cancelLabel);
            }
            if (layoutParams==null) {
                layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParams.setMargins((int) context.getResources().getDimension(R.dimen.app_alert_spacing),
                        (int) context.getResources().getDimension(R.dimen.app_alert_spacing),
                        (int) context.getResources().getDimension(R.dimen.app_alert_spacing),
                        (int) context.getResources().getDimension(R.dimen.app_alert_spacing));
            }
            rtn = bnAlert.create(context, alertTitle, alertMessage, alertIcon, buttons);
            if (alertChannel!= null) {
                if (animation==null) {
                    animation = AnimationUtils.loadAnimation(context, R.anim.view_set_transition);
                }
                rtn.getView().startAnimation(animation);
                alertChannel.addView(rtn.getView(), layoutParams);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return rtn;
    }
    public BNAlert getInfoAlert() {
        alertIcon = R.drawable.ic_baseline_info_24;
        return build();
    }
    public BNAlert getErrorAlert() {
        alertIcon = R.drawable.ic_baseline_error_24;
        return build();
    }
    public BNAlert getWarnAlert() {
        alertIcon = R.drawable.ic_baseline_warning_24;
        return build();
    }
    public BNAlert getConfirmAlert() {
        alertIcon = R.drawable.ic_baseline_warning_24;
        return build();
    }
}
