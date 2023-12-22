package com.example.btl_java_studyapp.model

class TaskItem {
    private var isDone: Boolean = false
    private var taskName: String = ""

    constructor(taskName: String) {
        this.taskName = taskName
    }

    var IsDone: Boolean
        get() {
            return this.isDone
        }
        set(value) {
            this.isDone = value
        }

    var TaskName: String
        get() {
            return this.taskName
        }
        set(value) {
            this.taskName = value
        }


}