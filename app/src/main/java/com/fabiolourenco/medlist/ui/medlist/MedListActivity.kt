package com.fabiolourenco.medlist.ui.medlist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.fabiolourenco.medlist.R
import com.fabiolourenco.medlist.data.model.Medication
import com.fabiolourenco.medlist.ui.MedViewModel
import com.fabiolourenco.medlist.ui.newmed.AddMedDialogFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_med_list.*
import javax.inject.Inject

class MedListActivity : DaggerAppCompatActivity(), MedListView, MedListAdapter.MedicationListener {

    @Inject
    lateinit var viewModeFactory: ViewModelProvider.Factory

    private lateinit var adapter: MedListAdapter
    private lateinit var viewModel: MedListViewModel

    private val medicationList: MutableList<Medication> = mutableListOf()
    private var addMedDialogFragment: AddMedDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_med_list)

        adapter = MedListAdapter(medicationList, this)
        medListRecycler.layoutManager = LinearLayoutManager(this)
        medListRecycler.adapter = adapter

        viewModel = ViewModelProviders.of(this, viewModeFactory).get(MedViewModel::class.java)
        viewModel.getMedicationList().observe(this, Observer {
            hideLoading()
            it?.let {
                updateListUi(it)
                hideError()

            } ?: kotlin.run {
                showError()
            }
        })

        loadInitialUi()
    }

    override fun loadInitialUi() {
        showLoading()
        hideMedListView()
        hideEmptyView()

        newMedFab.setOnClickListener {
            showAddMedScreen()
        }
    }

    override fun updateListUi(medList: List<Medication>) {
        if (medList.isEmpty()) {
            showEmptyView()
            hideMedListView()

        } else {
            hideEmptyView()
            showMedListView()
        }

        medicationList.clear()
        medicationList.addAll(medList)
        adapter.notifyDataSetChanged()
    }

    override fun showAddMedScreen() {
        addMedDialogFragment = AddMedDialogFragment()
        addMedDialogFragment?.dialog?.setOnCancelListener {
            hideAddMedScreen()
        }
        addMedDialogFragment?.dialog?.setOnDismissListener {
            hideAddMedScreen()
        }
        addMedDialogFragment?.show(supportFragmentManager,
            AddMedDialogFragment::class.java.simpleName)
    }

    override fun hideAddMedScreen() {
        actionBar?.title = getString(R.string.app_name)
        addMedDialogFragment?.dismiss()
        addMedDialogFragment = null
    }

    override fun showMedListView() {
        medListRecycler.isVisible = true
    }

    override fun hideMedListView() {
        medListRecycler.isInvisible = true
    }

    override fun showLoading() {
        loadingView.isVisible = true
    }

    override fun hideLoading() {
        loadingView.isVisible = false
    }

    override fun showError() {

    }

    override fun hideError() {

    }

    override fun showEmptyView() {
        emptyListText.isVisible = true
    }

    override fun hideEmptyView() {
        emptyListText.isVisible = false
    }

    override fun showMedDetails(medication: Medication) {
        Toast.makeText(this,
            "You have ${medication.quantity} boxes of ${medication.name}!",
            Toast.LENGTH_SHORT).show()
    }

    override fun onMedicationClicked(medication: Medication) {
        showMedDetails(medication)
    }

    override fun onMedicationLongClicked(medication: Medication): Boolean {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.delete_medication))
            .setMessage("Are you sure you want to delete ${medication.name}?")
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                viewModel.deleteMedication(medication)
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()

        return true
    }
}
