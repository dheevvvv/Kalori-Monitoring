package com.dheevvvv.kalorimonitoring.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dheevvvv.kalorimonitoring.datastore_preferences.UserManager
import com.dheevvvv.kalorimonitoring.model.UserData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserViewModel():ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")


    suspend fun getUserByEmail(email: String): UserData? {
        return try {
            val querySnapshot: QuerySnapshot = usersCollection.whereEqualTo("email", email).get().await()
            val userDocuments = querySnapshot.documents

            if (userDocuments.isNotEmpty()) {
                userDocuments[0].toObject(UserData::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

}