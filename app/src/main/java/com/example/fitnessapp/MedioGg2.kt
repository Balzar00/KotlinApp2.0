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

class MedioGg2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_medio_gg2, container, false)

        val btncondividi = view.findViewById<ImageButton>(R.id.condividi)
        val prog = view.findViewById<TextView>(R.id.prog2)
        val risc = view.findViewById<TextView>(R.id.risc)
        val rec1 = view.findViewById<TextView>(R.id.recup1)
        val rec2 = view.findViewById<TextView>(R.id.recup2)
        val rec3 = view.findViewById<TextView>(R.id.recup3)
        val rec4 = view.findViewById<TextView>(R.id.recup4)

        val link1 = view.findViewById<TextView>(R.id.latMacAvtx)
        val link2 = view.findViewById<TextView>(R.id.rowingtx)
        val link3 = view.findViewById<TextView>(R.id.pulleytx)
        val link4 = view.findViewById<TextView>(R.id.trictx)
        val link5 = view.findViewById<TextView>(R.id.frPrtx)
        val link6 = view.findViewById<TextView>(R.id.alzateLattx)
        val link7 = view.findViewById<TextView>(R.id.alzateSintx)
        val link8 = view.findViewById<TextView>(R.id.sitUpstx)
        val link9 = view.findViewById<TextView>(R.id.russTwtx)

        val linksMap = mapOf(
            R.id.latMacAvtx to "La <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Lat machine avanti:</a>" +
                    "Siediti sulla lat machine con le gambe bloccate sotto i cuscinetti.<br>" +
                    "Afferra la barra con presa larga.<br>" +
                    "Tira la barra verso il petto, contraendo i dorsali, mantenendo il busto leggermente inclinato all'indietro.<br>" +
                    "Rilascia la barra lentamente fino a tornare in alto.",
            R.id.rowingtx to "La <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Low rowing machine:<br></a>" +
                    "Simile al pulley, ma utilizzando una macchina con resistenza.<br>" +
                    "Afferra le maniglie e tira verso il busto, concentrandoti sulla contrazione dei dorsali e avvicinando le scapole.<br>" +
                    "Rilascia lentamente fino a tornare alla posizione iniziale.",
            R.id.pulleytx to "I <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Pulley:<br></a>" +
                    "Siediti di fronte alla macchina con i piedi sulle pedane.<br>" +
                    "Afferra la barra o maniglia e tira verso di te, mantenendo il busto dritto.<br>" +
                    "Tira i gomiti indietro, avvicinando le scapole.<br>" +
                    "Rilascia lentamente alla posizione di partenza.",
            R.id.trictx to "I <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Tricipiti ai cavi:<br></a>" +
                    "In piedi di fronte alla macchina con cavi.<br>" +
                    "Afferra la barra con presa stretta e spingi verso il basso, estendendo completamente i gomiti.<br>" +
                    "Rilascia lentamente fino a tornare alla posizione iniziale, tenendo i gomiti fermi.",
            R.id.frPrtx to "La <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>French press con bilanciere:<br></a>" +
                    "Sdraiati su una panca piana con il bilanciere tenuto sopra il petto.<br>" +
                    "Fletti i gomiti portando il bilanciere verso la fronte, mantenendo i gomiti fermi.<br>" +
                    "Estendi le braccia tornando alla posizione iniziale, concentrandoti sui tricipiti.",
            R.id.alzateLattx to "Le <a href='https://www.youtube.com/watch?v=5Dyh1z6E6rM'>Alzate laterali:<br></a>" +
                    "In piedi, con un manubrio in ciascuna mano e le braccia lungo i fianchi.<br>" +
                    "Solleva le braccia lateralmente fino a portarle all'altezza delle spalle, mantenendo un leggero piegamento dei gomiti.<br>" +
                    "Rilascia lentamente fino a tornare alla posizione iniziale, mantenendo il controllo.",
            R.id.alzateSintx to "Le <a href='https://www.youtube.com/watch?v=nwBSKOpMOdo'>Alzate laterali singole inclinate:</a>" +
                    "Posizione: inclinati in avanti con il busto quasi parallelo al pavimento, un braccio appoggiato su una panca per supporto.<br>" +
                    "Con l'altro braccio, solleva un manubrio lateralmente, concentrandoti sul deltoide posteriore.<br>" +
                    "Mantieni il gomito leggermente flesso e controlla il movimento sia in salita che in discesa.",
            R.id.sitUpstx to "I <a href='https://www.youtube.com/watch?v=jDwoBqPH0jk'>Sit ups:</a>" +
                    "Sdraiato sulla schiena con le ginocchia piegate e i piedi piatti a terra.<br>" +
                    "Metti le mani dietro la testa o incrocia le braccia sul petto.<br>" +
                    "Solleva il busto fino a sederti, contrarre gli addominali, poi torna lentamente alla posizione di partenza.",
            R.id.russTwtx to "I <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Russian twist: <br></a>" +
                    "Siediti a terra con le ginocchia piegate e i piedi sollevati.<br>" +
                    "Afferra un peso (palla medica o manubrio) e ruota il busto da un lato all'altro.<br>" +
                    "Mantieni il core contratto e la schiena dritta mentre esegui la rotazione."
        )

        val testo = """${prog.text}
            ${risc.text} ${rec1.text}
            ${link1.text} ${rec2.text}
            ${link2.text} ${rec2.text}
            ${link3.text} ${rec2.text}
            ${link4.text} ${rec2.text}
            ${link5.text} ${rec3.text}
            ${link6.text} ${rec2.text}
            ${link7.text} ${rec3.text}
            ${link8.text} ${rec4.text}
            ${link9.text} ${rec4.text}""".trimIndent()

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