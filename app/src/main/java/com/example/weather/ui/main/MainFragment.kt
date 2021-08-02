package com.example.weather.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.weather.MainActivity
import com.example.weather.R
import android.widget.Toast.makeText as makeText1

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        var poteryaevka = City("Потеряевка", 45)
        var textView1 = view?.findViewById<TextView>(R.id.textView)
        var textView2 = view?.findViewById<TextView>(R.id.textView2)

        val button = view?.findViewById<Button>(R.id.Button)
        button?.setOnClickListener {
            textView1?.setText("Город " + poteryaevka.title)
            textView2?.setText("Температура " + (poteryaevka.temperature).toString() + " Градусов")

            for (i in 1..10) {
                println("Hello Kotlin!")
            }
        }
    }
}