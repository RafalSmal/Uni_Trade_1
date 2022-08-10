package de.syntaxinstitut.myapplication.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrdersData(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var auftragsNr : Int,
    var firma : String,
    val bestellDatum : String,
    val auftragNetto : Float,
    val auftragBrutto : Float,
    var lieferStrasse : String,
    var lieferOrt : String,
    var articles : List<ArtikelData>
)
