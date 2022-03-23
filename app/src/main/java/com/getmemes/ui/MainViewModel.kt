package com.getmemes.ui

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.getmemes.data.model.GetMemes
import com.getmemes.data.model.Meme
import com.getmemes.data.repo.MainRepository
import com.getmemes.util.NetworkHelper
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    val movies = MutableLiveData<List<Meme>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun getMemes() {
        viewModelScope.launch {
            loading.postValue(true)
            try {
                val gson = Gson()
                if (networkHelper.isNetworkConnected()) {
                    mainRepository.getMemes().let {
                        if (it.isSuccessful) {
                            movies.postValue(it.body()?.data?.memes)
                            sharedPreferences.edit().putString("getMemes", gson.toJson(it.body()))
                                .apply()
                        } else error.postValue(it.errorBody().toString())
                    }
                } else {
                    sharedPreferences.getString("getMemes", "").let {
                        if (!it.isNullOrEmpty()) {
                            movies.postValue(gson.fromJson(it, GetMemes::class.java).data?.memes)
                        }
                    }
                    error.postValue("No internet connection")
                }
            } catch (e: Exception) {
                error.postValue(e.message)
            }
            loading.postValue(false)
        }
    }
}