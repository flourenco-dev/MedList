package com.fabiolourenco.medlist.ui.medlist

import com.fabiolourenco.medlist.data.model.Medication

/**
 * Use this interface just to establish some rules for the view that implements it (MVP style)
 */
interface MedListView {
    fun loadInitialUi()
    fun updateListUi(medList: List<Medication>)
    fun showAddMedScreen()
    fun hideAddMedScreen()
    fun showMedListView()
    fun hideMedListView()
    fun showLoading()
    fun hideLoading()
    fun showError()
    fun hideError()
    fun showEmptyView()
    fun hideEmptyView()
    fun showMedDetails(medication: Medication)
}