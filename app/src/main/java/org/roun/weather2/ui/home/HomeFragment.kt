package org.roun.weather2.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.roun.weather2.R
import org.roun.weather2.databinding.FragmentHomeBinding
import org.roun.weather2.model.data.Weather
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { permissionGranted: Boolean ->
            if (permissionGranted) {
                refresh()
            }
        }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.weather.observe(viewLifecycleOwner, this::updateUI)
        viewModel.progress.observe(viewLifecycleOwner) { binding.progressIndicator.visibility = if (it) View.VISIBLE else View.GONE }
    }

    override fun onStart() {
        super.onStart()
        checkPermissions()
    }

    private fun updateUI(weather: Weather?) {

        if (weather != null) {
            binding.temperature.text = getString(R.string.temperature, weather.temperature)
            binding.temperature.visibility = if (weather.temperature != null) View.VISIBLE else View.GONE
            binding.windSpeed.text = getString(R.string.wind_speed, weather.windSpeed)
            binding.windSpeed.visibility = if (weather.windSpeed != null) View.VISIBLE else View.GONE
            binding.precipitation.text = getString(R.string.precipitation_amount, weather.precipitationAmount)
            binding.precipitation.visibility = if (weather.precipitationAmount != null) View.VISIBLE else View.GONE
            binding.observationTime.text = weather.time.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withZone(ZoneId.systemDefault())
                .withLocale(Locale.getDefault()))

            if (weather.symbolCode != null) {
                binding.symbol.setImageResource(resources.getIdentifier(weather.symbolCode, "drawable", requireContext().packageName))
            }
        }
    }

    private fun refresh() {
        viewModel.refresh()
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            refresh()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }
}