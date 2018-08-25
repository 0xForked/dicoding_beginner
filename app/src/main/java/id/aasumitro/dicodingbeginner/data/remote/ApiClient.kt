package id.aasumitro.dicodingbeginner.data.remote

import id.aasumitro.dicodingbeginner.utlil.AppConst
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

class ApiClient {

    private fun provideOkHttpClient(): OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    private fun provideRetrofit(): Retrofit = Retrofit
            .Builder()
            .baseUrl(AppConst.API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()

    fun apiServices(): ApiService = provideRetrofit()
            .create(ApiService::class.java)

}