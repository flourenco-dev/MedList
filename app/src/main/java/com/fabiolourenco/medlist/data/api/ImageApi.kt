package com.fabiolourenco.medlist.data.api

import com.fabiolourenco.medlist.data.model.ImageUrl
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageApi {
    // Just a fake url
    @GET("images/{name}")
    fun getImageByName(@Path(value = "name") name: String): Call<ImageUrl>
}