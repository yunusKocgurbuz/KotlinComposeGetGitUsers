package com.yunuskocgurbuz.kotlincomposegetusersgit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yunuskocgurbuz.kotlincomposegetusersgit.dao.FavoriteUsersDao
import com.yunuskocgurbuz.kotlincomposegetusersgit.entity.FavoriteUsersEntity


@Database(entities = [FavoriteUsersEntity::class], version = 1, exportSchema = false)
abstract class FavoriteUsersDatabase: RoomDatabase() {

    abstract fun FavoriteUsersDao(): FavoriteUsersDao

    companion object{
        @Volatile
        private var INSTANCE: FavoriteUsersDatabase? = null

        fun getInstance(context: Context): FavoriteUsersDatabase{
            synchronized(this){
                return INSTANCE?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteUsersDatabase::class.java,
                    "favoriteusers_database"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}