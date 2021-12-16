package com.example.android.infinitescroll.api

import android.util.Log
import com.example.android.infinitescroll.Constants.BASE_URL
import com.example.android.infinitescroll.models.Character
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @Json
    @GET("character")
    fun getCharacter(
        @Query("page") page: Int
    ): Deferred<Character>
}

object RickAndMortyApi {
    val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
        Log.d("infiniteScroll", it)
    })

    val client = OkHttpClient.Builder().addInterceptor(logger.setLevel(HttpLoggingInterceptor.Level.BASIC)).build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ConverterScalarOrJson.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}