package com.fabiolourenco.medlist.data.api

import com.fabiolourenco.medlist.data.model.ImageUrl
import retrofit2.Call
import javax.inject.Inject

/**
 * This is an API helper class, which is particularly helpful when we need to inject different
 * headers in different request to the same API. In this case we don't need any headers because we
 * are doing a fake request.
 */
class AppApiHelper @Inject constructor(private val imageApi: ImageApi) : ApiHelper {
    override fun getImageByName(name: String): Call<ImageUrl> = imageApi.getImageByName(name)
}