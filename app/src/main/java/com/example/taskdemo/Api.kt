package com.example.taskdemo

import com.example.taskdemo.data.ApiResult
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("/posts")
    suspend fun getApiResult() : Response<List<ApiResult>>
}