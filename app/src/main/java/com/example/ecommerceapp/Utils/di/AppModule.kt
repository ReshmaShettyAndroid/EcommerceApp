package com.example.ecommerceapp.di

import android.app.Application
import android.content.Context
import com.example.ecommerceapp.Utils.ApiUrlConstants
import com.example.ecommerceapp.data.repositoryImpl.RegistrationImpl
import com.example.ecommerceapp.domain.repository.RegistrationRepository
import com.example.ecommerceapp.network.ApiService
import com.example.ecommerceapp.network.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesBaseUrl() = ApiUrlConstants.BASE_URL

    @Provides
    @Singleton
    fun providesApiService(url: String): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .authenticator(TokenAuthenticator())
        .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


    @Provides
    fun provideProductRepository(registrationImpl: RegistrationImpl): RegistrationRepository = registrationImpl

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}