package com.dheevvvv.kalorimonitoring.viewmodel

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dheevvvv.kalorimonitoring.datastore_preferences.UserManager
import com.dheevvvv.kalorimonitoring.model.DaftarMakanan
import com.dheevvvv.kalorimonitoring.model.UserData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserViewModel():ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")

    private val _user = MutableLiveData<UserData>()
    val user: LiveData<UserData> get() = _user

    @SuppressLint("NullSafeMutableLiveData")
    fun fetchUserByEmail(email: String) {
        viewModelScope.launch {
            try {
                val querySnapshot = usersCollection.whereEqualTo("email", email).get().await()

                if (!querySnapshot.isEmpty) {
                    val user = querySnapshot.documents[0].toObject(UserData::class.java)
                    _user.value = user!!
                } else {
                    // Dokumen tidak ditemukan untuk email yang diberikan
                    _user.value = null
                }
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }


}