package com.dheevvvv.kalorimonitoring.datastore_preferences

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserManager(private val context: Context) {
    private val preferenceName = "prefs"
    private val Context.datastore by preferencesDataStore(preferenceName)
    private val IS_LOGIN_KEY = booleanPreferencesKey("is_login")
    private val ROLE = stringPreferencesKey("role")
    private val EMAIL = stringPreferencesKey("email")

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var instance: UserManager? = null

        fun getInstance(context: Context): UserManager {
            return instance ?: synchronized(this) {
                instance ?: UserManager(context).also { instance = it }
            }
        }
    }

    suspend fun saveData (email:String, is_login_key:Boolean, role:String){
        context.datastore.edit {
            it [EMAIL] = email
            it [IS_LOGIN_KEY] = is_login_key
            it [ROLE] = role
        }
    }
    suspend fun clearData(){
        context.datastore.edit {
            it.clear()
        }
    }
    fun isLoggedIn(): Flow<Boolean> {
        return context.datastore.data
            .map { preferences ->
                preferences[IS_LOGIN_KEY] ?: false
            }
    }

    val userEmail: Flow<String?>
        get() = context.datastore.data
            .map { preferences ->
                preferences[EMAIL]
            }
    suspend fun getRole():String{
        val preferences = context.datastore.data.first()
        return preferences[ROLE] ?: ""
    }

}