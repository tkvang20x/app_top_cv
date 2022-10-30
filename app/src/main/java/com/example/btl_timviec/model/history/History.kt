package com.example.btl_timviec.model.history

import com.example.btl_timviec.model.job.Investor

data class History(
    val _id: String,
    val type: String,
    val investor_id: String,
    val user_id: String,
    val job_id: String,
    val created_at: String,
    val updated_at: String,
    val __v: String,
    val investor: Investor,
    val job: JobHistory
) {
}