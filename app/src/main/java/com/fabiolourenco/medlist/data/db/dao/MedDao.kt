package com.fabiolourenco.medlist.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fabiolourenco.medlist.data.model.Medication

@Dao
interface MedDao {

    @Query("SELECT * FROM Medication")
    fun getMedicationList(): LiveData<List<Medication>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(medication: Medication): Long

    @Delete
    fun delete(medication: Medication): Int

    @Delete
    fun deleteAll(medications: List<Medication>): Int

}