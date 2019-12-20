package com.fabiolourenco.medlist.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.fabiolourenco.medlist.data.Repository
import com.fabiolourenco.medlist.data.model.Medication
import com.fabiolourenco.medlist.ui.medlist.MedListViewModel
import com.fabiolourenco.medlist.ui.newmed.NewMedViewModel
import javax.inject.Inject

/**
 * Has this is a simple example, one view model is enough.
 */
class MedViewModel @Inject constructor(private val repository: Repository) : ViewModel(),
    MedListViewModel, NewMedViewModel {

    // TODO("Use loading states to move loading showing from Views to ViewModel")

    // MediatorLiveData can observe other LiveData objects and react on their emissions
    private var observableMedicationList: MediatorLiveData<List<Medication>> = MediatorLiveData()

    init {
        observableMedicationList.addSource(repository.getMedicationList()) {
            observableMedicationList.value = it
        }
    }

    override fun deleteMedication(medication: Medication) {
        repository.removeMedication(medication)
    }

    override fun getMedicationList() = observableMedicationList

    override fun onSubmitClicked(name: String, quantity: Int) {
        repository.getImageUrlForName(name).observeForever {
            repository.addNewMedication(Medication(name, quantity, it))
        }
    }
}