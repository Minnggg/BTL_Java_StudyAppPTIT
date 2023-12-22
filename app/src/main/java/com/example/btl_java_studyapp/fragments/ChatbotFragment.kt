package com.example.btl_java_studyapp.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btl_java_studyapp.model.Message
import com.example.btl_java_studyapp.customadapter.MessageAdapter
import com.example.btl_java_studyapp.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class ChatbotFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var welcomeTextView: TextView
    private lateinit var messageEditText: EditText
    private lateinit var sendButton: ImageButton
    private val messageList = ArrayList<Message>()
    private lateinit var messageAdapter: MessageAdapter
    val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
    var client = OkHttpClient()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_chatbot, container, false)

        recyclerView = view.findViewById(R.id.recycler_view);
        messageEditText = view.findViewById(R.id.message_edit_text);
        sendButton = view.findViewById(R.id.send_btn);

        //setup recycler view
        messageAdapter = MessageAdapter(messageList);
        recyclerView.adapter = messageAdapter;
        val llm = LinearLayoutManager(activity)
        llm.stackFromEnd = true
        recyclerView.layoutManager = llm

        addToChat("Xin chào, bạn hãy đặt câu hỏi, tôi sẽ trả lời thắc mắc đó của bạn ngay nhé!",
            Message.SENT_BY_BOT
        )

        sendButton.setOnClickListener {
            val question = messageEditText.text.toString().trim()
            if (question.isNotEmpty()) {
                addToChat(question, Message.SENT_BY_ME);
                messageEditText.setText("");
                callAPI(question);
            }
        }

        return view
    }

    fun addToChat(message: String, sentBy: String) {
        Handler(Looper.getMainLooper()).post {
            messageList.add(Message(message, sentBy))
            for (msg in messageList) {
                Log.d("TAG", "Message: ${msg.message}, Sent By: ${msg.sentBy}")
            }
            messageAdapter.notifyDataSetChanged()
            recyclerView.smoothScrollToPosition(messageAdapter.itemCount)
        }
    }

    fun addResponse(response: String?) {
        messageList.removeAt(messageList.size - 1)
        addToChat(response!!, Message.SENT_BY_BOT)
    }

    fun callAPI(question: String) {
        // OkHttp
        addToChat("Typing...", Message.SENT_BY_BOT)

        val jsonBody = JSONObject()
        try {
            jsonBody.put("model", "text-davinci-003")
            jsonBody.put("prompt", question)
            jsonBody.put("max_tokens", 4000)
            jsonBody.put("temperature", 0)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val body = jsonBody.toString().toRequestBody(JSON)
        val request = Request.Builder()
            .url("https://api.openai.com/v1/completions")
            .header("Authorization", "Bearer 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")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                addResponse("Không thể tải phản hồi do ${e.message}")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    var jsonObject: JSONObject? = null
                    try {
                        jsonObject = JSONObject(response.body!!.string())
                        val jsonArray = jsonObject.getJSONArray("choices")
                        val result = jsonArray.getJSONObject(0).getString("text")
                        addResponse(result.trim())
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } else {
                    addResponse("Không thể tải phản hồi do ${response.body.toString()}")
                }
            }
        })
    }
}