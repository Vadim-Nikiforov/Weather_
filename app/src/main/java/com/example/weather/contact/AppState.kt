package com.example.weather.contact

sealed class AppState {
    class Success(val data: List<String>) : AppState()
    object Loading : AppState()
}
