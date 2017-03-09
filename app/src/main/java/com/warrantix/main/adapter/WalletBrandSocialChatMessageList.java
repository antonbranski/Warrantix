package com.warrantix.main.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.warrantix.main.R;
import com.warrantix.main.common.date.DateUtil;
import com.warrantix.main.common.rest.model.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WalletBrandSocialChatMessageList extends BaseAdapter {

    private final static String TAG = WalletBrandSocialChatMessageList.class.getSimpleName();

    private List<Message> chatMessages = null;
    private Activity context = null;

    public WalletBrandSocialChatMessageList(Activity context, List<Message> chatMessages) {
        this.context = context;
        this.chatMessages = chatMessages;
    }

    @Override
    public int getCount() {
        if (chatMessages != null) {
            return chatMessages.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (chatMessages != null) {
            return chatMessages.get(position);
        }
        else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView is called: position = " + position + " convertView = " + convertView + " parent = " + parent);

        ViewHolder holder;
        Message chatMessage = chatMessages.get(position);
//        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater vi = (LayoutInflater) context.getLayoutInflater();

        if (convertView == null) {
            convertView = vi.inflate(R.layout.listview_social_chat_message, parent, false);
            holder = createViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        boolean myMsg = false;
        if (chatMessages.get(position).getFrom().getId().equals("c0")){
             myMsg = true; //Just a dummy check to simulate whether it me or other sender
        }
        setAlignment(holder, myMsg);
        if (myMsg == true) {
            holder.txtMyMessage.setText(chatMessage.getContent());
            //String date = DateUtil.dateFarmatWithString(chatMessage.getCreatedAt());
            String date = chatMessage.getCreatedAt();
            holder.txtMyTimeInfo.setText(formatDate(date));
        }
        else {
            holder.txtMessage.setText(chatMessage.getContent());
            //String date = DateUtil.dateFarmatWithString(chatMessage.getCreatedAt());
            String date = chatMessage.getCreatedAt();
            holder.txtTimeInfo.setText(formatDate(date));
        }

        return convertView;
    }

    public void add(Message message) {
        if (chatMessages == null)
            chatMessages = new ArrayList<>();

        chatMessages.add(message);
        notifyDataSetChanged();
    }

    public void add(List<Message> messages) {
        chatMessages.addAll(messages);
        notifyDataSetChanged();
    }

    private void setAlignment(ViewHolder holder, boolean isMe) {
        if (isMe == true) {
            holder.contentWithBG.setVisibility(View.GONE);
            holder.txtTimeInfo.setVisibility(View.GONE);
            holder.myContentWithBG.setVisibility(View.VISIBLE);
            holder.txtMyTimeInfo.setVisibility(View.VISIBLE);
        }
        else {
            holder.myContentWithBG.setVisibility(View.GONE);
            holder.txtMyTimeInfo.setVisibility(View.GONE);
            holder.contentWithBG.setVisibility(View.VISIBLE);
            holder.txtTimeInfo.setVisibility(View.VISIBLE);
        }
    }

    private ViewHolder createViewHolder(View v) {
        ViewHolder holder = new ViewHolder();
        holder.txtMyMessage = (TextView) v.findViewById(R.id.mytxtMessage);
        holder.txtMessage = (TextView) v.findViewById(R.id.txtMessage);
        holder.txtTimeInfo = (TextView) v.findViewById(R.id.timeInfo);
        holder.txtMyTimeInfo = (TextView) v.findViewById(R.id.myTimeInfo);
        holder.myContentWithBG = (LinearLayout) v.findViewById(R.id.myContentWithBackground);
        holder.contentWithBG = (LinearLayout) v.findViewById(R.id.contentWithBackground);
        return holder;
    }


    private static class ViewHolder {
        public TextView txtMyMessage;
        public TextView txtMessage;
        public TextView txtTimeInfo;
        public TextView txtMyTimeInfo;
        public LinearLayout contentWithBG;
        public LinearLayout myContentWithBG;
    }

    private String formatDate(String date){

        String strInputDate = date;

        Date inputDate;
        String  currentStrDate= "";
        Date currentDate;


        currentDate = new Date();
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        SimpleDateFormat inputDateFormart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        try {
             inputDate = inputDateFormart.parse(strInputDate);
             DateUtil.Differnece(inputDate, currentDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        long dayLimite = 86400000;
        long hourLimite = 3600000;
        long minLimite = 60000;
        long secondLiminte = 2000;

        long different = DateUtil.getDiffernet();

        if (different > dayLimite) {
            try {
                currentStrDate = DateUtil.formatDate(date, inputDateFormart.toPattern(), dateFormat.toPattern());
            } catch (ParseException e){

            }
        } else {

            if (different > hourLimite) {
                if (DateUtil.getElapseHours() == 1)
                    currentStrDate = DateUtil.getElapseHours() + " hr ago";
                else
                    currentStrDate = DateUtil.getElapseHours() + " hrs ago";
            } else {
                if (different > minLimite) {
                    if (DateUtil.getElapseMinutes() == 1)
                        currentStrDate = DateUtil.getElapseMinutes() + " min ago";
                    else
                        currentStrDate = DateUtil.getElapseMinutes() + " mins ago";
                } else {
                    if (different < secondLiminte) {
                        currentStrDate = "now";
                    } else {
                        if (DateUtil.getElapseSeconds() == 1)
                            currentStrDate = DateUtil.getElapseSeconds() + " sec ago";
                        else
                            currentStrDate = DateUtil.getElapseSeconds() + " secs ago";
                    }
                }
            }

        }


        return currentStrDate;
    }


}
