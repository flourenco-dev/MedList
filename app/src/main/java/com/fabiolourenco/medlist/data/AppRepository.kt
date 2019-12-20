package com.fabiolourenco.medlist.data

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fabiolourenco.medlist.data.api.ApiHelper
import com.fabiolourenco.medlist.data.db.dao.MedDao
import com.fabiolourenco.medlist.data.model.ImageUrl
import com.fabiolourenco.medlist.data.model.Medication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class AppRepository @Inject constructor(private val medDao: MedDao,
                                        private val executor: ExecutorService,
                                        private val apiHelper: ApiHelper) : Repository {

    override fun getMedicationList(): LiveData<List<Medication>> = medDao.getMedicationList()

    override fun addNewMedication(medication: Medication) {
        executor.execute {
            val result = medDao.insert(medication)
            result > 0
        }
    }

    override fun removeMedication(medication: Medication) {
        executor.execute {
            val result = medDao.delete(medication)
            result > 0
        }
    }

    override fun bulkRemoveMedications(medications: List<Medication>) {
        executor.execute {
            val result = medDao.deleteAll(medications)
            result > 0
        }
    }

    override fun getImageUrlForName(name: String): LiveData<String> {
        val imageName = MutableLiveData<String>()

        apiHelper.getImageByName(name).enqueue(object : Callback<ImageUrl?> {
            override fun onResponse(call: Call<ImageUrl?>, response: Response<ImageUrl?>) {
                if (response.isSuccessful && !TextUtils.isEmpty(response.body()?.url)) {
                    imageName.postValue(response.body()!!.url)

                } else {
                    Timber.w("Request was not successful or the body was empty")
                    imageName.postValue(null)
                }
            }

            override fun onFailure(call: Call<ImageUrl?>, t: Throwable) {
                Timber.w(t)
                imageName.postValue(null)
            }
        })

        return imageName
    }

    override fun getSuccessOperationObservable(): LiveData<Boolean> {
        TODO("Give feedback from operations made to db and api")
    }

    override fun getShowLoadingObservable(): LiveData<Boolean> {
        TODO("Alert observer that information is being loaded")
    }
}
