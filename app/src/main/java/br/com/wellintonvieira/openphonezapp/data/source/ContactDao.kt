package br.com.wellintonvieira.openphonezapp.data.source

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.wellintonvieira.openphonezapp.data.models.Contact

@Dao
interface ContactDao {

    @Insert
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY id DESC")
    fun loadContacts(): LiveData<List<Contact>>

    @Query("SELECT * FROM contact WHERE name LIKE :query OR phone_number LIKE :query ORDER BY id DESC")
    fun loadContacts(query: String): LiveData<List<Contact>>
}