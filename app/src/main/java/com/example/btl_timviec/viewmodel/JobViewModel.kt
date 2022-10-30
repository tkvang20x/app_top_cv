package com.example.btl_timviec.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.btl_timviec.Const
import com.example.btl_timviec.model.SearchParam
import com.example.btl_timviec.model.job.DataJob
import com.example.btl_timviec.model.user.DataUser
import com.example.socialapp.response.Api
import com.example.socialapp.response.ApiRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobViewModel: ViewModel() {

    private val api: Api
    var dataJob : MutableLiveData<DataJob?>
    var token: String? = Const.TOKEN
    init {
        api = ApiRetrofit.createRetrofit(Const.BASE_URL, Api::class.java) as Api
        dataJob = MutableLiveData<DataJob?>()
    }

     fun getListJob(searchParam: SearchParam,page: Int) {
         api.getListJob("Bearer $token", searchParam, page)
                 .enqueue(object : Callback<DataJob?> {
                     override fun onResponse(call: Call<DataJob?>, response: Response<DataJob?>) {
                         if (response.body() == null) {
                             dataJob.postValue(null)
                         } else {
                             dataJob.postValue(response.body())
                         }
                     }

                     override fun onFailure(call: Call<DataJob?>, t: Throwable) {
                         Log.d("aaa","Fail: ${t.message}")
                     }
                 })
         }
}