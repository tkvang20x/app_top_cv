package com.example.socialapp.response


import com.example.btl_timviec.model.SearchParam
import com.example.btl_timviec.model.job.DataJob
import com.example.btl_timviec.model.history.Data
import com.example.btl_timviec.model.login.DataLogin
import com.example.btl_timviec.model.login.Login
import com.example.btl_timviec.model.register.DataRegister
import com.example.btl_timviec.model.register.GetRegister
import com.example.btl_timviec.model.user.AvatarUpload
import com.example.btl_timviec.model.user.DataUser
import com.example.btl_timviec.model.user.User
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @POST("/auth/login")
    fun postLogin(@Body login: Login) : Call<DataLogin>

    @POST("/auth/register")
    fun postRegister(@Body register: DataRegister) : Call<GetRegister>

    @GET("/v1/users/me")
    fun getUser(@Header("Authorization") token: String?) : Call<DataUser>

    @POST("/v1/users/update/{id}")
    fun updateUser(@Header("Authorization") token: String?,@Path("id") userId: String?, @Body dataUser: User?) : Call<GetRegister>

    @Multipart
    @POST("/v1/upload/avatar/{id}")
    fun uploadAvatar(
        @Header("Authorization") token: String?,
        @Path("id") userId: String?,
        @Part image: MultipartBody.Part?
    ): Call<AvatarUpload?>?

    @POST("/v1/jobs")
    fun getListJob(@Header("Authorization") token: String?,@Body pagination: SearchParam?, @Query("page") page: Int) : Call<DataJob>


    @GET("/v1/history")
    fun getListHistory(@Header("Authorization") token: String?, @Query("page") page: Int) : Call<Data>

}