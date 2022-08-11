package de.syntaxinstitut.myapplication.data.database

import androidx.room.*
import de.syntaxinstitut.myapplication.data.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.data.datamodels.OrdersData

@Dao
interface ArtikelDatabaseDao {

    /**
     *
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(itemData: ArtikelData)

    @Query("SELECT COUNT(*) FROM artikel_table")
    fun getCount(): Int

    @Query("SELECT * FROM artikel_table")
    fun getAll():List<ArtikelData>

    @Query("DELETE FROM artikel_table WHERE id=:id")
    suspend fun deleteById(id:Long)

    @Update
    suspend fun update(artikelData: ArtikelData)

    @Query("SELECT * FROM ordersdata")
    fun getAllFromOrdersdata() : List<OrdersData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(itemData: OrdersData)

    @Query("SELECT COUNT (*) FROM ordersdata")
    fun getCountFromOrdersdata () : Int

}