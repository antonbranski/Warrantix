package com.warrantix.main.activities.social;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.WalletBrandSocialSelectContactList;

public class WalletBrandSocialSelectContact extends BaseActivity {

    final String[] name = { "Srinivasan M.K.", "Pankaj Vadhel", "Seetaraman Narayanan","Hardik Sindha", "Vishal Chauhan"};
    final String[] type = { "Hero Splendor", "Karizma ZMR", "Hero CBZ","Hero Splendor", "Hero Passion"};
    final String[] image = {Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.person1).toString(), Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.person2).toString(),
            Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.person3).toString(),Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.person1).toString(),
            Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.person1).toString(),Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.person3).toString()};


    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_select_contact);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }

        if (list != null)
            list.setOnItemClickListener(listviewClickListener);
    }

    public void Initialize() {
        list = (ListView) findViewById(R.id.list);

        WalletBrandSocialSelectContactList listAdapter = new WalletBrandSocialSelectContactList(this, name, type);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(listviewClickListener);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }

    private final AdapterView.OnItemClickListener listviewClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent mIntent = new Intent(WalletBrandSocialSelectContact.this, WalletBrandSocialChatMessage.class);
            mIntent.putExtra("title", "NEW CHAT");
            mIntent.putExtra("name", name[position]);
            mIntent.putExtra("type", type[position]);
            mIntent.putExtra("imageThumb", image[position]);
            startActivity(mIntent, true);

            list.setOnItemClickListener(null);
        }
    };
}