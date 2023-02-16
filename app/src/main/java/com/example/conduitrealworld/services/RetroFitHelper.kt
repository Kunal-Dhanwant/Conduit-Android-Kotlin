package com.example.conduitrealworld.services

import com.example.conduitrealworld.MainApplication
import com.example.conduitrealworld.Utils.TokenManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroFitHelper {


    lateinit var  tokenManager: TokenManager

    private  val BASE_URL = "https://api.realworld.io/api/"

    fun getInstance(): Retrofit {

        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }


    private val authInterceptor = Interceptor { chain ->







        tokenManager = TokenManager(MainApplication.getInstance())

        var req = chain.request()
        val token = tokenManager.getToken()
        token?.let {
            req = req.newBuilder()
                .header("Authorization", "Token $it")
                .build()
        }
        chain.proceed(req)
    }

    val okHttpBuilder = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(2, TimeUnit.SECONDS)



    val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())


    val authApi = retrofitBuilder
        .client(okHttpBuilder.addInterceptor(authInterceptor).build())
        .build()
        .create(ConduitAuthApi::class.java)
}