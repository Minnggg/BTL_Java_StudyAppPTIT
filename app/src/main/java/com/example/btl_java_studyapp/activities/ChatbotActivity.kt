package com.example.btl_java_studyapp.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btl_java_studyapp.model.Message
import com.example.btl_java_studyapp.customadapter.MessageAdapter
import com.example.btl_java_studyapp.databinding.ActivityChatbotBinding
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

class ChatbotActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityChatbotBinding

    private val messageList = ArrayList<Message>()
    private lateinit var messageAdapter: MessageAdapter
    val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
    var client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val messageEditText = binding.messageEditText;
        messageAdapter = MessageAdapter(messageList);
        binding.recyclerView.adapter = messageAdapter;

        val llm = LinearLayoutManager(this)
        llm.stackFromEnd = true
        binding.recyclerView.layoutManager = llm

        addToChat("Xin chào, bạn hãy đặt câu hỏi, tôi sẽ trả lời thắc mắc đó của bạn ngay nhé!", Message.SENT_BY_BOT)

        binding.sendBtn.setOnClickListener {
            val question = messageEditText.text.toString().trim()
            if (question.isNotEmpty()) {
                addToChat(question, Message.SENT_BY_ME);
                messageEditText.setText("");
                callAPI(question);
            }
        }

    }

    fun addToChat(message: String, sentBy: String) {
        Handler(Looper.getMainLooper()).post {
            messageList.add(Message(message, sentBy))
            for (msg in messageList) {
                Log.d("TAG", "Message: ${msg.message}, Sent By: ${msg.sentBy}")
            }
            messageAdapter.notifyDataSetChanged()
            binding.recyclerView.smoothScrollToPosition(messageAdapter.itemCount)  // Fix: Use binding.recyclerView
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
            jsonBody.put("model", "gpt-3.5-turbo-instruct")
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