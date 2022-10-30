package com.example.btl_timviec.model.job

import java.io.Serializable

data class Job(
    val __v: Int,
    val _id: String,
    val address: String,
    val age: Int,
    val company_name: String,
    val created_at: String,
    val investor: Investor,
    val investor_id: String,
    val level: String,
    val other: String,
    val salary: String,
    val skill: String,
    val updated_at: String,
    val work: String
): Serializable