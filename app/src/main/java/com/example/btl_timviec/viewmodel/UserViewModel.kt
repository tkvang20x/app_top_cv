package com.example.btl_timviec.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.btl_timviec.Const
import com.example.btl_timviec.model.register.GetRegister
import com.example.btl_timviec.model.user.AvatarUpload
import com.example.btl_timviec.model.user.DataUser
import com.example.btl_timviec.model.user.User
import com.example.socialapp.response.Api
import com.example.socialapp.response.ApiRetrofit
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val api: Api
    var data1: MutableLiveData<DataUser?>
    var data2: MutableLiveData<GetRegister?>
    var token: String? = Const.TOKEN
    var dataAvatar: MutableLiveData<AvatarUpload?>

    init {
        data1 = MutableLiveData<DataUser?>()
        data2 = MutableLiveData<GetRegister?>()
        dataAvatar = MutableLiveData<AvatarUpload?>()
        api = ApiRetrofit.createRetrofit(Const.BASE_URL, Api::class.java) as Api
    }
    fun getDataUser() {
        api.getUser("Bearer $token").enqueue(object : Callback<DataUser> {
            override fun onResponse(call: Call<DataUser>, response: Response<DataUser>) {
                if (response.body() == null ) {
                    data1.postValue(null)
                } else {
                    data1.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<DataUser>, t: Throwable) {
                Log.d("Error", "Fail: ${t.message}")
            }
        })
    }

    fun updateUser(userId: String?, dataUser: User?) {
        api.updateUser("Bearer $token", userId, dataUser).enqueue(object : Callback<GetRegister?>{
            override fun onResponse(call: Call<GetRegister?>, response: Response<GetRegister?>) {
                if (response.body() == null ) {
                    data2.postValue(null)
                } else {
                    data2.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GetRegister?>, t: Throwable) {
                Log.d("Error", "Fail: ${t.message}")
            }

        })
    }

    fun uploadAvatar(userId: String?, image: MultipartBody.Part?) {
        api.uploadAvatar("Bearer $token", userId, image)?.enqueue(object : Callback<AvatarUpload?> {
            override fun onResponse(call: Call<AvatarUpload?>, response: Response<AvatarUpload?>) {
                if (response.body() == null) {
                    dataAvatar.setValue(null)
                } else {
                    dataAvatar.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<AvatarUpload?>, t: Throwable) {
                Log.d("Error", "Fail: ${t.message}")
            }
        })
    }
}