package com.fabiolourenco.medlist

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fabiolourenco.medlist.data.db.MedDatabase
import com.fabiolourenco.medlist.data.db.dao.MedDao
import com.fabiolourenco.medlist.data.model.Medication
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MedDatabaseTester {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var medDao: MedDao
    private lateinit var db: MedDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, MedDatabase::class.java).build()
        medDao = db.medDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeReadTest() {
        val medication = Medication("Veraspir", 55)
        medDao.insert(medication)
        medDao.getMedicationList().observeForever {
            assertThat(it[0], equalTo(medication))
        }
    }
}