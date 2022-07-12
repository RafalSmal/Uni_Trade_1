package de.syntaxinstitut.myapplication.datamodels

import de.syntaxinstitut.myapplication.data.KategorieDetailEnum

data class ArtikelData(
    val id: Int,
    val productText: String,
    val quantity: Int,
    val image: Int,
    val price: Double,
    val category: KategorieDetailEnum
)
