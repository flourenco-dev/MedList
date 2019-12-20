package com.fabiolourenco.medlist.data.api

import com.fabiolourenco.medlist.data.model.ImageUrl
import retrofit2.Call

interface ApiHelper {
    fun getImageByName(name: String): Call<ImageUrl>
}