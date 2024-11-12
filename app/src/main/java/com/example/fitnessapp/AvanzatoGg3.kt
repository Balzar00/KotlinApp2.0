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

class AvanzatoGg3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_avanzato_gg3, container, false)

        val btncondividi = view.findViewById<ImageButton>(R.id.condividi)
        val prog = view.findViewById<TextView>(R.id.progr3)
        val risc = view.findViewById<TextView>(R.id.risc)
        val rec1 = view.findViewById<TextView>(R.id.recup1)
        val rec2 = view.findViewById<TextView>(R.id.recup2)
        val rec3 = view.findViewById<TextView>(R.id.recup3)
        val rec4 = view.findViewById<TextView>(R.id.recup4)
        val rec5 = view.findViewById<TextView>(R.id.recup5)

        val link1 = view.findViewById<TextView>(R.id.latMacAvtx)
        val link2 = view.findViewById<TextView>(R.id.rowingtx)
        val link3 = view.findViewById<TextView>(R.id.pulleytx)
        val link4 = view.findViewById<TextView>(R.id.rematoretx)
        val link5 = view.findViewById<TextView>(R.id.bicBtx)
        val link6 = view.findViewById<TextView>(R.id.bicCbtx)
        val link7 = view.findViewById<TextView>(R.id.diptx)
        val link8 = view.findViewById<TextView>(R.id.trictx)
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
            R.id.rematoretx to "Il <a href='https://www.youtube.com/watch?v=e1YdZdLVsmw'>Rematore alla t bar:<br></a>" +
                    "Posizionati sulla T-bar, con i piedi alla larghezza delle spalle e le ginocchia leggermente piegate.<br>" +
                    "Afferra le maniglie della T-bar con entrambe le mani, tenendo le braccia distese.<br>" +
                    "Mantieni il busto inclinato in avanti, con la schiena dritta e il petto in fuori, creando un angolo di circa 45 gradi rispetto al pavimento.<br>" +
                    "Esecuzione:<br>" +
                    "<br>" +
                    "Tira la maniglia della T-bar verso il busto, portando i gomiti indietro e vicino al corpo.<br>" +
                    "Durante il movimento, concentra la contrazione nei muscoli dorsali, cercando di avvicinare le scapole tra loro.<br>" +
                    "Mantieni una breve contrazione nella parte alta del movimento, con la barra vicino alla parte inferiore del petto o all'addome.<br>" +
                    "Rilascia lentamente il peso tornando alla posizione di partenza, estendendo le braccia senza perdere la tensione sui dorsali.",
            R.id.bicBtx to "I <a href='https://www.youtube.com/watch?v=7ECvCFpsOik'>Bicipiti col bilanciere:</a>" +
                    "<br>Se eseguita sulla panca scott bisogna prestare attenzione alla posizione delle braccia che devono essere parallele tra loro" +
                    " e al movimento del braccio che non deve un angolo piatto (180°) ma uno acuto (circa 100/120°)." +
                    "<br>Mentre se eseguiti da in piedi bisogna prestare attenzione a bloccare il motito al busto e non muoverlo durante l'esecuzione.",
            R.id.bicCbtx to "I <a href='https://www.youtube.com/watch?v=ykJmrZ5v0Oo'>Bicipiti curl coi manubri:</a> " +
                    "<br>Stessa esecuzione dei curl col bilanciere unica differenza è che non limiteremo il movimento quindi partiamo con il braccio" +
                    " a riposo e porteremo i manubri fino all'altezza delle spalle, possiamo aggiungere una rotazione alla partenza per attivare anche gli avambracci.",
            R.id.diptx to "Le <a href='https://www.youtube.com/watch?v=TrJVszDm7ik'>Dip:<br></a>" +
                    "Posizionati su parallele o su una stazione per dip, con le braccia estese e il corpo sospeso.<br>" +
                    "Fletti i gomiti abbassando lentamente il corpo fino a che le braccia non formano un angolo di 90 gradi o meno.<br>" +
                    "Spingi verso l'alto estendendo i gomiti fino a tornare alla posizione di partenza.<br>" +
                    "Mantieni il busto leggermente inclinato in avanti per coinvolgere maggiormente i pettorali, o dritto per concentrarti sui tricipiti.",
            R.id.trictx to "I <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Tricipiti ai cavi:<br></a>" +
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
            ${link3.text} ${rec4.text}
            ${link4.text} ${rec2.text}
            ${link5.text} ${rec2.text}
            ${link6.text} ${rec2.text}
            ${link7.text} ${rec4.text}
            ${link8.text} ${rec5.text}
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