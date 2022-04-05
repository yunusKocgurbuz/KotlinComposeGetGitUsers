package com.yunuskocgurbuz.kotlincomposegetusersgit.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteUsersData")
data class FavoriteUsersEntity(
    @PrimaryKey val uuId: Long? = null,

    @ColumnInfo(name = "user_name")
    var user_name: String?


) {
    /*
    @PrimaryKey(autoGenerate = true)
    var uuId : Int = 0

     */
}