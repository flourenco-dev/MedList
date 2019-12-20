package com.fabiolourenco.medlist.ui.medlist

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fabiolourenco.medlist.R
import com.fabiolourenco.medlist.data.model.Medication
import com.fabiolourenco.medlist.utils.isNotEmpty
import kotlinx.android.synthetic.main.item_medication.view.*

class MedListAdapter(private val medList: List<Medication>,
                     private val listener: MedicationListener)
    : RecyclerView.Adapter<MedListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_medication, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = medList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(medList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: Medication) {
            itemView.medicationNameText.text = item.name
            itemView.medicationQuantityText.text = item.quantity.toString()

                Glide.with(itemView.context)
                    .setDefaultRequestOptions(RequestOptions().circleCrop())
                    .load(item.image)
                    .into(itemView.medicationImage)

            itemView.setOnClickListener {
                listener.onMedicationClicked(item)
            }

            itemView.setOnLongClickListener {
                listener.onMedicationLongClicked(item)
            }
        }
    }

    interface MedicationListener {
        fun onMedicationClicked(medication: Medication)
        fun onMedicationLongClicked(medication: Medication): Boolean
    }
}