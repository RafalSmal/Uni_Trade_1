package de.syntaxinstitut.myapplication.data.database

import android.content.Context
import androidx.room.*
import de.syntaxinstitut.myapplication.data.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.data.datamodels.OrdersData

@Database(entities = [ArtikelData::class,OrdersData::class], version = 1)
@TypeConverters(Converters::class)
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
