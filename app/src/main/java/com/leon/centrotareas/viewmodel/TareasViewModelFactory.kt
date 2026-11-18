package com.leon.centrotareas.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leon.centrotareas.data.TareasRepositoryLocal
class TareasViewModelFactory:ViewModelProvider.Factory {
    override fun <T:ViewModel> create(modelClass:Class<T>):T {
        require(modelClass.isAssignableFrom(TareasViewModel::class.java))
        @Suppress("UNCHECKED_CAST")
        return TareasViewModel(TareasRepositoryLocal()) as T
    }
}
