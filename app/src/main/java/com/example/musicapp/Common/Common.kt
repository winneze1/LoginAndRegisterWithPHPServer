package com.example.musicapp.Common

import com.example.musicapp.Retrofit.IMyAPI
import com.example.musicapp.Retrofit.RetrofitClient

object Common {
    val BASE_URL = "http://10.0.2.2/myapi/"

    val api : IMyAPI
    get() = RetrofitClient.getClient(BASE_URL).create(IMyAPI::class.java)

}