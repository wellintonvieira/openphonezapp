package br.com.wellintonvieira.openphonezapp.data.sources

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.wellintonvieira.openphonezapp.data.models.Contact
import br.com.wellintonvieira.openphonezapp.util.Constants

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        fun createDatabase(context: Context) : ContactDao {
            return synchronized(this) {
                Room.databaseBuilder(context,
                    ContactDatabase::class.java,
                    Constants.DATABASE_NAME)
                    .build().contactDao()
            }
        }
    }
}