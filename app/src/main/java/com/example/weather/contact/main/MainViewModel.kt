package com.example.weather.contact.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.contact.AppState
import com.example.weather.contact.repository.RepositoryContact
import com.example.weather.contact.repository.RepositoryContactImpl

class MainViewModel(private val repository: RepositoryContact = RepositoryContactImpl()) : ViewModel() {
    val contacts: MutableLiveData<AppState> = MutableLiveData()

    fun getContacts(){
        contacts.value = AppState.Loading
        val answer = repository.getListOfContact()
        contacts.value = AppState.Success(answer)
    }
}