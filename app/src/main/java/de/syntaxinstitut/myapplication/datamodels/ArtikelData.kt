package de.syntaxinstitut.myapplication.datamodels

import de.syntaxinstitut.myapplication.data.KategorieDetailEnum

data class ArtikelData(
    val id: Int,
    val productText: String,
    var quantity: Int,
    val image: Int,
    val price: Double,
    val category: KategorieDetailEnum
)
