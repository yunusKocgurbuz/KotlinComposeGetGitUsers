package com.yunuskocgurbuz.kotlincomposegetusersgit.model.usersmodel

data class UsersModel(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)