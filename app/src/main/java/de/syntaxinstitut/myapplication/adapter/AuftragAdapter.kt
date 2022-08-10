package de.syntaxinstitut.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.datamodels.OrdersData
import org.w3c.dom.Text

class AuftragAdapter (
    private val dataset : List<OrdersData>,
    private val context: Context
        ): RecyclerView.Adapter<AuftragAdapter.ItemViewHolder>(){

            inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
                val auftragSendInput : TextView = view.findViewById(R.id.auftragNrInputSend)
                val datumInput : TextView = view.findViewById(R.id.datumInputSend)
                val nettoBetrag : TextView = view.findViewById(R.id.nettoBetragSend)
                val bruttoBetrag : TextView = view.findViewById(R.id.bruttoBetragSend)
                val adresseStrasse : TextView = view.findViewById(R.id.adresseInputSend)
                val adresseStadt : TextView = view.findViewById(R.id.adressePlzSend)
                val auftragButton : ImageButton = view.findViewById(R.id.auftragButton)
                val auftragListLayout : LinearLayout = view.findViewById(R.id.artikelSafeLayout)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       val adapterLayout = LayoutInflater.from(parent.context)
           .inflate(R.layout.list_item_auftrag, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]


        holder.auftragSendInput.text = item.auftragsNr.toString() + "-" + item.firma
        holder.datumInput.text = item.bestellDatum
        holder.nettoBetrag.text = String.format("%.2f",item.auftragNetto)
        holder.bruttoBetrag.text = String.format("%.2f",item.auftragBrutto)
        holder.adresseStrasse.text = item.lieferStrasse
        holder.adresseStadt.text = item.lieferOrt

        holder.auftragButton.setOnClickListener{
            if (holder.auftragListLayout.visibility == View.VISIBLE){
                holder.auftragListLayout.visibility = View.GONE
            }else{
                holder.auftragListLayout.visibility = View.VISIBLE
            }
        }

        // Gehe durch alle Artikel und füge diese dem LinearLayout hinzu
        for (article in item.articles){

            // Erstelle Items einer Zeile
            val linearLayout = TableRow(context)
            val artikelName = TextView(context)
            val artikelAnzahl = TextView(context)
            val artikelPreis = TextView(context)

            // Formattiere TextViews
            val density = context.resources.displayMetrics.density.toInt()
            artikelName.setPadding(0, density * 8, 0, density * 8)
            artikelAnzahl.setPadding(0, density * 8, 0, density * 8)
            artikelPreis.setPadding(0, density * 8, 0, density * 8)

            artikelName.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2.0f)
            artikelAnzahl.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f)
            artikelPreis.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f)

            // Bestücke TextViews mit Textinhalten
            artikelName.text = article.productText
            artikelAnzahl.text = article.quantity.toString()
            artikelPreis.text = article.price.toString()


            // Füge alles zusammen
            linearLayout.addView(artikelName)
            linearLayout.addView(artikelAnzahl)
            linearLayout.addView(artikelPreis)

            //  Füge Zeile zu Layout hinzu
            holder.auftragListLayout.addView(linearLayout)
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }


}
