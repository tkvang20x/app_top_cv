package com.example.btl_timviec.model.job

data class DataJob(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)