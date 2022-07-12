package de.syntaxinstitut.myapplication.datamodels

import de.syntaxinstitut.myapplication.data.KategorieDetailEnum
import de.syntaxinstitut.myapplication.ui.fragmenteXml.KategorienDetailFragment

data class KategorienData(
    val name: String,
    val image: Int,
    val kategorie: KategorieDetailEnum
)
