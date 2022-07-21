package de.syntaxinstitut.myapplication.local

import android.content.Context
import androidx.room.*
import de.syntaxinstitut.myapplication.datamodels.ArtikelData

@Database(entities = [ArtikelData ::class],version=1)
abstract class ArtikelDatabase: RoomDatabase() {

    abstract val artikelPoolDao: ArtikelDatabaseDao


}

private lateinit var INSTANCE : ArtikelDatabase

fun getDatabase(context: Context) : ArtikelDatabase{
    synchronized(ArtikelDatabase::class.java){
        if (!::INSTANCE.isInitialized){
            INSTANCE=Room.databaseBuilder(context.applicationContext,ArtikelDatabase::class.java,"ArtikelData").build()


        }

    }

    return INSTANCE
}
