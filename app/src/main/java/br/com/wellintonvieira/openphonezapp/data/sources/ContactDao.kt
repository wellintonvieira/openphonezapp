package br.com.wellintonvieira.openphonezapp.data.sources

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.wellintonvieira.openphonezapp.data.models.Contact

@Dao
interface ContactDao {

    @Insert
    suspend fun insert(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY id DESC")
    fun load(): LiveData<List<Contact>>
}