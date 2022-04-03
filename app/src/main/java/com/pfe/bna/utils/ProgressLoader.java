package com.pfe.bna.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pfe.bna.R;

public class ProgressLoader {
    /**
     * private variable to hold the loader progress objet.
     */
    private ProgressBar progressBar;
    /**
     * private variable to hold the loader text.
     */
    private TextView progressText;
    /**
     * private variable to hold the context where the progress loader
     * will be show.
     */
    private Context context;
    /**
     * private variable to hold the loader progress holder.
     */
    private LinearLayout holder;
    /**
     * private variable to hold the progress object
     */
    private View progress;
    public ProgressLoader(Context context, ProgressBar progressBar, TextView progressText) {
        this.progressBar = progressBar;
        this.progressText = progressText;
        this.context = context;
        // init the loader object.
        init();
    }

    public ProgressLoader(Context context) {
        this.context = context;
        // init the loader object.
        init();
    }

    public LinearLayout getHolder() {
        return holder;
    }

    public void setHolder(LinearLayout holder) {
        this.holder = holder;
    }

    private void init() {
        // layout inflater to inflate the layout progress layout.
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        // inflate the progress layout.
        progress = layoutInflater.inflate(R.layout.app_loading_progress, null, false);
        //get the progress bar object from layout.
        progressBar = progress.findViewById(R.id.loader_progress_bar);
        //get the text view object from layout.
        progressText = progress.findViewById(R.id.loader_text_view);
    }

    /**
     * method to show the progress loader
     */
    public void show() {
        // check if the holder is not null add view.
        if (holder!=null) {
            if (holder.indexOfChild(progress) == -1) {
                holder.addView(progress);
            }
            holder.setVisibility(View.VISIBLE);
        }
    }

    /**
     * method to hide the progress loader
     */
    public void hide() {
        // check if the holder is not null hide it.
        if (holder!=null) {
            holder.setVisibility(View.GONE);
        }
    }

    /**
     * method to destroy the progress loader
     */
    public void destroy() {
        if (holder!=null) {
            holder.setVisibility(View.GONE);
            holder.removeView(progress);
        }
    }
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public TextView getProgressText() {
        return progressText;
    }

    public void setProgressText(TextView progressText) {
        this.progressText = progressText;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
