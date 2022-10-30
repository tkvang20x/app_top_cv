package com.example.btl_timviec.callback

import com.example.btl_timviec.model.job.Job


interface CallBack {
    fun onClick(job: Job)

    fun onLongClick(job: Job)
}