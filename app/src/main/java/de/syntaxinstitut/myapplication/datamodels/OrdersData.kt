package de.syntaxinstitut.myapplication.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrdersData(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val auftragsNr : String,
    val bestellDatum : String,
    val auftragNetto : Float,
    val auftragBrutto : Float,
    val lieferStrasse : String,
    val lieferOrt : String
)
