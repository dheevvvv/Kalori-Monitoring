package com.dheevvvv.kalorimonitoring.room_database

import androidx.room.*


@Dao
interface MakananDikonsumsiDAO {
    @Insert
    fun insert(makananDikonsumsiData: MakananDikonsumsiData)

    @Query("SELECT * FROM makanan_dikonsumsi_table WHERE email = :email")
    fun getMakananDikonsumsiByUser(email:String): List<MakananDikonsumsiData>


    @Query("DELETE FROM makanan_dikonsumsi_table WHERE makananId = :makananId AND email = :email")
    fun deleteMakananDikonsumsiByIdAndUser(makananId: Int, email: String)

    @Update
    fun updateMakananDikonsumsi(makananDikonsumsiData: MakananDikonsumsiData)

    @Delete
    fun delete(makananDikonsumsiData: MakananDikonsumsiData)


}