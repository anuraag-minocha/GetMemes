package com.getmemes.data.api

import com.getmemes.data.model.GetMemes
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("get_memes")
    suspend fun getMemes(): Response<GetMemes>

}