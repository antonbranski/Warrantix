package com.warrantix.main.adapter;

import android.app.Activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.model.ReminderMessageContent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WalletBrandListRecallsList extends BaseAdapter {
    private static final String TAG = WalletBrandListRecallsList.class.getSimpleName();

    private final Activity context;
    private List<Message> mMessages = new ArrayList<>();

    public WalletBrandListRecallsList(Activity context, List<Message> messages) {
        this.context = context;
        this.mMessages = messages;
    }

    @Override
    public int getCount() {
        return mMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return mMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if (view == null)
            rowView = inflater.inflate(R.layout.activity_brand_list_recalls_list, null, true);
        else
            rowView = view;

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        TextView nameText1 = (TextView) rowView.findViewById(R.id.name1);
        TextView nameText2 = (TextView) rowView.findViewById(R.id.name2);
        TextView daysText = (TextView) rowView.findViewById(R.id.days);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.products_image);


        ReminderMessageContent reminderMessageContent = parseFromJson(mMessages.get(position).getContent());

        nameText.setText(reminderMessageContent.getName());
        nameText1.setText(reminderMessageContent.getSub());
        nameText2.setText(reminderMessageContent.getDescription());
        daysText.setText(mMessages.get(position).getUpdatedAt());


        PicassoProvider.getInstance().get()
                .load(reminderMessageContent.getImageThumb())
                .error(R.drawable.image_holder)
                .placeholder(R.drawable.image_holder)
                .into(imageView);


        return rowView;
    }

    public ReminderMessageContent parseFromJson(String content){
        ReminderMessageContent reminderMessageContent = new ReminderMessageContent();

        if (content != null) {
            try {
                JSONObject json = new JSONObject(content);

                String description = json.getString("description");
                String name = json.getString("name");
                String sub = json.getString("sub");
                String imageThumb = json.getString("imageThumb");

                reminderMessageContent.setDescription(description);
                reminderMessageContent.setName(name);
                reminderMessageContent.setSub(sub);
                reminderMessageContent.setImageThumb(imageThumb);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "Couldn't get any data from the url");
        }

        return reminderMessageContent;
    }


}