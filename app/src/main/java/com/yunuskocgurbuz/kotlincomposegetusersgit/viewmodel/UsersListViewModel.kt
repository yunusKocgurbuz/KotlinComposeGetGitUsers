package com.yunuskocgurbuz.kotlincomposegetusersgit.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunuskocgurbuz.kotlincomposegetusersgit.model.usersmodel.Item
import com.yunuskocgurbuz.kotlincomposegetusersgit.model.usersmodel.UsersModel
import com.yunuskocgurbuz.kotlincomposegetusersgit.repository.ApiRepository
import com.yunuskocgurbuz.kotlincomposeimageapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val repository: ApiRepository
): ViewModel() {

    var usersList = mutableStateOf<List<Item>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        loadUsers()
    }

    fun loadUsers(){
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getUsersList()

            when(result){
                is Resource.Success -> {
                    val newItems = result.data!!.items

                    errorMessage.value = ""
                    isLoading.value = false
                    usersList.value += newItems

                }

                is Resource.Error -> {

                    errorMessage.value = result.message!!
                    isLoading.value = false

                }
            }

        }
    }

}


