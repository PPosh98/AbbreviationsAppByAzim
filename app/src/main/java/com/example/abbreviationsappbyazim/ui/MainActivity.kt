package com.example.abbreviationsappbyazim.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    private val viewModel : AbbreviationsViewModel by lazy {
        ViewModelProvider(this)[AbbreviationsViewModel::class.java]
    }

    private lateinit var networkListener: NetworkListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val searchView = binding.searchView
        searchView.setOnQueryTextListener(this@MainActivity)

        setContentView(binding.root)

    }

    private fun readDatabase(query: String?) {
        lifecycleScope.launch {
            if (query != null) {
                viewModel.readAbbreviations(query)
            }
            viewModel.readAbbreviations.observeOnce(this@MainActivity) { database ->
                if (database.isNotEmpty()) {
                    Log.i("data", "readDatabase called!")
                    binding.viewPager.adapter = ViewPagerAdapter(database[0].abbreviationsModel)
                } else {
                    if (query != null) {
                        viewModel.getAbbreviations(query)
                    }
                    Log.i("data", "API called!")
                    viewModel.abbreviationsLiveData.observe(this@MainActivity) { state ->
                        when(state) {
                            is UiState.Loading -> {
                                Log.i("API Response: ", "LOADING")
                            }
                            is UiState.Success -> {
                                if (state.abbrevResponse.size > 0) {
                                    binding.viewPager.adapter = ViewPagerAdapter(state.abbrevResponse)
                                } else {
                                    Toast.makeText(this@MainActivity, "Sorry, results for '$query' not found", Toast.LENGTH_SHORT).show()
                                }
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        lifecycleScope.launchWhenStarted {
            networkListener = NetworkListener()
            networkListener.checkNetworkAvailability(applicationContext)
                .collect { status ->
                    Log.i("NetworkListener", status.toString())
                    viewModel.abbreviationsLiveData.removeObservers(this@MainActivity)
                    readDatabase(query)
                }
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}