package com.fabiolourenco.medlist.ui.medlist

import androidx.lifecycle.LiveData
import com.fabiolourenco.medlist.data.model.Medication

/**
 * Use this interface to establish some rules for the ViewModel that implements it.
 * In the future use this with a ViewModelFactory to achieve a higher level of abstraction
 */
interface MedListViewModel {
    fun deleteMedication(medication: Medication)
    fun getMedicationList(): LiveData<List<Medication>>
}