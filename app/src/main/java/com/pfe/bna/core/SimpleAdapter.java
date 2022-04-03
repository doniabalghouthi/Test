package com.pfe.bna.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.pfe.bna.R;

import java.util.Arrays;

public class SimpleAdapter extends BaseAdapter {
    private String[] items = new String[]{};
    private Context context;
    public SimpleAdapter(Context context, int resources) {
        this.items = context.getResources().getStringArray(resources);
        this.context = context;
        System.out.println("ListAdapter -> items = " + items);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return -1;
    }

    public int getPosition(String item) {
        return Arrays.asList(items).indexOf(item);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View option = inflater.inflate(R.layout.spinner_item_layout, null);
        TextView text = option.findViewById(R.id.spinner_item_text);
        LinearLayout layout = option.findViewById(R.id.stripped_item_layout);
        text.setText(items[i]);
        if (i%2==0) {
            layout.setBackground(ContextCompat.getDrawable(context, R.color.edit_text_bg_color ));
        }
        return option;
    }
}

