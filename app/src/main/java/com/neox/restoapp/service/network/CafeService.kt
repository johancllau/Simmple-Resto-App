package com.neox.restoapp.service.network

import com.neox.restoapp.model.Cafe
import com.neox.restoapp.service.api.CafeApi
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CafeService {

    private val BASE_URL = "https://cws-api.herokuapp.com/"
    private val api: CafeApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CafeApi::class.java)
    }

    fun getCafe(): Single<Cafe> {
        return api.getCafe()
    }
}