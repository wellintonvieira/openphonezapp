package br.com.wellintonvieira.openphonezapp.data.sources

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import androidx.room.OnConflictStrategy.REPLACE
import br.com.wellintonvieira.openphonezapp.data.models.History

@Dao
interface HistoryDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(contact: History)

    @Delete
    suspend fun delete(contact: History)

    @Query("SELECT * FROM history ORDER BY phone_number DESC")
    fun load(): LiveData<List<History>>

    @Query("SELECT * FROM history WHERE phone_number LIKE :query ORDER BY phone_number DESC")
    fun load(query: String): LiveData<List<History>>
}