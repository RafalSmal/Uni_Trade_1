package de.syntaxinstitut.myapplication.adapter


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

class KategorienDetailAdapter(

    private val dataset: List<ArtikelData>,
    private val basket : List<ArtikelData>,
    private val clickListener: (ArtikelData, Boolean) -> Unit
) : RecyclerView.Adapter<KategorienDetailAdapter.ItemViewHolder>() {


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
            if (item.quantity == 0 && value <= 0) {
                clickListener(item,false)
            } else {
                item.quantity = value
                holder.detailCounterKD.text = item.quantity.toString()
                clickListener(item,true)
            }
        }


        val artikel = dataset[position]
        holder.detailName.text = artikel.productText

        holder.detailPrice.text = artikel.price.toString() + " €"

        for (i in basket){
            if (i.productText == item.productText ){
                item.quantity = i.quantity
            }
        }

        changeBasket(artikel.quantity)
        holder.addCardKD.setOnClickListener {
            changeBasket(item.quantity + 1)
            clickListener(item,true)
        }

        val imageUri = artikel.image.toUri().buildUpon().scheme("https").build()

        holder.detailImage.load(imageUri) {
            transformations(RoundedCornersTransformation(10f))
        }


        holder.deleteCardKD.setOnClickListener {
            changeBasket(item.quantity -1 )
            if (item.quantity == 0) {
                clickListener(item,false)
            } else {
                clickListener(item, true)
            }

        }


    }
}

