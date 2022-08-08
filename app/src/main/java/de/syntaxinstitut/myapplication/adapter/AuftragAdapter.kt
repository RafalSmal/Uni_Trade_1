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
        val users = listOf("McDonalds","Subway","Döner Kebab Berlin","Pizza Italiana","BurgerKing","Dean & David","Mr. Clou","Ali Baba & die 40 Döner","BurgerMe")
        val streets = listOf("Am Gasthof 13","Krumme Straße 30","Schimmelweg 3","Parkstraße 23","Musterstraße 11","Berliner Platz 44","Vogelweg 13","Himmelweg 41","Maschsee Straße 9")
        val places = listOf("27578 Bremerhaven","93055 Regensburg","80339 München","45127 Essen","47608 Geldern","28195 Bremen","27798 Hude","27472 Cuxhaven","53111 Bonn")


        holder.auftragSendInput.text = item.auftragsNr + "-" + users.random()
        holder.datumInput.text = item.bestellDatum
        holder.nettoBetrag.text = String.format("%.2f",item.auftragNetto)
        holder.bruttoBetrag.text = String.format("%.2f",item.auftragBrutto)
        holder.adresseStrasse.text = streets.random()
        holder.adresseStadt.text = places.random()

    }

    override fun getItemCount(): Int {
        return dataset.size
    }


}
