package com.example.btl_java_studyapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btl_java_studyapp.api.ApiClient
import com.example.btl_java_studyapp.api.AppConfigToGetDiem
import com.example.btl_java_studyapp.api.apilichhoc.datalichhoc.DsThoiKhoaBieu
import com.example.btl_java_studyapp.api.apilichhoc.datalichhoc.ResponseLichHoc
import com.example.btl_java_studyapp.customadapter.CalendarAdapter
import com.example.btl_java_studyapp.customadapter.RvScheduleAdapter
import com.example.btl_java_studyapp.databinding.FragmentScheduleBinding
import com.example.btl_java_studyapp.model.CalendarDateModel
import com.example.testapi.jsonbody.Additional
import com.example.testapi.jsonbody.Filter
import com.example.testapi.jsonbody.Ordering
import com.example.testapi.jsonbody.Paging
import com.example.testapi.jsonbody.bodyy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScheduleFragment : Fragment(), CalendarAdapter.onItemClickListener {

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

    private val sdf = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    private val cal = Calendar.getInstance(Locale.ENGLISH)
    private val currentDate = Calendar.getInstance(Locale.ENGLISH)
    private val dates = ArrayList<Date>()
    private lateinit var adapter: CalendarAdapter
    private val calendarList2 = ArrayList<CalendarDateModel>()

    private lateinit var binding: FragmentScheduleBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleBinding.inflate(layoutInflater)

        // Calendar
        setUpAdapter()
        setUpClickListener()
        setUpCalendar()

        // Schedule
        val calendar = Calendar.getInstance()
        Log.e("TAG", "onCreateView: $calendar", )

        val current = calendar.get(Calendar.DAY_OF_MONTH).toString() + "/" +  (calendar.get(Calendar.MONTH) + 1).toString() + "/" +  calendar.get(Calendar.YEAR).toString();
        binding.selectedDate.text = current
        getSchedule()
        Log.d("TAG", "onNow: $current ")
        return binding.root

    }

    override fun onItemClick(text: String, date: String, day: String) {
        var selectedDay = ""
        for(i in text) {
            if (i.isDigit()) {
                selectedDay += i
            }
            else if (i == ' ' && selectedDay[selectedDay.length - 1] != '/') {
                selectedDay += "/"
            }
        }
        binding.selectedDate.text = selectedDay
        getSchedule()
    }
    /**
     * Set up click listener
     */

    private fun setUpClickListener() {
        binding.ivCalendarNext.setOnClickListener {
            cal.add(Calendar.MONTH, 1)
            setUpCalendar()
        }
        binding.ivCalendarPrevious.setOnClickListener {
            cal.add(Calendar.MONTH, -1)
            if (cal == currentDate)
                setUpCalendar()
            else
                setUpCalendar()
        }
    }


    /**
     * Setting up adapter for recyclerview
     */
    private fun setUpAdapter() {
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewCalendar)
        adapter = CalendarAdapter { calendarDateModel: CalendarDateModel, position: Int ->
            calendarList2.forEachIndexed { index, calendarModel ->
                calendarModel.isSelected = index == position
            }
            adapter.setData(calendarList2)
            adapter.setOnItemClickListener(this@ScheduleFragment)
        }
        binding.recyclerViewCalendar.adapter = adapter
    }

    /**
     * Function to setup calendar for every month
     */
    private fun setUpCalendar() {
        val calendarList = ArrayList<CalendarDateModel>()
        binding.textDateMonth.text = sdf.format(cal.time)
        val monthCalendar = cal.clone() as Calendar
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        dates.clear()
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        while (dates.size < maxDaysInMonth) {
            dates.add(monthCalendar.time)
            calendarList.add(CalendarDateModel(monthCalendar.time))
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        calendarList2.clear()
        calendarList2.addAll(calendarList)
        adapter.setOnItemClickListener(this@ScheduleFragment)
        adapter.setData(calendarList)
    }


    // Schedule API process
    fun getSchedule() {
        //lấy token
        var context: Context = requireActivity().applicationContext
        var sharedPref: SharedPreferences =
            context.getSharedPreferences("BTLJava", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPref.edit()
        var token: String? = sharedPref.getString("token", "")

        //lấy api lịch học
        var clientLichHoc: ApiClient = AppConfigToGetDiem.retrofit1.create(ApiClient::class.java)
        //tao requestbody
        val filter = Filter("20231","")
        val paging= Paging("100","1")
        val ordering = Ordering(null,null)
        val additional = Additional(listOf<Ordering>(ordering),paging)
        val body = bodyy(filter,additional)
        clientLichHoc.getTKB(body,"Bearer "+token).enqueue(object : Callback<ResponseLichHoc> {
            override fun onResponse(call: Call<ResponseLichHoc>, response: Response<ResponseLichHoc>) {
                Log.d("TAG", "apltkb1:\n"+ response.body()?.data?.dsTietTrongNgay.toString())
                val subjectList = ArrayList<DsThoiKhoaBieu>()
                val dsTuan = response.body()?.data?.dsTuanTkb
                val tmp = binding.selectedDate.text.toString()
                var selectedDay = ""
                for(i in tmp) {
                    if (i.isDigit()) {
                        selectedDay += i
                    }
                }
                if (dsTuan != null) {
                    for(i in dsTuan) {
                        for(j in i.dsThoiKhoaBieu) {
                            var tmp = ""
                            for(k in j.ngayHoc) {
                                if (k == 'T') {
                                    break
                                }
                                tmp += k
                            }
                            val tmp1 = tmp.split("-").toTypedArray()
                            var learnDay = ""
                            for (k in tmp1.size - 1 downTo 0) {
                                learnDay += tmp1[k]
                            }
                            Log.e("TAG", "compare:$selectedDay : $learnDay", )
                            if (selectedDay.equals(learnDay)) {
                                subjectList.add(j)
                                Log.e("TAG", "add subject: success", )
                            }
                        }
                    }
                }
                val rvScheduleAdapter = RvScheduleAdapter(subjectList)
                binding.recyclerViewSchedule.adapter = rvScheduleAdapter
                binding.recyclerViewSchedule.layoutManager = LinearLayoutManager(
                    activity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
            override fun onFailure(call: Call<ResponseLichHoc>, t: Throwable) {
                Log.d("TAG", "apitkb 3")
            }
        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScheduleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}