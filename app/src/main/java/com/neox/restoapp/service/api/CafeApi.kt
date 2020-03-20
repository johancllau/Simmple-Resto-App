package com.neox.restoapp.service.api

import com.neox.restoapp.model.Cafe
import io.reactivex.Single
import retrofit2.http.GET

interface CafeApi {

    @GET("space")
    fun getCafe(): Single<Cafe>

}