package com.dheevvvv.kalorimonitoring.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.dheevvvv.kalorimonitoring.room_database.KaloriDatabase
import com.dheevvvv.kalorimonitoring.room_database.MakananDikonsumsiData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MakananDikonsumsiViewModel(application: Application):AndroidViewModel(application){
    private var _listMakananDikonsumsi : MutableLiveData<List<MakananDikonsumsiData>> = MutableLiveData()
    val listMakananDikonsumsi : LiveData<List<MakananDikonsumsiData>> get() = _listMakananDikonsumsi

    private var _makananDikonsumsi : MutableLiveData<MakananDikonsumsiData> = MutableLiveData()
    val makananDikonsumsi : LiveData<MakananDikonsumsiData> get() = _makananDikonsumsi

    val database = KaloriDatabase.getInstance(getApplication())
    val makananDikonsumsiDAO = database!!.makananDikonsumsiDao()


    fun insertMakananDikonsumsi(makananDikonsumsiData: MakananDikonsumsiData){
        viewModelScope.launch {
            makananDikonsumsiDAO.insert(makananDikonsumsiData)
        }
    }

    fun updateMakananDikonsumsi(makananDikonsumsiData: MakananDikonsumsiData){
        viewModelScope.launch {
            makananDikonsumsiDAO.updateMakananDikonsumsi(makananDikonsumsiData)
        }
    }

    fun deleteMakananDikonsumsi(makananDikonsumsiData: MakananDikonsumsiData) {
        viewModelScope.launch {
            makananDikonsumsiDAO.delete(makananDikonsumsiData)
        }
    }

    fun getMakananDikonsumsi(){
        viewModelScope.launch {
            val listMakananDikonsumsi = makananDikonsumsiDAO?.getAllDataMakananDikonsumsi()
            _listMakananDikonsumsi.postValue(listMakananDikonsumsi!!)
        }
    }
}