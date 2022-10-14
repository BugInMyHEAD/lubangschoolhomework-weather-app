package com.buginmyhead.lubangschoolhomework.weather.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buginmyhead.lubangschoolhomework.weather.databinding.FragmentMainBinding
import com.buginmyhead.lubangschoolhomework.weather.domain.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoFailure
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoLoading

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewStatePresenter = object : ViewState.Presenter<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure> {

            override fun onSuccess(data: WeatherInfo) {
                binding.message.text = data.toString()
            }

            override fun onLoading(data: WeatherInfoLoading) {
                binding.message.text = data.toString()
            }

            override fun onFailure(data: WeatherInfoFailure) {
                binding.message.text = data.toString()
            }

        }
        viewModel.weatherInfoLiveData.observe(viewLifecycleOwner) {
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