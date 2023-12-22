package com.example.btl_java_studyapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.example.btl_java_studyapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OptionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OptionsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_options, container, false)

        val chatbotBtn = view.findViewById<AppCompatButton>(R.id.ChatBotBut)

        val pomodoroBtn = view.findViewById<AppCompatButton>(R.id.PomodoroBut)

        val scoreTableBtn = view.findViewById<AppCompatButton>(R.id.ScoreTableBut)

        val daily = view.findViewById<AppCompatButton>(R.id.DailyAimBut)


        chatbotBtn.setOnClickListener{
            findNavController().navigate(R.id.action_optionsFragment_to_chatbotActivity)
        }
        pomodoroBtn.setOnClickListener{
            findNavController().navigate(R.id.action_optionsFragment_to_pomodoroFragment2)
        }
        scoreTableBtn.setOnClickListener {
            findNavController().navigate(R.id.action_optionsFragment_to_scoreTableActivity)
        }
        val context1 = this?.context

        daily.setOnClickListener(View.OnClickListener {
            Toast.makeText(context1, "Tính năng đang được phát triển", Toast.LENGTH_SHORT).show()

        })



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OptionsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OptionsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}