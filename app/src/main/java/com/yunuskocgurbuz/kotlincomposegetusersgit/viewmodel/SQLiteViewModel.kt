package com.yunuskocgurbuz.kotlincomposegetusersgit.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.yunuskocgurbuz.kotlincomposegetusersgit.database.FavoriteUsersDatabase
import com.yunuskocgurbuz.kotlincomposegetusersgit.entity.FavoriteUsersEntity
import com.yunuskocgurbuz.kotlincomposegetusersgit.repository.SQLiteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SQLiteViewModel(application: Application): AndroidViewModel(application){

    val readAllFavoriteUsers: LiveData<List<FavoriteUsersEntity>>

    private val repository: SQLiteRepository

    init {
        val CurrencyDao = FavoriteUsersDatabase.getInstance(application).FavoriteUsersDao()
        repository = SQLiteRepository(CurrencyDao)
        readAllFavoriteUsers = repository.readAllFavoriteUsers
    }


    fun AddFavoriteUser(item: List<FavoriteUsersEntity>){
        viewModelScope.launch(Dispatchers.IO ) {
            repository.addFavoriteUser(item = item)
        }
    }

    fun DeleteFavoriteUser(uuId : Long){
        viewModelScope.launch(Dispatchers.IO ) {
            repository.deleteFavoriteUser(uuId)
        }
    }

    fun DeleteAllFavoriteUsers(){
        viewModelScope.launch(Dispatchers.IO ) {
            repository.deleteAllFavoriteUsers()
        }
    }
}

class FavoriteUsersViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if(modelClass.isAssignableFrom(SQLiteViewModel::class.java)){
            return SQLiteViewModel(application) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}

