package com.warrantix.main.activities.social;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.warrantix.main.R;
import com.warrantix.main.activities.BaseActivity;
import com.warrantix.main.adapter.WalletBrandSocialChatMessageList;
import com.warrantix.main.common.event.ChatMessagesSuccessEvent;
import com.warrantix.main.common.image.PicassoProvider;
import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.model.RoleInfo;
import com.warrantix.main.common.rest.request.ChatMessageRequest;
import com.warrantix.main.common.rest.response.PullMessageResponse;
import com.warrantix.main.manager.BackendManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WalletBrandSocialChatMessage extends BaseActivity {

    ListView list;
    WalletBrandSocialChatMessageList listAdapter;
    String customerID = "";

    int hour_init;
    int minute_init;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_chat_message);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isInitialized == false) {
            Initialize();
            isInitialized = true;
        }
    }

    public void Initialize() {
        list = (ListView) findViewById(R.id.list);

        String title = getIntent().getStringExtra("title");
        if (title != null) {
            TextView titleView = (TextView) findViewById(R.id.title);
            titleView.setText(title);
        }

        Calendar c = Calendar.getInstance();
        hour_init = c.get(Calendar.HOUR);
        minute_init = c.get(Calendar.MINUTE);


        String imageThumb = getIntent().getStringExtra("imageThumb");
        String stringName = getIntent().getStringExtra("name");
        String stringType = getIntent().getStringExtra("type");
        customerID = getIntent().getStringExtra("customerID");

        ImageView imageView = (ImageView) findViewById(R.id.customer_image);

        PicassoProvider.getInstance().get()
                .load(imageThumb)
                .error(R.drawable.image_holder)
                .placeholder(R.drawable.image_holder)
                .into(imageView);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(stringName);

        Boolean bLoadMessage = getIntent().getBooleanExtra("loadMessage", false);

        if (bLoadMessage == true) {

            // Load previous messages
            ChatMessageRequest chatMessageRequest = new ChatMessageRequest();
            chatMessageRequest.setType("chat");
            RoleInfo to = new RoleInfo();
            to.setRole("customer");
            to.setId("c0");
            chatMessageRequest.setTo(to);
            RoleInfo from = new RoleInfo();
            from.setRole("customer");
            from.setId(customerID);
            chatMessageRequest.setFrom(from);

            BackendManager.getInstance().getChatMessagesResponse(chatMessageRequest);

        } else {

            // new blank chat screen
            listAdapter = new WalletBrandSocialChatMessageList(this, null);
            list.setAdapter(listAdapter);
        }

        final EditText txtSendMessage = (EditText) findViewById(R.id.txtSendMessage);

        ImageButton btnBack = (ImageButton) findViewById(R.id.brand_arrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(true);
            }
        });

        ImageButton btnSend = (ImageButton) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sendText = txtSendMessage.getText().toString();
                sendText = sendText.trim();
                if (sendText.equalsIgnoreCase("") == true)
                    return;

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strCurrentDate = "";

                Date currentDate = new Date();

                strCurrentDate = mdformat.format(currentDate);

                Message newMessage = new Message();
                newMessage.setId("");
                newMessage.setType("chat");
                newMessage.setUpdatedAt(strCurrentDate);
                newMessage.setContent(txtSendMessage.getText().toString());
                    RoleInfo from = new RoleInfo();
                    from.setId("c0");
                    from.setRole("customer");
                newMessage.setFrom(from);
                    RoleInfo to = new RoleInfo();
                    to.setId(customerID);
                    to.setRole("customer");
                newMessage.setCreatedAt(strCurrentDate);
                listAdapter.add(newMessage);

                txtSendMessage.setText("");
                list.smoothScrollToPosition(listAdapter.getCount());
            }
        });

    }

//    public ArrayList<ChatMessage> loadMessages() {
//        ArrayList<ChatMessage> messages = new ArrayList<>();
//        ChatMessage newMessage = new ChatMessage();
//        newMessage.id = 0;
//        newMessage.isMe = false;
//        newMessage.username = "Srinivasan M.K";
//        newMessage.message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's dummy text ever since the 1500s";
//        newMessage.dateTime = "32 minutes ago";
//        messages.add(newMessage);
//
//        ChatMessage newMessage1 = new ChatMessage();
//        newMessage1.id = 1;
//        newMessage1.isMe = true;
//        newMessage1.username = "Srinivasan M.K";
//        newMessage1.message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's dummy text ever since the 1500s";
//        newMessage1.dateTime = "22 minutes ago";
//        messages.add(newMessage1);
//
//        ChatMessage newMessage2 = new ChatMessage();
//        newMessage2.id = 2;
//        newMessage2.isMe = false;
//        newMessage2.username = "Srinivasan M.K";
//        newMessage2.message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's dummy text ever since the 1500s";
//        newMessage2.dateTime = "12 minutes ago";
//        messages.add(newMessage2);
//        return messages;
//    }

    @Subscribe
    public void onChatMessagesSuccessEvent(final ChatMessagesSuccessEvent event)
    {
        WalletBrandSocialChatMessage.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PullMessageResponse pullMessageResponse = event.getPullMessageResponse();
                if (pullMessageResponse != null){
                    List<Message> messages = pullMessageResponse.getMessages();
                    if (messages != null) {

                        listAdapter = new WalletBrandSocialChatMessageList(WalletBrandSocialChatMessage.this, messages);
                        list.setAdapter(listAdapter);
                    }
                }
            }
        });
    }

}