package com.dheevvvv.kalorimonitoring.room_database

import androidx.room.*


@Dao
interface MakananDikonsumsiDAO {
    @Insert
    suspend fun insert(makananDikonsumsiData: MakananDikonsumsiData)

    @Query("SELECT * FROM makanan_dikonsumsi_table")
    suspend fun getAllDataMakananDikonsumsi() : List<MakananDikonsumsiData>

    @Update
    suspend fun updateMakananDikonsumsi(makananDikonsumsiData: MakananDikonsumsiData)

    @Delete
    suspend fun delete(makananDikonsumsiData: MakananDikonsumsiData)


}