package com.example.weather.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.weather.R
import com.example.weather.databinding.FragmentDetailsBinding
import com.example.weather.dto.WeatherDTO
import com.example.weather.model.Weather
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        binding.mainView.hide()
        binding.loadingLayout.show()
        val loader = WeatherLoader(onLoadingListener, weatherBundle.city.lat, weatherBundle.city.lon)
        loader.loadWeather()
    }

    private val onLoadingListener = object : WeatherLoader.WeatherLoaderListener{
        override fun onLoaded(weatherDTO: WeatherDTO) {
            displayWeather(weatherDTO)
        }

        override fun onFailed(throwable: Throwable) {
            //обработака ошибок
        }

    }

    private fun displayWeather(weatherDTO: WeatherDTO) {
        with(binding) {
            mainView.show()
            loadingLayout.hide()
            weatherBundle.city.also { city ->
                cityName.text = city.city
                cityCoordinates.text = String.format(
                    getString(R.string.city_coordinates),
                    city.lat.toString(),
                    city.lon.toString()
                )
                weatherDTO.fact?.let {
                    weatherCondition.text = weatherDTO.fact.condition
                    temperatureValue.text = weatherDTO.fact.temp.toString()
                    feelsLikeValue.text = weatherDTO.fact.feels_like.toString()
                }
            }
        }
    }

    companion object {

        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
