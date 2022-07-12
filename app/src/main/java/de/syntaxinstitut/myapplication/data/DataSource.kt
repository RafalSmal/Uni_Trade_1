package de.syntaxinstitut.myapplication.data

import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.datamodels.ArtikelData

class DataSource {
    fun loadArtikel(): List<ArtikelData> {
        return listOf(
            ArtikelData(1,"Tomaten getrocknet 500ml",3, R.drawable.tomate,2.22,KategorieDetailEnum.GEMUESE),
            ArtikelData(2,"Salatgurken 10kg Kiste",2,R.drawable.gurke,8.87,KategorieDetailEnum.GEMUESE),
            ArtikelData(3,"Rote Paprika 5kg Kiste",3,R.drawable.paprika,12.12,KategorieDetailEnum.GEMUESE),
            ArtikelData(4,"Rinderfleisch 10kg mit Knochen",1,R.drawable.fleisch,28.80,KategorieDetailEnum.FLEISCH),
            ArtikelData(5,"Pommes Tiefgekühlt 10kg Packung",5,R.drawable.pommes,7.54,KategorieDetailEnum.TIEFKUEHL),
            ArtikelData(6,"Griechisches Olivenöl 10l",2,R.drawable.oliven_l,16.88,KategorieDetailEnum.FLEISCH),
            ArtikelData(7,"Süßigkeiten Mix 5kg",3,R.drawable.sweets,5.55,KategorieDetailEnum.SWEETS)
        )
    }
}

