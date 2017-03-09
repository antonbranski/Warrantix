package com.warrantix.main.adapter;

import android.app.Activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.warrantix.main.R;
import com.squareup.picasso.Picasso;
import com.warrantix.main.WarrantixApplication;
import com.warrantix.main.common.image.PicassoProvider;

import java.util.ArrayList;
import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecipientListAdapter extends ArrayAdapter<RecipientListAdapter.Recipient> implements AdapterView.OnItemClickListener {

    public static class Recipient {
        public String name;
        public int imageResource;   // temporary

        public Recipient() {

        }
    }

    private final Activity context;
    private ArrayList<Recipient> recipients;
    private OnRecipientItemSelectListener itemSelectListener = null;
    private ViewGroup parentListView = null;

    public RecipientListAdapter(Activity context, Recipient[] recipients) {
        super(context, R.layout.listview_recipient, recipients);

        this.context = context;
        if (recipients == null)
            this.recipients = new ArrayList<>();
        else {
            this.recipients = new ArrayList<>();
            for (int i=0; i<recipients.length; i++)
                this.recipients.add(recipients[i]);
        }
    }

    public void setRecipientItemSelectListener(OnRecipientItemSelectListener listener) {
        this.itemSelectListener = listener;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView;

        if ((recipients != null) && (position < recipients.size())) {
            rowView = inflater.inflate(R.layout.listview_recipient, null, true);
            TextView nameText = (TextView) rowView.findViewById(R.id.profileNameView);
            nameText.setText(recipients.get(position).name);

            ImageView profileImageView = (ImageView) rowView.findViewById(R.id.profileImageView);
            PicassoProvider.getInstance().get()
                    .load(recipients.get(position).imageResource)
                    .error(R.drawable.image_holder)
                    .placeholder(R.drawable.image_holder)
                    .into(profileImageView);
        }
        else {
            rowView = new View(context);
        }

        parentListView = parent;
        return rowView;
    }

    public void addRecipient(Recipient recipient) {
        if (this.recipients == null)
            this.recipients = new ArrayList<>();

        this.recipients.add(recipient);

        // adjust list view height
        setListViewHeightBasedOnItems();
     }

    public void removeRecipient(int position) {
        this.recipients.remove(position);

        // adjust list view height
        setListViewHeightBasedOnItems();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (recipients == null)
            return;

        if (position >= recipients.size())
            return;

        Recipient recipient = recipients.get(position);
        removeRecipient(position);
        RecipientListAdapter.this.notifyDataSetChanged();

        if (itemSelectListener != null)
            itemSelectListener.OnRecipientItemSelected(recipient);
    }

    /**
     * Sets ListView height dynamically based on the height of the items.
     */
    public void setListViewHeightBasedOnItems() {

        if (parentListView == null)
            return;

        Log.v("RecipientListAdapter", "setListViewHeightBasedOnItems() is called");

        if (parentListView instanceof ListView) {

            int numberOfItems = this.recipients.size();
            if (numberOfItems >= 3)
                numberOfItems = 3;

            // Get total height of all items.
            int totalItemsHeight = 0;
//            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
//                View item = getView(itemPos, null, parentListView);
//                item.measure(0, 0);
//                totalItemsHeight += item.getMeasuredHeight();
//            }
            int dp = (int) parentListView.getContext().getResources().getDimension(R.dimen._30sdp);
            totalItemsHeight = dp * numberOfItems;

            // Get total height of all item dividers.
            int totalDividersHeight = ((ListView)parentListView).getDividerHeight() * (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = parentListView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            parentListView.setLayoutParams(params);
            parentListView.requestLayout();
        }
    }

    //
    // public interface
    //
    public interface OnRecipientItemSelectListener {
        public void OnRecipientItemSelected(Recipient recipient);
    }
}