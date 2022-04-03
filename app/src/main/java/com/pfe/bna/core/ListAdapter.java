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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListAdapter extends BaseAdapter {
    private JSONArray items = new JSONArray();
    private final Context context;

    public ListAdapter(Context context, JSONArray items) {
        System.out.println("ListAdapter -> items = " + items);
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (items != null) {
            return items.length();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        Object item = null;
        try {
            if (items != null) {
                item = items.get(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }

    public int getPosition(String item) {
        int index = -1;
        try {
            for (int i = 0; i < items.length(); i++) {
                if (((JSONObject) items.get(i)).getString("text").equals(item)) {
                    index = i;
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public long getItemId(int i) {
        long id = -1;
        try {
            id = ((JSONObject) getItem(i)).getLong("itemId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View option = inflater.inflate(R.layout.spinner_item_layout, null);
        TextView text = option.findViewById(R.id.spinner_item_text);
        LinearLayout layout = option.findViewById(R.id.stripped_item_layout);
        try {
            String label = ((JSONObject) items.get(i)).getString("text");
            text.setText(label);
            if (i % 2 == 0) {
                layout.setBackground(ContextCompat.getDrawable(context, R.color.edit_text_bg_color));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return option;
    }
}
