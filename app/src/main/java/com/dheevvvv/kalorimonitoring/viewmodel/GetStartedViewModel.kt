package com.dheevvvv.kalorimonitoring.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GetStartedViewModel():ViewModel() {
    internal val _nama = MutableLiveData<String>()
    val nama:LiveData<String> get() =_nama

    internal val _beratBadanSaatIni = MutableLiveData<String>()
    val beratBadanSaatIni:LiveData<String> get() =_beratBadanSaatIni

    internal val _beratBadanYangDiinginkan = MutableLiveData<String>()
    val beratBadanYangDiinginkan:LiveData<String> get() =_beratBadanYangDiinginkan

    internal val _tujuanDiet = MutableLiveData<String>()
    val tujuanDiet:LiveData<String> get() =_tujuanDiet

    internal val _tanggalTargetCapaian = MutableLiveData<String>()
    val tanggalTargetCapaian:LiveData<String> get() =_tanggalTargetCapaian

    internal val _jumlahTargetkaloriHarian = MutableLiveData<String>()
    val jumlahTargetkaloriHarian:LiveData<String> get() =_jumlahTargetkaloriHarian
}