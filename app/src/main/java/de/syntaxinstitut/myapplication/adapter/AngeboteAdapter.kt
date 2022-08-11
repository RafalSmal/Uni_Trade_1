package de.syntaxinstitut.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.data.datamodels.ArtikelData

/**
 *
 */
class AngeboteAdapter(
    private val context: Context,
    private val dataset: List<ArtikelData>,
    private val clickListener: (ArtikelData, Boolean) -> Unit

) : RecyclerView.Adapter<AngeboteAdapter.ItemViewHolder>() {

    //Klassen Variablen
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImageView: ImageView = view.findViewById(R.id.list_articleImage_angebote_new)
        val productText: TextView = view.findViewById(R.id.artikelDescription_angeboteDetail)
        val productPrice: TextView = view.findViewById(R.id.salePrice_angeboteDetail)
        val detailCounterA: TextView = view.findViewById(R.id.addCard_Angebot)
        val plusItemA: ImageButton = view.findViewById(R.id.plusItemAN)
        val minusItemA: ImageButton = view.findViewById(R.id.minusItemAN)
    }

    /**
     * Die Funktion ist für die größe der Liste Zuständig
     */
    override fun getItemCount(): Int {
        return dataset.size
    }

    /**
     * Hier wird das Layout ( View ) erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_angebote_new, parent, false)
        return ItemViewHolder(adapterLayout)
    }


    /**
     * Hier findet der Recycler Prozess statt, die vom ViewHolder bereitgestellten Parameter werden geladen
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val saleItem = dataset[position]

        val imageUri = saleItem.image.toUri().buildUpon().scheme("https").build()

        holder.productImageView.load(imageUri) {
            transformations(RoundedCornersTransformation(10f))
        }

        holder.productText.text = saleItem.productText

        holder.productPrice.text = saleItem.price.toString()


        /**
         * Mit dieser Funktion kann man innerhalb des Warenkorbs die Anzahl der Artikel bestimmt werden
         */
        fun changeBasket(value: Int) {
            if (saleItem.quantity == 0 && value < 0) {
                return
            } else {
                saleItem.quantity += value
                holder.detailCounterA.text = saleItem.quantity.toString()
            }
        }

        holder.productPrice.text = saleItem.price.toString() + " €"
        holder.productText.text = saleItem.productText.toString()
        holder.productImageView.load(imageUri)
        holder.detailCounterA.text = saleItem.quantity.toString()

        holder.plusItemA.setOnClickListener {
            changeBasket(1)
            clickListener(saleItem, true)
        }

        holder.minusItemA.setOnClickListener {
            changeBasket(-1)
            if (saleItem.quantity == 0) {
                clickListener(saleItem, false)
            } else {
                clickListener(saleItem, true)
            }
        }

    }

}





