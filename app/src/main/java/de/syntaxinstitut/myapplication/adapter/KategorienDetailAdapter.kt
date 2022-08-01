package de.syntaxinstitut.myapplication.adapter


import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.datamodels.KategorienData

class KategorienDetailAdapter(

    private val dataset: List<ArtikelData>,
    private val clickListener: (ArtikelData, Boolean) -> Unit
) : RecyclerView.Adapter<KategorienDetailAdapter.ItemViewHolder>() {

    private var articleCounter = 0


    //Klassen Variablen
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val detailName = itemView.findViewById<TextView>(R.id.artikelName_detail)
        val detailPrice = itemView.findViewById<TextView>(R.id.artikelPreis_detail)
        val detailImage = itemView.findViewById<ImageView>(R.id.tomate_detail)
        val addCardKD = itemView.findViewById<ImageButton>(R.id.addCardKD)
        val deleteCardKD = itemView.findViewById<ImageButton>(R.id.outCardKD)
        val detailCounterKD = itemView.findViewById<TextView>(R.id.counterKD)


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

        val item = dataset[position]
        fun changeBasket(value: Int) {
            if (item.quantity == 0 && value < 0) {
                return
            } else {
                item.quantity += value
                holder.detailCounterKD.text = articleCounter.toString()
            }
        }


        val artikel = dataset[position]
        holder.detailName.text = artikel.productText
       // holder.detailImage.setImageResource(Int)
        holder.detailPrice.text = artikel.price.toString() + " €"
        holder.detailCounterKD.text = artikel.quantity.toString()
        holder.addCardKD.setOnClickListener {
            changeBasket(1)
            clickListener(item,true)
        }

        holder.deleteCardKD.setOnClickListener {
            changeBasket(-1)
            if (item.quantity == 0) {
                clickListener(item,false)
                Log.d("Hallo","Bitte")
            } else {
                clickListener(item, true)
                Log.d("Hallo","Why")
            }

        }


    }
}

