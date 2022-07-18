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
    private val dataset: List<ArtikelData>,
    private val clickListener: (ArtikelData,Boolean) -> Unit

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

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_angebote_new, parent, false)
        return ItemViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val saleItem = dataset[position]

        fun changeBasket(value: Int) {
            if (saleItem.quantity == value && value < 0) {
                return
            } else {
                saleItem.quantity += value
                holder.detailCounterA.text = saleItem.quantity.toString()
            }
        }


        holder.productPrice.text = saleItem.price.toString() + " €"
        holder.productText.text = saleItem.productText.toString()
        holder.productImageView.setImageResource(saleItem.image)
        holder.detailCounterA.text = saleItem.quantity.toString()
        holder.plusItemA.setOnClickListener {
            changeBasket(1)
            clickListener(saleItem,true)
        }
        holder.minusItemA.setOnClickListener {
            changeBasket(-1)
            if (saleItem.quantity == 0 ){
                clickListener(saleItem,false)
            }else{
                clickListener(saleItem,true)
            }
        }

    }

}



