package com.example.abbreviationsappbyazim.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.abbreviationsappbyazim.R
import com.example.abbreviationsappbyazim.adapters.ViewPagerAdapter
import com.example.abbreviationsappbyazim.databinding.ActivityMainBinding
import com.example.abbreviationsappbyazim.util.NetworkListener
import com.example.abbreviationsappbyazim.util.UiState
import com.example.abbreviationsappbyazim.util.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel : AbbreviationsViewModel by lazy {
        ViewModelProvider(this)[AbbreviationsViewModel::class.java]
    }

    private lateinit var networkListener: NetworkListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        lifecycleScope.launchWhenStarted {
            networkListener = NetworkListener()
            networkListener.checkNetworkAvailability(applicationContext)
                .collect { status ->
                    Log.i("NetworkListener", status.toString())
                    readDatabase()
                }
        }

        setContentView(binding.root)

    }

    private fun readDatabase() {
        lifecycleScope.launch {
            viewModel.readAbbreviations("SOS")
            viewModel.readAbbreviations.observeOnce(this@MainActivity) { database ->
                if (database.isNotEmpty()) {
                    Log.i("data", "readDatabase called!")
                    Log.i("data", database[0].abbreviationsModel.toString())
//                    updateUI(database[0].abbreviationsModel)
                } else {
                    viewModel.getAbbreviations("SOS")
                    Log.i("data", "API called!")
                    viewModel.abbreviationsLiveData.observe(this@MainActivity) { state ->
                        when(state) {
                            is UiState.Loading -> {
                                Log.i("API Response: ", "LOADING")
                            }
                            is UiState.Success -> {
                                Log.i("data", "API called success!")
                                binding.viewPager.adapter = ViewPagerAdapter(state.abbrevResponse)
                            }
                            is UiState.Error -> {
                                Log.i("API Response: ", "Error -> ${state.error}")
                            }
                        }
                    }
                }
            }
        }
    }
}