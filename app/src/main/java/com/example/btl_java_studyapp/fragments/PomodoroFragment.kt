package com.example.btl_java_studyapp.fragments

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.btl_java_studyapp.R
import com.example.btl_java_studyapp.customadapter.SpinnerTaskAdapter
import com.example.btl_java_studyapp.databinding.FragmentPomodoroBinding
import com.example.btl_java_studyapp.model.TaskItem

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PomodoroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PomodoroFragment : Fragment() {
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

    private lateinit var binding: FragmentPomodoroBinding
    private lateinit var countdownTimer: CountDownTimer
    private var learnTime: Long = 1500000
    private var breakTime: Long = 300000
    private var timeLeftInMillis: Long = learnTime
    private var totalTime: Long = learnTime
    private var isLearn: Boolean = true
    private var isClockRunning: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPomodoroBinding.inflate(inflater)

        // Process Progress Bar
        binding.timerCircularProgressBar.apply {
            progressMax = 100f
        }

        binding.controlClockBtn.setOnClickListener {
            clockControl()
        }

        binding.nextClockBtn.setOnClickListener {
            nextClockStatus()
        }

        binding.refreshClockBtn.setOnClickListener {
            refreshClock()
        }

        updateCountdownText()
        updateTimerProgressBar(totalTime)


        var taskList = ArrayList<TaskItem>()
        val spinnerTaskAdapter = activity?.let { SpinnerTaskAdapter(it, taskList) }

        binding.spinnerTask.adapter = spinnerTaskAdapter

        binding.addTaskBtn.setOnClickListener {
            var taskName = binding.addTaskEditText.text.toString()
            if (spinnerTaskAdapter != null) {
                addTask(spinnerTaskAdapter, taskName)
            }
            binding.addTaskEditText.setText("")
        }

        binding.deleteTaskBtn.setOnClickListener {
            var position = binding.spinnerTask.selectedItemPosition
            if (spinnerTaskAdapter != null) {
                deleteTask(spinnerTaskAdapter, position)
            }
        }

        binding.checkTaskBtn.setOnClickListener {
            var position = binding.spinnerTask.selectedItemPosition
            if (spinnerTaskAdapter != null) {
                checkTask(spinnerTaskAdapter, position)
            }
        }

        binding.clearTaskBtn.setOnClickListener {
            if (spinnerTaskAdapter != null) {
                clearTask(spinnerTaskAdapter)
            }
            binding.addTaskEditText.setText("")
        }

        binding.backBtn.setOnClickListener{
            findNavController().navigate(R.id.action_pomodoroFragment_to_optionsFragment2)
        }

        return binding.root
    }

    // Task process

    private fun addTask(spinnerTaskAdapter: SpinnerTaskAdapter, taskName: String) {
        Log.e(TAG, "added Item", )
        spinnerTaskAdapter.add(taskName)
    }

    private fun deleteTask(spinnerTaskAdapter: SpinnerTaskAdapter, position: Int) {
        Log.e(TAG, "deleted Item", )
        spinnerTaskAdapter.delete(position)
    }

    private fun checkTask(spinnerTaskAdapter: SpinnerTaskAdapter, position: Int) {
        Log.e(TAG, "checked Item", )
        spinnerTaskAdapter.check(position)
    }

    private fun clearTask(spinnerTaskAdapter: SpinnerTaskAdapter) {
        Log.e(TAG, "cleared Item", )
        spinnerTaskAdapter.clear()
    }

    // Clock control

    private fun clockControl() {
        if (binding.controlClockBtn.paddingLeft != 0) {
            binding.controlClockBtn.setImageResource(R.drawable.pause_btn_img)
            binding.controlClockBtn.setPadding(0, 0, 0, 0)
            startCountdown()
        } else {
            binding.controlClockBtn.setImageResource(R.drawable.resume_btn_img)
            binding.controlClockBtn.setPadding(17, 0, 0, 0)
            countdownTimer.cancel()
        }
    }


    private fun updateCountdownText() {
        var minutes = (timeLeftInMillis / 1000 / 60).toString()
        var seconds = (timeLeftInMillis / 1000 % 60).toString()

        if (minutes.toInt() < 10) {
            minutes = "0$minutes"
        }
        if (seconds.toInt() < 10) {
            seconds = "0$seconds"
        }
        var formattedTime: String = "${minutes}:${seconds}"
        binding.clockTextview.text = formattedTime
    }

    private fun startCountdown() {
        countdownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                isClockRunning = true
                timeLeftInMillis = millisUntilFinished
                updateCountdownText()
                updateTimerProgressBar(totalTime)
            }

            override fun onFinish() {
                isClockRunning = false
                timeLeftInMillis = 0
                updateCountdownText()
                binding.controlClockBtn.setImageResource(R.drawable.resume_btn_img)
                binding.controlClockBtn.setPadding(17, 0, 0, 0)
                showEndTimeDialog()
                setClockTime()
                setNoticeText()
                updateTimerProgressBar(totalTime)
                setLearnStatus()
                Handler().postDelayed({
                    updateCountdownText()
                }, 1000)
            }
        }.start()
    }

    private fun updateTimerProgressBar(time: Long) {
        binding.timerCircularProgressBar.apply {
            val percents: Float = ((time - timeLeftInMillis) * 1.0 / time).toFloat() * 100;
            val durations = time / 10
            setProgressWithAnimation(percents * 1.25f, durations)
        }
    }

    private fun setClockTime() {
        if (isLearn) {
            totalTime = breakTime
            timeLeftInMillis = totalTime
        } else {
            totalTime = learnTime
            timeLeftInMillis = totalTime
        }
    }

    private fun setNoticeText() {
        if (isLearn) {
            binding.noticeTextView.text = "Take a break for 5 minutes"
        }
        else {
            binding.noticeTextView.text = "Stay focus for 25 minutes"
        }
    }

    private fun setLearnStatus() {
        isLearn = !isLearn
    }

    private fun nextClockStatus() {
        binding.controlClockBtn.setImageResource(R.drawable.resume_btn_img)
        binding.controlClockBtn.setPadding(17, 0, 0, 0)
        if(isClockRunning) {
            countdownTimer.cancel()
        }
        setClockTime()
        setNoticeText()
        updateTimerProgressBar(totalTime)
        updateCountdownText()
        setLearnStatus()
    }

    private fun refreshClock() {
            isLearn = true
            totalTime = learnTime
            timeLeftInMillis = learnTime
            binding.noticeTextView.text = "Stay focus for 25 minutes"
            binding.controlClockBtn.setImageResource(R.drawable.resume_btn_img)
            binding.controlClockBtn.setPadding(17, 0, 0, 0)
            if (isClockRunning) {
                countdownTimer.cancel()
            }
            updateCountdownText()
            updateTimerProgressBar(totalTime)
    }

    private fun showEndTimeDialog() {
        val dialogBuilder = AlertDialog.Builder(activity);
        var message: String = ""
        if (isLearn) {
            message = "Hãy nghỉ ngơi trong 5 phút bạn nhé!"
        }
        else {
            message = "Hãy tập trung làm việc trong 25 phút bạn nhé!"
        }
        dialogBuilder.setMessage(message)
        dialogBuilder.setCancelable(false)
        dialogBuilder.setPositiveButton("OK, let's go!") { _,_ ->
            Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PomodoroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PomodoroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}