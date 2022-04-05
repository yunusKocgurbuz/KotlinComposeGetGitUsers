package com.yunuskocgurbuz.kotlincomposegetusersgit.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yunuskocgurbuz.kotlincomposegetusersgit.entity.FavoriteUsersEntity

@Dao
interface FavoriteUsersDao {

    @Query("SELECT * FROM favoriteUsersData")
    fun getAllFavoriteUsers(): LiveData<List<FavoriteUsersEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteUser(item : List<FavoriteUsersEntity>)

    @Query("DELETE FROM favoriteUsersData")
    fun deleteAllFavoriteUsers()

    @Query("DELETE FROM favoriteUsersData WHERE uuId = :uuId")
    fun deleteFavoriteUser(uuId : Long)
}