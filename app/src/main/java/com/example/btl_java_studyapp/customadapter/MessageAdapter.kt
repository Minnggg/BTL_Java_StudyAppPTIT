package com.example.btl_java_studyapp.customadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_java_studyapp.R
import com.example.btl_java_studyapp.model.Message

class MessageAdapter(private val messageList: List<Message>) : RecyclerView.Adapter<MessageAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val chatView: View = LayoutInflater.from(parent.context).inflate(R.layout.chat_item, null)
        return MyViewHolder(chatView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val message: Message = messageList[position]
        if (message.sentBy == Message.SENT_BY_ME) {
            holder.leftChatView.setVisibility(View.GONE)
            holder.rightChatView.setVisibility(View.VISIBLE)
            holder.rightTextView.setText(message.message)
        } else {
            holder.rightChatView.setVisibility(View.GONE)
            holder.leftChatView.setVisibility(View.VISIBLE)
            holder.leftTextView.setText(message.message)
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var leftChatView: LinearLayout
        var rightChatView: LinearLayout
        var leftTextView: TextView
        var rightTextView: TextView

        init {
            leftChatView = itemView.findViewById(R.id.left_chat_view)
            rightChatView = itemView.findViewById(R.id.right_chat_view)
            leftTextView = itemView.findViewById(R.id.left_chat_text_view)
            rightTextView = itemView.findViewById(R.id.right_chat_text_view)
        }
    }
}