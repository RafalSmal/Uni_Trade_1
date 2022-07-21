package de.syntaxinstitut.myapplication.local

import androidx.lifecycle.LiveData
import androidx.room.*
import de.syntaxinstitut.myapplication.datamodels.ArtikelData

@Dao
interface ArtikelDatabaseDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(itemData: ArtikelData)

    @Query("SELECT COUNT(*) FROM artikel_table")
    fun getCount(): Int

    @Query("SELECT * FROM artikel_table")
    fun getAll(): LiveData<List<ArtikelData>>

    @Query("DELETE FROM artikel_table WHERE id=:id")
    suspend fun deleteById(id:Long)

    @Update
    suspend fun update(artikelData: ArtikelData)
}