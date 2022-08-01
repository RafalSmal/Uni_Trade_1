package de.syntaxinstitut.myapplication.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum


@Entity(tableName = "artikel_table")
data class ArtikelData(
    @PrimaryKey(autoGenerate = true)
    var id: Long= 0,
    var productText: String,
    var image: String,
    var price: Double?,
    var category: KategorieDetailEnum,
    var quantity: Int = 0,

    )
