package com.example.btl_java_studyapp.customadapter

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt
import com.example.btl_java_studyapp.R
import com.example.btl_java_studyapp.model.TaskItem

class SpinnerTaskAdapter(val activity: Activity, val taskList: ArrayList<TaskItem>): ArrayAdapter<TaskItem>(activity, R.layout.spinner_task_item) {
    fun add(taskName: String) {
        if (taskName.isNotEmpty()) {
            taskList.add(TaskItem(taskName))
            notifyDataSetChanged()
            Log.e(TAG, "added", )
        }
    }

    fun delete(position: Int) {
        if (taskList.isNotEmpty()) {
            taskList.removeAt(position)
            notifyDataSetChanged()
            Log.e(TAG, "deleted", )
        }
    }

    fun check(position: Int) {
        if (taskList.isNotEmpty()) {
            taskList[position].IsDone = true
            notifyDataSetChanged()
            Log.e(TAG, "done", )
        }
    }

    override fun clear() {
        taskList.clear()
        notifyDataSetChanged()
    }

    private fun getDoneTask(): String {
        var cnt: Int = 0
        for (i in taskList) {
            if (i.IsDone) {
                cnt++
            }
        }
        return cnt.toString()
    }

    private fun sortTaskList() {
        taskList.sortBy { it.IsDone }
    }

    override fun getCount(): Int {
        return taskList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = activity.layoutInflater.inflate(R.layout.spinner_task_item, parent,false)

        val taskName = view.findViewById<TextView>(R.id.taskName)
        val taskIndex = view.findViewById<TextView>(R.id.taskIndex)
        val cardViewTaskItem = view.findViewById<CardView>(R.id.cardViewTaskItem)

        taskName.text = taskList[position].TaskName
        taskIndex.text = getDoneTask() + "/" + taskList.size

        if(taskList[position].IsDone) {
            cardViewTaskItem.setCardBackgroundColor("#92f0c1".toColorInt())
        }

        return view
    }
}