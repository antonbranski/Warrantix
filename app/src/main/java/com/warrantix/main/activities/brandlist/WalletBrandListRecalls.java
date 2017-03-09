package com.warrantix.main.activities.brandlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.WalletBrandListManualsList;
import com.warrantix.main.adapter.WalletBrandListRecallsList;
import com.warrantix.main.common.rest.WarrantixWebService;
import com.warrantix.main.common.rest.model.RoleInfo;
import com.warrantix.main.common.rest.request.PullMessageRequest;
import com.warrantix.main.common.rest.response.PullMessageResponse;


/**
 * Created by MyUserName on 2/26/2016.
 */
public class WalletBrandListRecalls extends BaseActivity {
    ListView list;

    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list_recalls);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize(){

        list=(ListView)findViewById(R.id.list);
        list.setAdapter(null);

        PullMessageRequest reminderPullMessageRequest = new PullMessageRequest();
        reminderPullMessageRequest.setType("reminder");

        RoleInfo to = new RoleInfo();
        to.setId("c1");
        to.setRole("customer");
        reminderPullMessageRequest.setTo(to);

//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
//        String strDate = "Current Date : " + mdformat.format(calendar.getTime());
        reminderPullMessageRequest.setSince("2016-06-01");

        PullMessageResponse getReminderMessages = WarrantixWebService.getInstance().getMessages(reminderPullMessageRequest.getType());

        if (getReminderMessages != null ){
            WalletBrandListRecallsList adapter = new WalletBrandListRecallsList(this, getReminderMessages.getMessages());
            list.setAdapter(adapter);
        }

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });
    }
}
