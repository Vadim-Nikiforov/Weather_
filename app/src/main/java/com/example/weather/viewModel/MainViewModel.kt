package com.example.weather.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.Repository
import com.example.weather.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository = RepositoryImpl()) :
    ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussia = true)

    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussia = false)

    private fun getDataFromLocalSource(isRussia: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(3000)
            liveDataToObserve.postValue(
                AppState.Success(
                    if (isRussia) repository.getWeatherFromLocalStorageRus()
                    else repository.getWeatherFromLocalStorageWorld()
                )
            )
        }.start()
    }
}