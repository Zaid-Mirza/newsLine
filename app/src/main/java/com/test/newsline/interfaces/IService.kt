package com.test.newsline.interfaces


import com.test.newsline.models.GeneralResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface IService {

    @GET("{section}/{days}.json")
    suspend fun getArticles(
        @Path("section") section: String,
        @Path("days") days: String,
        @Query("api-key") apiKey: String
    ): Response<GeneralResponse>


}
