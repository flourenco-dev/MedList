package com.fabiolourenco.medlist.ui.newmed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.fabiolourenco.medlist.R
import com.fabiolourenco.medlist.ui.MedViewModel
import dagger.android.support.DaggerAppCompatDialogFragment
import kotlinx.android.synthetic.main.fragment_add_med_dialog.*
import javax.inject.Inject

class AddMedDialogFragment : DaggerAppCompatDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: NewMedViewModel

    private var name: String = ""
    private var quantity: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            viewModel = ViewModelProviders.of(it, viewModelFactory).get(MedViewModel::class.java)

            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_add_med_dialog, container, false)

        } ?: kotlin.run {
            return null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMedSubmitButton.setOnClickListener {
            if (areInputsValid()) {
                submitClicked()
            }
        }
    }

    private fun areInputsValid() : Boolean {
        var isValid: Boolean
        val nameValue = addMedNameInput.text?.toString()
        isValid = if (nameValue?.length ?: 0 > 0) {
            name = nameValue!!
            hideNameInputError()
            true

        } else {
            showNameInputError()
            false
        }

        isValid = try {
            val quantityValue = addMedQuantityInput.text?.toString()?.toInt()
            if (quantityValue ?: -1 >= 0) {
                quantity = quantityValue!!
                hideQuantityInputError()
                isValid and true

            } else {
                showQuantityInputError()
                false
            }
        } catch (e: NumberFormatException) {
            showQuantityInputError()
            false
        }

        return isValid
    }

    private fun showNameInputError() {
        addMedNameInputContainer.isErrorEnabled = true
        addMedNameInputContainer.error = getString(R.string.add_med_name_input_error)
    }

    private fun hideNameInputError() {
        addMedNameInputContainer.isErrorEnabled = false
    }

    private fun showQuantityInputError() {
        addMedQuantityInputContainer.isErrorEnabled = true
        addMedQuantityInputContainer.error = getString(R.string.add_med_quantity_input_error)
    }

    private fun hideQuantityInputError() {
        addMedQuantityInputContainer.isErrorEnabled = false
    }

    private fun submitClicked() {
        viewModel.onSubmitClicked(name, quantity)
        dismiss()
    }
}
