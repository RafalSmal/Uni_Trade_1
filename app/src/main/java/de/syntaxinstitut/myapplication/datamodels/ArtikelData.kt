package de.syntaxinstitut.myapplication.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum


@Entity(tableName = "artikel_table")
data class ArtikelData (
    @PrimaryKey(autoGenerate = true)
    var id: Long= 0,
    var productText: String,
    var quantity: Int,
    var image: Int,
    var price: Double,
    var category: KategorieDetailEnum
)
