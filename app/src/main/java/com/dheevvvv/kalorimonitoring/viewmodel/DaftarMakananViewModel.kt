package com.dheevvvv.kalorimonitoring.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dheevvvv.kalorimonitoring.model.DaftarMakanan
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DaftarMakananViewModel:ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val makananCollection = db.collection("makanan")

    private val _makananList = MutableLiveData<List<DaftarMakanan>>()
    val makananList: LiveData<List<DaftarMakanan>> get() = _makananList

    fun fetchListMakanan() {
        viewModelScope.launch {
            try {
                val querySnapshot = makananCollection.get().await()
                val makananList = querySnapshot.toObjects(DaftarMakanan::class.java)
                _makananList.value = makananList
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }
}