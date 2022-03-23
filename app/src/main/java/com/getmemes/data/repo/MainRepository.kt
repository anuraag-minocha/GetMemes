package com.getmemes.data.repo

import com.getmemes.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getMemes() =  apiHelper.getMemes()

}