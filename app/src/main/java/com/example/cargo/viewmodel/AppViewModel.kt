package com.example.cargo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cargo.data.Query
import com.example.cargo.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {
    var itemClickedData :Query?=null
    val data = dataRepository.getStaticData()
}