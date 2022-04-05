package com.yunuskocgurbuz.kotlincomposegetusersgit.view

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.yunuskocgurbuz.kotlincomposegetusersgit.entity.FavoriteUsersEntity
import com.yunuskocgurbuz.kotlincomposegetusersgit.viewmodel.FavoriteUsersViewModelFactory
import com.yunuskocgurbuz.kotlincomposegetusersgit.viewmodel.SQLiteViewModel

@Composable
fun FavoriteUsersScreen() {

    //for SQLite connection
    val context = LocalContext.current
    val sqliteViewModel: SQLiteViewModel = viewModel(
        factory = FavoriteUsersViewModelFactory(context.applicationContext as Application)
    )

    val getAllFavoriteUsers= sqliteViewModel.readAllFavoriteUsers.observeAsState(listOf()).value



    Column {

        Button(onClick = { sqliteViewModel.DeleteAllFavoriteUsers() }) {
            Text(text = "Delete All Favorite Users")
        }

        LazyColumn{
            items(getAllFavoriteUsers){ items ->
                FavoriteUsers(items, sqliteViewModel)
            }

        }

    }


}


@Composable
fun FavoriteUsers(
    items: FavoriteUsersEntity,
    sqliteViewModel: SQLiteViewModel
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        backgroundColor = Color.LightGray,
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ) {

    }



    Row {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = items.user_name!!,
            modifier = Modifier.size(200.dp, 25.dp))
        }

        Column(modifier = Modifier.padding(20.dp)) {
            Icon(
                Icons.Outlined.Delete,
                contentDescription = "delete",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        sqliteViewModel.DeleteFavoriteUser(items.uuId!!)
                    }
            )
        }


    }

}

