package com.example.lab_week_05.api

import com.example.lab_week_05.model.ImageData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {
    @GET("images/search")
    fun searchImages(
        @Query("limit") limit: Int,
        @Query("size") format: String
        //@Query("varname") will forward the following variable as varname (basically api will hear "here you go this is <varname>, it contains the following...)
    ) : Call<List<ImageData>>
    //Call<returnType> returns the as the data type specified
}