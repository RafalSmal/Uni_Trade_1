package de.syntaxinstitut.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.datamodels.OrdersData
import org.w3c.dom.Text

class AuftragAdapter (
    private val dataset : List<OrdersData>
        ): RecyclerView.Adapter<AuftragAdapter.ItemViewHolder>(){

            inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
                val auftragSendInput : TextView = view.findViewById(R.id.auftragNrInputSend)
                val datumInput : TextView = view.findViewById(R.id.datumInputSend)
                val nettoBetrag : TextView = view.findViewById(R.id.nettoBetragSend)
                val bruttoBetrag : TextView = view.findViewById(R.id.bruttoBetragSend)
                val adresseStrasse : TextView = view.findViewById(R.id.adresseInputSend)
                val adresseStadt : TextView = view.findViewById(R.id.adressePlzSend)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       val adapterLayout = LayoutInflater.from(parent.context)
           .inflate(R.layout.list_item_auftrag, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.auftragSendInput.text = item.auftragsNr
        holder.datumInput.text = item.bestellDatum
        holder.nettoBetrag.text = item.auftragNetto.toString()
        holder.bruttoBetrag.text = item.auftragBrutto.toString()
        holder.adresseStrasse.text = item.lieferStrasse
        holder.adresseStadt.text = item.lieferOrt

    }

    override fun getItemCount(): Int {
        return dataset.size
    }


}
