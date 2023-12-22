package com.example.btl_java_studyapp.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.btl_java_studyapp.R
import com.example.btl_java_studyapp.activities.LogInActivity
import com.example.btl_java_studyapp.databinding.ActivityMainBinding
import com.example.btl_java_studyapp.databinding.FragmentAccountBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
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
        binding = FragmentAccountBinding.inflate(inflater, container, false)
//        inflater.inflate(R.layout.fragment_account, container, false)
        // set giá trị tên và mã sv
        val context1 = this?.context
        var sharedPref : SharedPreferences = context1!!.getSharedPreferences("BTLJava", Context.MODE_PRIVATE)
        val name = sharedPref.getString("name","")
        binding.tvName.text = name
        binding.tvMasv.text = sharedPref.getString("masv","")
        //xu ly logic 4 thang dau
        binding.thongtin.setOnClickListener(View.OnClickListener {
            Toast.makeText(context1, "Tính năng đang được phát triển", Toast.LENGTH_SHORT).show()
        })
        binding.caidat.setOnClickListener(View.OnClickListener {
            Toast.makeText(context1, "Tính năng đang được phát triển", Toast.LENGTH_SHORT).show()
        })
        binding.baomat.setOnClickListener(View.OnClickListener {
            Toast.makeText(context1, "Tính năng đang được phát triển", Toast.LENGTH_SHORT).show()
        })
        binding.help.setOnClickListener(View.OnClickListener {
            Toast.makeText(context1, "Tính năng đang được phát triển", Toast.LENGTH_SHORT).show()
        })
        // xu ly logic dangxuat
        binding.dangxuat.setOnClickListener(View.OnClickListener {
            val intent = Intent(context1,LogInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity?.finish() // Kết thúc Fragment hiện tại sau khi chuyển sang Activity mới
            Toast.makeText(context1, "Đăng xuất thành công", Toast.LENGTH_SHORT).show()
        })
        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AcountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}