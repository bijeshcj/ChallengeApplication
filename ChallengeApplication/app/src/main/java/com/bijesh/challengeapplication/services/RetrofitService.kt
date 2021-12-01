package com.bijesh.challengeapplication.services

import com.bijesh.challengeapplication.models.DogBreeds
import com.bijesh.challengeapplication.models.desmodels.SelectedBreed
import com.bijesh.challengeapplication.models.desmodels.SelectedBreedItem
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("breeds")
    fun getDogBreeds() : Call<List<DogBreeds>>

    @GET("breeds/search")
    fun getDogBreed(@Query("q") name:String) : Call<List<DogBreeds>>

    @GET("images/search")
    fun getDogBreedById(@Query("breed_id") id: Int) : Call<List<SelectedBreedItem>>


    companion object{
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService{
            var httpClient : OkHttpClient.Builder = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder().addHeader("x-api-key","c8b0b340-a88f-48b3-909a-599e38e33a36").build()
                    return chain.proceed(request)
                }

            })
            if (retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.thedogapi.com/v1/").client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }

}