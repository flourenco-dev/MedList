package com.fabiolourenco.medlist.ui.newmed

import androidx.annotation.IntRange

/**
 * Use this interface to establish some rules for the ViewModel that implements it.
 * In the future use this with a ViewModelFactory to achieve a higher level of abstraction
 */
interface NewMedViewModel {
    fun onSubmitClicked(name: String, @IntRange(from = 0) quantity: Int)
}