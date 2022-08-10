package de.syntaxinstitut.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.data.datamodels.KategorienData

class KategorienAdapter(
    private val dataset: List<KategorienData>, private val clickListener: (KategorienData) -> Unit
) : RecyclerView.Adapter<KategorienAdapter.ItemViewHolder>() {

    //Klassen Variablen
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryName = itemView.findViewById<TextView>(R.id.list_Kategorie_ButtonPic_text)
        val categoryImage = itemView.findViewById<ImageView>(R.id.list_Kategorie_ButtonPic)

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_kategorien, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: KategorienAdapter.ItemViewHolder, position: Int) {
        val kategorie = dataset[position]
        holder.categoryName.text = kategorie.name
        holder.categoryImage.setImageResource(kategorie.image)
        holder.categoryImage.setOnClickListener {
            clickListener(kategorie)
        }

    }
}

