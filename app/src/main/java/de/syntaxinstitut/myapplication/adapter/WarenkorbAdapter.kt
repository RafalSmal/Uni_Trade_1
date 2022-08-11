package de.syntaxinstitut.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.data.datamodels.ArtikelData

class WarenkorbAdapter(
    private val context: Context,
    private val clickListener: (ArtikelData,Boolean ) -> Unit
) : RecyclerView.Adapter<WarenkorbAdapter.ItemViewHolder>() {

    //Klassen Variablen

    var dataset: List<ArtikelData> = emptyList()


    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val warenkorbArtikel = itemView.findViewById<TextView>(R.id.list_warenkorb_artikel_text)
        val warenkorbAnzahl = itemView.findViewById<TextView>(R.id.list_warenkorb_quantity_text)
        val warenkorbPreis = itemView.findViewById<TextView>(R.id.list_warenkorb_price_text)
        val warenkorbPic = itemView.findViewById<ImageView>(R.id.warenkorbPic)
        val addCardWk = itemView.findViewById<ImageView>(R.id.addCardWk)
        val deleteCardWk = itemView.findViewById<ImageView>(R.id.deleteCardWk)
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
        holder.setIsRecyclable(false)

        val warenkorb = dataset[position]
        val imageUri = warenkorb.image.toUri().buildUpon().scheme("https").build()

        holder.warenkorbArtikel.text = warenkorb.productText
        holder.warenkorbAnzahl.text = warenkorb.quantity.toString()
        holder.warenkorbPreis.text = String.format("%.2f",warenkorb.price!! * warenkorb.quantity) + " €"
        holder.warenkorbPic.load(imageUri) {
            transformations(RoundedCornersTransformation(10f))
        }

        fun changeBasket(value: Int) {
            if (warenkorb.quantity == 0 && value <= 0) {
                clickListener(warenkorb,false)
            } else {
                warenkorb.quantity += value
                holder.warenkorbAnzahl.text = warenkorb.quantity.toString()
                clickListener(warenkorb,true)
            }
        }

        holder.addCardWk.setOnClickListener {
            changeBasket(1)
            clickListener(warenkorb, true)
        }
        holder.deleteCardWk.setOnClickListener {
            changeBasket(-1)
            if (warenkorb.quantity == 0) {
                clickListener(warenkorb, false)
            } else {
                clickListener(warenkorb, true)
            }
        }

    }

    fun update(list: List<ArtikelData>){
        this.dataset = list

        notifyDataSetChanged()
    }
}

