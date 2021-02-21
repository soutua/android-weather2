package org.roun.weather2.ui.home

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.roun.weather2.model.WeatherRepository
import org.roun.weather2.model.data.Weather
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    val weather: LiveData<Weather> = weatherRepository.weatherDataInCurrentLocation().asLiveData()
    private val _progress: MutableLiveData<Boolean> = MutableLiveData(false)
    val progress: LiveData<Boolean> = _progress

    fun refresh() {
        viewModelScope.launch {
            _progress.value = true
            weatherRepository.refresh()
            _progress.value = false
        }
    }
}