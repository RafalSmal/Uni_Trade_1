package de.syntaxinstitut.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.datamodels.Artikel

class AngeboteAdapter(
    private val context: Context,
    private val dataset: List<Artikel>
) : RecyclerView.Adapter<AngeboteAdapter.ItemViewHolder>() {

    //Klassen Variablen
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImageButton: ImageView = view.findViewById(R.id.list_articleImage_angebote_new)
        val productText: TextView = view.findViewById(R.id.artikelDescription_angeboteDetail)
        val productPrice: TextView = view.findViewById((R.id.salePrice_angeboteDetail))
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
        holder.productPrice.text = saleItem.price.toString()
        holder.productText.text = saleItem.productText.toString()
        holder.productImageButton.setOnClickListener{

        }
    }
}



