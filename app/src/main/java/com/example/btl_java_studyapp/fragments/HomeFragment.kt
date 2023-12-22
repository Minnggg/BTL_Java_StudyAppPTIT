package com.example.btl_java_studyapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.btl_java_studyapp.R
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btl_java_studyapp.api.ApiClient
import com.example.btl_java_studyapp.api.AppConfigToGetDiem
import com.example.btl_java_studyapp.api.apilichhoc.datalichhoc.DsThoiKhoaBieu
import com.example.btl_java_studyapp.api.apilichhoc.datalichhoc.ResponseLichHoc
import com.example.btl_java_studyapp.customadapter.RvHomeScheduleAdapter
import com.example.btl_java_studyapp.customadapter.RvScheduleAdapter
import com.example.btl_java_studyapp.databinding.FragmentHomeBinding
import com.example.testapi.jsonbody.Additional
import com.example.testapi.jsonbody.Filter
import com.example.testapi.jsonbody.Ordering
import com.example.testapi.jsonbody.Paging
import com.example.testapi.jsonbody.bodyy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        //circular Progress Bar

        val circularProgressBar = binding.circularProgressBar
        circularProgressBar.apply {
            setProgressWithAnimation(20f, 1000)
            progressMax = 100f
            // Set Width
            progressBarWidth = 10f
            backgroundProgressBarWidth = 10f
            // Other
            roundBorder = true
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }
        //Line chart

        val mySet = linkedMapOf("label1" to 4F, "label2" to 7F, "label3" to 2F)

        // lấy tên
        val context1 = this?.context
        var sharedPref : SharedPreferences = context1!!.getSharedPreferences("BTLJava", Context.MODE_PRIVATE)
        var editor : SharedPreferences.Editor = sharedPref.edit()
        val name = sharedPref.getString("name","")
        Log.d("TAG", "onCreateView: "+name)

        // Tìm TextView bằng ID
        val textView: TextView = binding.textView

        // Sửa giá trị của TextView
        textView.text = name

        // Schedule
        getSchedule()
        return binding.root
    }

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
        clientLichHoc.getTKB(body,"Bearer " + token).enqueue(object : Callback<ResponseLichHoc> {
            override fun onResponse(call: Call<ResponseLichHoc>, response: Response<ResponseLichHoc>) {
                Log.d("TAG", "apltkb1:\n"+ response.body()?.data?.dsTietTrongNgay.toString())
                val subjectList = ArrayList<DsThoiKhoaBieu>()
                val dsTuan = response.body()?.data?.dsTuanTkb
                val calendar = Calendar.getInstance()
                val today = calendar.get(Calendar.DAY_OF_MONTH).toString() +  (calendar.get(Calendar.MONTH) + 1).toString() +  calendar.get(Calendar.YEAR).toString();
                var selectedDay = today
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
                val rvScheduleAdapter = RvHomeScheduleAdapter(subjectList)
                binding.rvHomeSchedule.adapter = rvScheduleAdapter
                binding.rvHomeSchedule.layoutManager = LinearLayoutManager(
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
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}