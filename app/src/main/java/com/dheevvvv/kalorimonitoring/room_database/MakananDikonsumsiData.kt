package com.dheevvvv.kalorimonitoring.room_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "makanan_dikonsumsi_table")
data class MakananDikonsumsiData(
    @PrimaryKey(autoGenerate = true)
    val makananId: Int = 0,
    @ColumnInfo(name = "namaMakanan")
    val namaMakanan:String,
    @ColumnInfo(name = "gambarPath")
    val gambarPath:String,
    @ColumnInfo(name = "jumlahKalori")
    val jumlahKalori:String,
    @ColumnInfo(name = "jamMakan")
    var jamMakan:String,
    @ColumnInfo(name = "takaranSaji")
    var takaranSaji:String
) {
}