package de.syntaxinstitut.myapplication.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.datamodels.KategorienData

class KategorienDetailAdapter(


    private val dataset: List<ArtikelData>, private val clickListener: (ArtikelData) -> Unit
) : RecyclerView.Adapter<KategorienDetailAdapter.ItemViewHolder>() {

    //Klassen Variablen
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val detailName = itemView.findViewById<TextView>(R.id.artikelName_detail)
        val detailPrice = itemView.findViewById<TextView>(R.id.artikelPreis_detail)
        val detailImage = itemView.findViewById<ImageView>(R.id.tomate_detail)


    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_kategorien_detail, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: KategorienDetailAdapter.ItemViewHolder, position: Int) {
        val artikel = dataset[position]
        holder.detailName.text = artikel.productText
        holder.detailImage.setImageResource(artikel.image)
        holder.detailPrice.text= artikel.price.toString()
      //  holder.categoryImage.setOnClickListener {
         //   clickListener(kategorie)
       // }

    }
}


