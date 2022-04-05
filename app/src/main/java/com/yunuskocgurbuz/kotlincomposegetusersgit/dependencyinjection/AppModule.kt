package com.yunuskocgurbuz.kotlincomposegetusersgit.dependencyinjection

import com.yunuskocgurbuz.kotlincomposegetusersgit.repository.ApiRepository
import com.yunuskocgurbuz.kotlincomposegetusersgit.service.ServiceAPI
import com.yunuskocgurbuz.kotlincomposeimageapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGitUserRepository(
        api: ServiceAPI
    ) = ApiRepository(api)

    @Singleton
    @Provides
    fun provideGitUserApi(): ServiceAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ServiceAPI::class.java)
    }
}