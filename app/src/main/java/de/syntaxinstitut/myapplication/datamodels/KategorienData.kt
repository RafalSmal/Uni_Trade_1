package de.syntaxinstitut.myapplication.datamodels

import de.syntaxinstitut.myapplication.data.KategorieDetailEnum

data class KategorienData(
    val name: String,
    val image: Int,
    val kategorie: KategorieDetailEnum
)
