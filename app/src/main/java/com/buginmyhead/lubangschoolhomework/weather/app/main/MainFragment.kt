package com.buginmyhead.lubangschoolhomework.weather.app.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.buginmyhead.lubangschoolhomework.weather.app.FromResources
import com.buginmyhead.lubangschoolhomework.weather.app.R
import com.buginmyhead.lubangschoolhomework.weather.app.databinding.FragmentMainBinding
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.ThreeDayWeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoFailure
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoLoading
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.comparison.ThreeDayWeatherInfoComparison
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val fromResources by lazy { FromResources(resources) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewStatePresenter = object : ViewState.Presenter<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure> {

            override fun onSuccess(data: ThreeDayWeatherInfo) {
                binding.message.text = fromResources.getStringBy(ThreeDayWeatherInfoComparison.from(data).yesterdayAndToday)
            }

            override fun onLoading(data: WeatherInfoLoading) {
                binding.message.text = getString(R.string.weather_info_loading)
            }

            override fun onFailure(data: WeatherInfoFailure) {
                binding.message.text = getString(R.string.weather_info_failure)
            }

        }
        viewModel.threeDayWeatherInfoLiveData.observe(viewLifecycleOwner) {
            it.showState(viewStatePresenter)
        }

        binding.root.setOnClickListener {
            viewModel.refreshWeatherInfo()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        fun newInstance() = MainFragment()

    }

}