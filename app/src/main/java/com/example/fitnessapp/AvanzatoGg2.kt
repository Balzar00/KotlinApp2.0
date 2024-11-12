package com.example.fitnessapp


import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

class AvanzatoGg2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_avanzato_gg2, container, false)

        val btncondividi = view.findViewById<ImageButton>(R.id.condividi)
        val prog = view.findViewById<TextView>(R.id.prog2)
        val risc = view.findViewById<TextView>(R.id.risc)
        val rec1 = view.findViewById<TextView>(R.id.recup1)
        val rec2 = view.findViewById<TextView>(R.id.recup2)
        val rec3 = view.findViewById<TextView>(R.id.recup3)
        val rec4 = view.findViewById<TextView>(R.id.recup4)
        val rec5 = view.findViewById<TextView>(R.id.recup5)

        val link1 = view.findViewById<TextView>(R.id.chestPtx)
        val link2 = view.findViewById<TextView>(R.id.pancatx)
        val link3 = view.findViewById<TextView>(R.id.spinte30tx)
        val link4 = view.findViewById<TextView>(R.id.crocitx)
        val link5 = view.findViewById<TextView>(R.id.alzateLattx)
        val link6 = view.findViewById<TextView>(R.id.alzateLatCavotx)
        val link7 = view.findViewById<TextView>(R.id.bicBtx)
        val link8 = view.findViewById<TextView>(R.id.bicCbtx)
        val link9 = view.findViewById<TextView>(R.id.crunchInvtx)

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
            R.id.crocitx to "Le <a href='https://www.youtube.com/watch?v=AY9pI9ANxs8'>Croci con la panca a 30°:<br></a>" +
                    "L'esecutore si posiziona su panca piana inclinata a 30# impugnando i manubri solitamente con una presa neutra con i piedi fissati al suolo." +
                    "Durante il movimento, i gomiti sono leggermente flessi e bloccati, ciò significa che non devono variare la loro lunghezza ed essere mobilizzati durante l'esecuzione." +
                    "Si alzano i manubri in vericale e facciamo un movimento ad aprire e poi chiudere, cercado di controllarli entrambi.",
            R.id.alzateLattx to "Le <a href='https://www.youtube.com/watch?v=5Dyh1z6E6rM'>Alzate laterali:<br></a>" +
                    "In piedi, con un manubrio in ciascuna mano e le braccia lungo i fianchi.<br>" +
                    "Solleva le braccia lateralmente fino a portarle all'altezza delle spalle, mantenendo un leggero piegamento dei gomiti.<br>" +
                    "Rilascia lentamente fino a tornare alla posizione iniziale, mantenendo il controllo.",
            R.id.alzateLatCavotx to "Le <a href='https://www.youtube.com/shorts/6Z-wuEf04ZQ'>Alzate laterali al cavo:<br></a>" +
                    "Posizionati di lato rispetto alla macchina con cavi.<br>" +
                    "Afferra la maniglia del cavo con la mano opposta rispetto al lato della macchina (se sei di fronte alla macchina con il lato destro, usa la mano sinistra e viceversa).<br>" +
                    "Mantieni una leggera inclinazione laterale verso la macchina e tieni l’altra mano sull’anca o su un supporto per maggiore stabilità.<br>" +
                    "Esecuzione:<br>" +
                    "<br>" +
                    "Tira il cavo sollevando il braccio lateralmente fino a portarlo all'altezza della spalla, mantenendo il gomito leggermente piegato e la mano in linea con il gomito.<br>" +
                    "Evita di ruotare il polso durante il movimento, mantenendo la mano in posizione neutra (come se stessi versando acqua da una brocca).<br>" +
                    "Fai una pausa in alto, sentendo la contrazione nel deltoide laterale (spalla).<br>" +
                    "Abbassa lentamente il braccio fino alla posizione di partenza, controllando il movimento per tutta la discesa.",
            R.id.bicBtx to "I <a href='https://www.youtube.com/watch?v=7ECvCFpsOik'>Bicipiti col bilanciere:</a>" +
                    "<br>Se eseguita sulla panca scott bisogna prestare attenzione alla posizione delle braccia che devono essere parallele tra loro" +
                    " e al movimento del braccio che non deve un angolo piatto (180°) ma uno acuto (circa 100/120°)." +
                    "<br>Mentre se eseguiti da in piedi bisogna prestare attenzione a bloccare il motito al busto e non muoverlo durante l'esecuzione.",
            R.id.bicCbtx to "I <a href='https://www.youtube.com/watch?v=ykJmrZ5v0Oo'>Bicipiti curl coi manubri:</a> " +
                    "<br>Stessa esecuzione dei curl col bilanciere unica differenza è che non limiteremo il movimento quindi partiamo con il braccio" +
                    " a riposo e porteremo i manubri fino all'altezza delle spalle, possiamo aggiungere una rotazione alla partenza per attivare anche gli avambracci.",
            R.id.crunchInvtx to "I <a href='https://www.youtube.com/watch?v=2UMZfWtY4mY'>Crunch inversi:<br></a>" +
                    "Sdraiato sulla schiena, con le gambe piegate e i piedi sollevati da terra.<br>" +
                    "Porta le ginocchia verso il petto, sollevando il bacino dal pavimento e contrai gli addominali.<br>" +
                    "Rilascia lentamente abbassando le gambe senza far toccare i piedi a terra, mantenendo il controllo del movimento.",
        )

        val testo = """${prog.text}
            ${risc.text} ${rec1.text}
            ${link1.text} ${rec2.text}
            ${link2.text} ${rec2.text}
            ${link3.text} ${rec3.text}
            ${link4.text} ${rec2.text}
            ${link5.text} ${rec2.text}
            ${link6.text} ${rec2.text}
            ${link7.text} ${rec2.text}
            ${link8.text} ${rec4.text}
            ${link9.text} ${rec5.text}""".trimIndent()

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