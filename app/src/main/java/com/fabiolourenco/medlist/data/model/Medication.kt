package com.fabiolourenco.medlist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medication(@PrimaryKey var name: String,
                      var quantity: Int,
                      var image: String? = null)