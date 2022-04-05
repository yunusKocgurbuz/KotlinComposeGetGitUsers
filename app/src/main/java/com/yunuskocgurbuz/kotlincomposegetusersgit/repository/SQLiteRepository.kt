package com.yunuskocgurbuz.kotlincomposegetusersgit.repository

import androidx.lifecycle.LiveData
import com.yunuskocgurbuz.kotlincomposegetusersgit.dao.FavoriteUsersDao
import com.yunuskocgurbuz.kotlincomposegetusersgit.entity.FavoriteUsersEntity

class SQLiteRepository(private  val FavoriteUsersDao: FavoriteUsersDao) {

    val readAllFavoriteUsers: LiveData<List<FavoriteUsersEntity>> = FavoriteUsersDao.getAllFavoriteUsers()

    suspend fun addFavoriteUser(item: List<FavoriteUsersEntity>){
        FavoriteUsersDao.insertFavoriteUser(item)
    }

    suspend fun deleteAllFavoriteUsers(){
        FavoriteUsersDao.deleteAllFavoriteUsers()
    }

    suspend fun deleteFavoriteUser(uuId : Long) {
        FavoriteUsersDao.deleteFavoriteUser(uuId)
    }

}