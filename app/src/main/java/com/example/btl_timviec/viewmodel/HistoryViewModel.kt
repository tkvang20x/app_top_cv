package com.example.btl_timviec.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.btl_timviec.Const
import com.example.btl_timviec.model.history.Data
import com.example.socialapp.response.Api
import com.example.socialapp.response.ApiRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel : ViewModel() {

    private val api: Api
    var dataHistory : MutableLiveData<Data?>
    var token: String? = Const.TOKEN

    init {
        api = ApiRetrofit.createRetrofit(Const.BASE_URL, Api::class.java) as Api
        dataHistory = MutableLiveData<Data?>()
    }

    fun getHistory( page: Int) {
        api.getListHistory("Bearer $token", page)
            .enqueue(object : Callback<Data?> {
                override fun onResponse(call: Call<Data?>, response: Response<Data?>) {
                    if (response.body() == null) {
                        dataHistory.postValue(null)
                    } else {
                        dataHistory.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<Data?>, t: Throwable) {
                    Log.d("aaa","Fail: ${t.message}")
                }
            })
    }
}