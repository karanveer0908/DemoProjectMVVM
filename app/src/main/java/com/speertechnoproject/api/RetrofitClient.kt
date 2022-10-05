package com.speertechnoproject.api

import com.speertechnoproject.utils.AppConstants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

  object RetrofitClient {


      val interceptor = run {
          val httpLoggingInterceptor = HttpLoggingInterceptor()
          httpLoggingInterceptor.apply {
              httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
          }
      }

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(interceptor).build()


    private  const val Base_Url=BASE_URL

    val retrofit = Retrofit.Builder()
        .baseUrl(Base_Url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


    val apiInstance = retrofit.create(ApiInterface::class.java)
}