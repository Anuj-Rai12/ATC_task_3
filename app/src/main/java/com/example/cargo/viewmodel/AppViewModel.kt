package com.example.cargo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cargo.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(dataRepository: DataRepository) : ViewModel() {
    val data = dataRepository.getStaticData()
}