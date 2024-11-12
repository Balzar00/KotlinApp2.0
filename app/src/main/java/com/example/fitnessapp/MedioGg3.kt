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


class MedioGg3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_medio_gg3, container, false)

        val btncondividi = view.findViewById<ImageButton>(R.id.condividi)
        val prog = view.findViewById<TextView>(R.id.prog3)
        val risc = view.findViewById<TextView>(R.id.risc)
        val rec1 = view.findViewById<TextView>(R.id.recup1)
        val rec2 = view.findViewById<TextView>(R.id.recup2)
        val rec3 = view.findViewById<TextView>(R.id.recup3)

        val link1 = view.findViewById<TextView>(R.id.chestPtx)
        val link2 = view.findViewById<TextView>(R.id.pancatx)
        val link3 = view.findViewById<TextView>(R.id.spinte30tx)
        val link4 = view.findViewById<TextView>(R.id.alzateLattx)
        val link5 = view.findViewById<TextView>(R.id.shoulderPresstx)
        val link6 = view.findViewById<TextView>(R.id.bicCbtx)
        val link7 = view.findViewById<TextView>(R.id.bicBtx)
        val link8 = view.findViewById<TextView>(R.id.trictx)
        val link9 = view.findViewById<TextView>(R.id.diptx)

        val linksMap = mapOf(
            R.id.chestPtx to "La <a href='https://www.youtube.com/watch?v=sqNwDkUU_Ps'>Chest press:</a><br>" +
                    "Per eseguirla bene ecco delle linee guida: non devi muoverti con l’intero corpo, ma solo distendere le braccia," +
                    "devi mantenere la schiena poggiata sullo schienale," +
                    "devi tenere addotte e depresse le scapole," +
                    "non devi spingere in avanti le scapole," +
                    "devi completare il movimento di distensione delle braccia," +
                    "utilizza pieno controllo motorio per l’intera esecuzione," +
                    "la testa non deve venire in avanti, ma rimanere appoggiata allo schienale," +
                    "i gomiti NON vanno tenuti ALTI, come si sente spesso dire.",
            R.id.pancatx to "La <a href='https://www.youtube.com/watch?v=abkLsC0HEjg'>Panca Piana</a><br>" +
                    "Ci si stende su una panca, si afferra il bilanciere dagli appoggi, si stendono le braccia, " +
                    "si porta il bilanciere al petto per spingerlo via fino a tornare nella posizione iniziale",
            R.id.spinte30tx to "Le <a href='https://www.youtube.com/watch?v=2mvWe_8jhMk'>Spinte su panca a 30°:</a><br>" +
                    "Posizionati su una panca inclinata a 30 gradi, con manubri o bilanciere in mano.<br>" +
                    "Afferra i manubri (o il bilanciere) con le mani all'altezza del petto, i gomiti piegati e leggermente verso il basso.<br>" +
                    "Spingi i pesi verso l'alto estendendo le braccia, mantenendo i gomiti leggermente piegati alla fine del movimento.<br>" +
                    "Torna alla posizione iniziale lentamente, controllando la discesa e mantenendo una buona attivazione dei pettorali superiori.",
            R.id.alzateLattx to "Le <a href='https://www.youtube.com/watch?v=5Dyh1z6E6rM'>Alzate laterali:<br></a>" +
                    "In piedi, con un manubrio in ciascuna mano e le braccia lungo i fianchi.<br>" +
                    "Solleva le braccia lateralmente fino a portarle all'altezza delle spalle, mantenendo un leggero piegamento dei gomiti.<br>" +
                    "Rilascia lentamente fino a tornare alla posizione iniziale, mantenendo il controllo.",
            R.id.shoulderPresstx to "La <a href='https://www.youtube.com/watch?v=UtQiS_rNg7M'>Shoulder press:<br> </a>" +
                    "In piedi o seduto, tieni i manubri o il bilanciere all'altezza delle spalle.<br>" +
                    "Spingi verso l'alto estendendo le braccia sopra la testa, mantenendo i gomiti leggermente in avanti.<br>" +
                    "Abbassa lentamente il peso fino alla posizione iniziale senza lasciare cadere le braccia troppo indietro.",
            R.id.bicCbtx to "I <a href='https://www.youtube.com/watch?v=ykJmrZ5v0Oo'>Bicipiti curl coi manubri:</a> " +
                    "<br>Stessa esecuzione dei curl col bilanciere unica differenza è che non limiteremo il movimento quindi partiamo con il braccio" +
                    " a riposo e porteremo i manubri fino all'altezza delle spalle, possiamo aggiungere una rotazione alla partenza per attivare anche gli avambracci.",
            R.id.bicBtx to "I <a href='https://www.youtube.com/watch?v=7ECvCFpsOik'>Bicipiti col bilanciere:</a>" +
                    "<br>Se eseguita sulla panca scott bisogna prestare attenzione alla posizione delle braccia che devono essere parallele tra loro" +
                    " e al movimento del braccio che non deve un angolo piatto (180°) ma uno acuto (circa 100/120°)." +
                    "<br>Mentre se eseguiti da in piedi bisogna prestare attenzione a bloccare il motito al busto e non muoverlo durante l'esecuzione.",
            R.id.trictx to "I <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Tricipiti ai cavi:<br></a>" +
                    "In piedi di fronte alla macchina con cavi.<br>" +
                    "Afferra la barra con presa stretta e spingi verso il basso, estendendo completamente i gomiti.<br>" +
                    "Rilascia lentamente fino a tornare alla posizione iniziale, tenendo i gomiti fermi.",
            R.id.diptx to "Le <a href='https://www.youtube.com/watch?v=TrJVszDm7ik'>Dip:<br></a>" +
                    "Posizionati su parallele o su una stazione per dip, con le braccia estese e il corpo sospeso.<br>" +
                    "Fletti i gomiti abbassando lentamente il corpo fino a che le braccia non formano un angolo di 90 gradi o meno.<br>" +
                    "Spingi verso l'alto estendendo i gomiti fino a tornare alla posizione di partenza.<br>" +
                    "Mantieni il busto leggermente inclinato in avanti per coinvolgere maggiormente i pettorali, o dritto per concentrarti sui tricipiti."
        )

        val testo = """${prog.text}
            ${risc.text} ${rec1.text}
            ${link1.text} ${rec2.text}
            ${link2.text} ${rec2.text}
            ${link3.text} ${rec3.text}
            ${link4.text} ${rec2.text}
            ${link5.text} ${rec3.text}
            ${link6.text} ${rec2.text}
            ${link7.text} ${rec3.text}
            ${link8.text} ${rec2.text}
            ${link9.text} ${rec2.text}""".trimIndent()

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