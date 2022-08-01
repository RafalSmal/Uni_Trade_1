package de.syntaxinstitut.myapplication.database

import android.content.Context
import androidx.room.*
import de.syntaxinstitut.myapplication.datamodels.ArtikelData

@Database(entities = [ArtikelData::class], version = 1)
abstract class ArtikelDatabase : RoomDatabase() {

    abstract val artikelPoolDao: ArtikelDatabaseDao

    companion object {
        private lateinit var INSTANCE: ArtikelDatabase
        fun getDatabase(context: Context): ArtikelDatabase {
            synchronized(this) {
                if (!Companion::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ArtikelDatabase::class.java,
                        "UniTrade_database"
                    ).allowMainThreadQueries().build()

                }

            }

            return INSTANCE
        }


    }
}
