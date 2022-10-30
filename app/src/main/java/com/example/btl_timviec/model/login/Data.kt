package com.example.btl_timviec.model.login

import com.example.btl_timviec.model.user.User

data class Data(
    val access_token: String,
    val expires_in: Int,
    val user: User
)