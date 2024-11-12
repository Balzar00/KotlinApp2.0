package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class PrincipianteGg2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_principiante_gg2, container, false)

        val btncondividi = view.findViewById<ImageButton>(R.id.condividi)
        val prog = view.findViewById<TextView>(R.id.prog2)
        val risc = view.findViewById<TextView>(R.id.risc)
        val rec1 = view.findViewById<TextView>(R.id.recup1)
        val rec2 = view.findViewById<TextView>(R.id.recup2)
        val rec3 = view.findViewById<TextView>(R.id.recup3)
        val rec4 = view.findViewById<TextView>(R.id.recup4)
        val rec5 = view.findViewById<TextView>(R.id.recup5)
        val rec6 = view.findViewById<TextView>(R.id.recup6)

        val link1 = view.findViewById<TextView>(R.id.stacSumtx)
        val link2 = view.findViewById<TextView>(R.id.staccoGTestx)
        val link3 = view.findViewById<TextView>(R.id.legCurltx)
        val link4 = view.findViewById<TextView>(R.id.latMacAvtx)
        val link5 = view.findViewById<TextView>(R.id.pulleytx)
        val link6 = view.findViewById<TextView>(R.id.rowMactx)
        val link7 = view.findViewById<TextView>(R.id.frenchPtx)
        val link8 = view.findViewById<TextView>(R.id.tricCortx)
        val link9 = view.findViewById<TextView>(R.id.russTwtx)

        val linksMap = mapOf(
            R.id.stacSumtx to "Lo <a href='https://www.youtube.com/watch?v=L2P_PqpbwSQ'>Stacco sumo:</a><br>" +
                    "Posizione: piedi più larghi delle spalle, punte dei piedi verso l'esterno.<br>" +
                    "Afferra il bilanciere con le mani tra le gambe.<br>" +
                    "Mantieni la schiena dritta e solleva il bilanciere estendendo le gambe e spingendo i fianchi in avanti.<br>" +
                    "Ritorna alla posizione iniziale piegando ginocchia e anche.",
            R.id.staccoGTestx to "Lo <a href='https://www.youtube.com/watch?v=89heg4k6Vps'> Stacco a gambe tese con i manubri: <br></a>" +
                    "In piedi, con i manubri davanti alle cosce e piedi a larghezza spalle.<br>" +
                    "Tieni le gambe quasi dritte, flettendo leggermente le ginocchia.<br>" +
                    "Scendi con i manubri lungo le gambe, mantenendo la schiena dritta, fino a sentire lo stretching nei femorali.<br>" +
                    "Risali contraendo i glutei e i femorali.",
            R.id.legCurltx to "I <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Leg curl: <br></a>" +
                    "Sdraiato o in piedi sulla macchina apposita, aggancia i piedi ai cuscinetti.<br>" +
                    "Fletti le ginocchia, portando i talloni verso i glutei.<br>" +
                    "Rilascia lentamente alla posizione di partenza, controllando il movimento.",
            R.id.latMacAvtx to "La <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Lat machine avanti:</a>" +
                    "Siediti sulla lat machine con le gambe bloccate sotto i cuscinetti.<br>" +
                    "Afferra la barra con presa larga.<br>" +
                    "Tira la barra verso il petto, contraendo i dorsali, mantenendo il busto leggermente inclinato all'indietro.<br>" +
                    "Rilascia la barra lentamente fino a tornare in alto.",
            R.id.pulleytx to "I <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Pulley:<br></a>" +
                    "Siediti di fronte alla macchina con i piedi sulle pedane.<br>" +
                    "Afferra la barra o maniglia e tira verso di te, mantenendo il busto dritto.<br>" +
                    "Tira i gomiti indietro, avvicinando le scapole.<br>" +
                    "Rilascia lentamente alla posizione di partenza.",
            R.id.rowMactx to "La <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Low rowing machine:<br></a>" +
                    "Simile al pulley, ma utilizzando una macchina con resistenza.<br>" +
                    "Afferra le maniglie e tira verso il busto, concentrandoti sulla contrazione dei dorsali e avvicinando le scapole.<br>" +
                    "Rilascia lentamente fino a tornare alla posizione iniziale.",
            R.id.frenchPtx to "La <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>French press con bilanciere:<br></a>" +
                    "Sdraiati su una panca piana con il bilanciere tenuto sopra il petto.<br>" +
                    "Fletti i gomiti portando il bilanciere verso la fronte, mantenendo i gomiti fermi.<br>" +
                    "Estendi le braccia tornando alla posizione iniziale, concentrandoti sui tricipiti.",
            R.id.tricCortx to "I <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Tricipiti ai cavi:<br></a>" +
                    "In piedi di fronte alla macchina con cavi.<br>" +
                    "Afferra la barra con presa stretta e spingi verso il basso, estendendo completamente i gomiti.<br>" +
                    "Rilascia lentamente fino a tornare alla posizione iniziale, tenendo i gomiti fermi.",
            R.id.russTwtx to "I <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Russian twist: <br></a>" +
                    "Siediti a terra con le ginocchia piegate e i piedi sollevati.<br>" +
                    "Afferra un peso (palla medica o manubrio) e ruota il busto da un lato all'altro.<br>" +
                    "Mantieni il core contratto e la schiena dritta mentre esegui la rotazione."
        )


        val testo = """${prog.text}
            ${risc.text} ${rec1.text}
            ${link1.text} ${rec2.text}
            ${link2.text} ${rec3.text}
            ${link3.text} ${rec3.text}
            ${link4.text} ${rec4.text}
            ${link5.text} ${rec4.text}
            ${link6.text} ${rec5.text}
            ${link7.text} ${rec5.text}
            ${link8.text} ${rec4.text}
            ${link9.text} ${rec6.text}""".trimIndent()

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, testo)
            type = "text/plain"
        }

        btncondividi.setOnClickListener({
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        })

        val linkClickListener = View.OnClickListener { view ->
            val linkText = linksMap[view.id]
            if (linkText != null) {
                showAlertDialogWithLink(linkText)
            }
        }

        link1.setOnClickListener(linkClickListener)
        link2.setOnClickListener(linkClickListener)
        link3.setOnClickListener(linkClickListener)
        link4.setOnClickListener(linkClickListener)
        link5.setOnClickListener(linkClickListener)
        link6.setOnClickListener(linkClickListener)
        link7.setOnClickListener(linkClickListener)
        link8.setOnClickListener(linkClickListener)
        link9.setOnClickListener(linkClickListener)

        return view
    }

    fun showAlertDialogWithLink(htmlText: String) {
        val messageTextView = TextView(requireContext())
        messageTextView.text = Html.fromHtml(htmlText)
        messageTextView.movementMethod = LinkMovementMethod.getInstance()
        messageTextView.setPadding(40, 20, 40, 20) // Padding per migliorare la leggibilità
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Spiegazione")
        alertDialog.setView(messageTextView)
        alertDialog.setPositiveButton("Chiudi") { dialog, which -> dialog.dismiss() }
        alertDialog.create()
        alertDialog.show()
        }

}