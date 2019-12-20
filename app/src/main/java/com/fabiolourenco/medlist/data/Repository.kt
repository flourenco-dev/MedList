package com.fabiolourenco.medlist.data

import androidx.lifecycle.LiveData
import com.fabiolourenco.medlist.data.model.Medication

interface Repository {
    fun getMedicationList(): LiveData<List<Medication>>
    fun addNewMedication(medication: Medication)
    fun removeMedication(medication: Medication)
    fun bulkRemoveMedications(medications: List<Medication>)

    fun getSuccessOperationObservable(): LiveData<Boolean>
    fun getShowLoadingObservable(): LiveData<Boolean>

    fun getImageUrlForName(name: String): LiveData<String>
}