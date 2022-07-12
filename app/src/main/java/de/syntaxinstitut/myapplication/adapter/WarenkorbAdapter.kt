package de.syntaxinstitut.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.datamodels.ArtikelData

class WarenkorbAdapter(
    private val context: Context,
    private val dataset: List<ArtikelData>
) : RecyclerView.Adapter<WarenkorbAdapter.ItemViewHolder>() {

    //Klassen Variablen
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val warenkorbArtikel = itemView.findViewById<TextView>(R.id.list_warenkorb_artikel_text)
        val warenkorbAnzahl = itemView.findViewById<TextView>(R.id.list_warenkorb_quantity_text)
        val warenkorbPreis = itemView.findViewById<TextView>(R.id.list_warenkorb_price_text)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_warenkorb, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val warenkorb = dataset[position]
        holder.warenkorbArtikel.text = context.resources.getString(warenkorb.id)
        holder.warenkorbAnzahl.text = context.resources.getString(warenkorb.id)
        holder.warenkorbPreis.text = context.resources.getString(warenkorb.id)
    }
}