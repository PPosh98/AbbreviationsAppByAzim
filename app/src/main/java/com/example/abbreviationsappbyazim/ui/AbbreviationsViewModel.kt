package com.example.abbreviationsappbyazim.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.abbreviationsappbyazim.models.Abbreviations
import com.example.abbreviationsappbyazim.repository.Repository
import com.example.abbreviationsappbyazim.roomdb.AbbreviationsEntity
import com.example.abbreviationsappbyazim.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AbbreviationsViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private var _abbreviationsLiveData: MutableLiveData<UiState> = MutableLiveData(UiState.Loading)
    val abbreviationsLiveData: LiveData<UiState> get() = _abbreviationsLiveData

    lateinit var readAbbreviations: LiveData<List<AbbreviationsEntity>>

    fun readAbbreviations(shortForm: String) {
        readAbbreviations = repository.getAbbreviationsFromDB(shortForm).asLiveData()
    }

    fun getAbbreviations(shortForm: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAbbreviationsFromAPI(shortForm)
            if(response.isSuccessful){
                _abbreviationsLiveData.postValue(
                    response.body()?.let {
                        if (response.body()!!.size > 0) addDataToDatabase(it)
                        UiState.Success(
                            response.body() as Abbreviations
                        )
                    }
                )
            } else {
                _abbreviationsLiveData.postValue(
                    UiState.Error(
                        Throwable(
                            response.message()
                        )
                    )
                )
            }
        }
    }

    private fun addDataToDatabase(abbreviations: Abbreviations) {
        val abbreviationsEntity = AbbreviationsEntity(abbreviations)
        insertAbbreviations(abbreviationsEntity)
    }

    private fun insertAbbreviations(abbreviationsEntity: AbbreviationsEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.addAbbreviationsToDB(abbreviationsEntity)
        }
    }
}