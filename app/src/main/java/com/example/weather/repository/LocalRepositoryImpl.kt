package com.example.weather.repository

import com.example.weather.model.Weather
import com.example.weather.model.convertHistoryEntityToWeather
import com.example.weather.model.convertWeatherToEntity
import com.example.weather.room.HistoryDao

class LocalRepositoryImpl(private val localDataSource: HistoryDao) : LocalRepository {
    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        return localDataSource.insert(convertWeatherToEntity(weather))
    }
}