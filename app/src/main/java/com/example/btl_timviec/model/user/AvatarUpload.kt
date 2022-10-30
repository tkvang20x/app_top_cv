package com.example.btl_timviec.model.user

data class AvatarUpload(
    val success:String,
    val message:String,
    val dataAvatar: DataAvatar,
    val statuscode:Int
) {
}