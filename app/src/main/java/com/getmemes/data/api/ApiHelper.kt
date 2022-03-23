package com.getmemes.data.api

import javax.inject.Inject

class ApiHelper @Inject constructor(private val apiService: ApiService) {

    suspend fun getMemes() = apiService.getMemes()

}