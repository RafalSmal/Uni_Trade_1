package de.syntaxinstitut.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.datamodels.ArtikelData

class AngeboteAdapter(
    private val context: Context,
    private val dataset: List<ArtikelData>
) : RecyclerView.Adapter<AngeboteAdapter.ItemViewHolder>() {

    private var articleCounter = 0

    //Klassen Variablen
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImageView: ImageView = view.findViewById(R.id.list_articleImage_angebote_new)
        val productText: TextView = view.findViewById(R.id.artikelDescription_angeboteDetail)
        val productPrice: TextView = view.findViewById(R.id.salePrice_angeboteDetail)
        val detailCounterA : TextView = view.findViewById(R.id.addCard_Angebot)
        val plusItemA : ImageButton = view.findViewById(R.id.plusItemAN)
        val minusItemA : ImageButton = view.findViewById(R.id.minusItemAN)
    }

    override fun getItemCount(): Int {
         return dataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_angebote_new, parent, false)
        return ItemViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        fun changeBasket(value: Int) {
            if (articleCounter == 0 && value < 0) {
                return
            } else {
                articleCounter += value
                holder.detailCounterA.text = articleCounter.toString()
            }
        }

        val saleItem = dataset[position]
        holder.productPrice.text = saleItem.price.toString()+" €"
        holder.productText.text = saleItem.productText.toString()
        holder.productImageView.setImageResource(saleItem.image)
        holder.plusItemA.setOnClickListener{
            changeBasket(1)
        }
        holder.minusItemA.setOnClickListener{
            changeBasket(-1)
        }

    }

}



