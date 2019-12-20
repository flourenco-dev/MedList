package com.fabiolourenco.medlist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fabiolourenco.medlist.data.db.dao.MedDao
import com.fabiolourenco.medlist.data.model.Medication

@Database(entities = [Medication::class], version = 1)
abstract class MedDatabase : RoomDatabase() {
    abstract val medDao: MedDao
}