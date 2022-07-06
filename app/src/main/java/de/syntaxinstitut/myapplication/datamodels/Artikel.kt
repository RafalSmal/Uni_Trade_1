package de.syntaxinstitut.myapplication.datamodels

data class Artikel(
    val id: Int,
    val productText: String,
    val quantity: Int,
    val image: Int,
    val price: Double,
    val category: Int
)
