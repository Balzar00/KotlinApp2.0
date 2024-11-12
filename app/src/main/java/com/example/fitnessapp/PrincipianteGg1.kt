package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class PrincipianteGg1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_principiante_gg1, container, false)

        val btncondividi = view.findViewById<ImageButton>(R.id.condividi)
        val prog = view.findViewById<TextView>(R.id.programma)
        val risc = view.findViewById<TextView>(R.id.risc)
        val rec1 = view.findViewById<TextView>(R.id.recup1)
        val rec2 = view.findViewById<TextView>(R.id.recup2)
        val rec3 = view.findViewById<TextView>(R.id.recup3)
        val rec4 = view.findViewById<TextView>(R.id.recup4)

        val link1 = view.findViewById<TextView>(R.id.legpresstx)
        val link2 = view.findViewById<TextView>(R.id.legEx)
        val link3 = view.findViewById<TextView>(R.id.affonditx)
        val link4 = view.findViewById<TextView>(R.id.pancatx)
        val link5 = view.findViewById<TextView>(R.id.crocitx)
        val link6 = view.findViewById<TextView>(R.id.chestPtx)
        val link7 = view.findViewById<TextView>(R.id.bicBtx)
        val link8 = view.findViewById<TextView>(R.id.bicCbtx)
        val link9 = view.findViewById<TextView>(R.id.crunchtx)

        val linksMap = mapOf(
            R.id.legpresstx to "<a href=''https://www.youtube.com/watch?v=uEsZWWiYNAQ>Leg Press:</a>" +
                    "<br>Appoggiare i piedi sulla pedana alla larghezza delle anche, assicurandoti che i talloni siano piatti." +
                    "Il sedere dovrebbe essere piatto contro il sedile piuttosto che sollevato. Le gambe dovrebbero formare un angolo di circa 90 gradi alle ginocchia." +
                    "Le ginocchia dovrebbero essere in linea con i piedi e non essere né piegate né verso l’interno né verso l’esterno." +
                    "Mentre spingete, assicuratevi di mantenere tale allineamento.",
            R.id.legEx to "La <a href='https://www.youtube.com/watch?v=4ZDm5EbiFI8'>Leg Extension:</a> <br> Posiziona il cuscinetto anteriore sulle tibie vicino alla caviglia e non a contatto coi piedi." +
                    "Le ginocchia devono posizionarsi al limite della seduta, in modo che il ginocchio non sporga eccessivamente dalla stessa ma non sia nemmeno troppo indietro." +
                    "I gradi del movimento vanno decisi in base anche ad eventuali problematiche che possiedi. Generalmente se le tue ginocchia stanno bene, potresti partire in un range compreso tra 100-120° di flessione di ginocchia." +
                    "Il movimento finisce quando le ginocchia sono in completa estensione." +
                    "I movimenti devono essere sempre controllati in ogni singola fase dell’esercizio.",
            R.id.affonditx to "<a href='https://www.youtube.com/watch?v=Cbqjuj3N7Zo'>Affondi:</a><br>Gli affondi si eseguono in una posizione di partenza eretta, " +
                    "con i piedi ben saldi a terra e le mani sui fianchi oppure lungo i fianchi. " +
                    "Dopo aver assunto la posizione corretta, si esegue un lungo passo o avanti, o indietro o laterale e si flette un ginocchio, mantenendo la parte superiore del corpo ben dritta.",
            R.id.pancatx to "La <a href='https://www.youtube.com/watch?v=abkLsC0HEjg'>Panca Piana</a><br>" +
                    "Ci si stende su una panca, si afferra il bilanciere dagli appoggi, si stendono le braccia, " +
                    "si porta il bilanciere al petto per spingerlo via fino a tornare nella posizione iniziale",
            R.id.crocitx to "Le <a href='https://www.youtube.com/watch?v=AY9pI9ANxs8'>Croci con la panca a 30°:<br></a>" +
                    "L'esecutore si posiziona su panca piana inclinata a 30# impugnando i manubri solitamente con una presa neutra con i piedi fissati al suolo." +
                    "Durante il movimento, i gomiti sono leggermente flessi e bloccati, ciò significa che non devono variare la loro lunghezza ed essere mobilizzati durante l'esecuzione." +
                    "Si alzano i manubri in vericale e facciamo un movimento ad aprire e poi chiudere, cercado di controllarli entrambi.",
            R.id.chestPtx to "La <a href='https://www.youtube.com/watch?v=sqNwDkUU_Ps'>Chest press:</a><br>" +
                    "Per eseguirla bene ecco delle linee guida: non devi muoverti con l’intero corpo, ma solo distendere le braccia," +
                    "devi mantenere la schiena poggiata sullo schienale," +
                    "devi tenere addotte e depresse le scapole," +
                    "non devi spingere in avanti le scapole," +
                    "devi completare il movimento di distensione delle braccia," +
                    "utilizza pieno controllo motorio per l’intera esecuzione," +
                    "la testa non deve venire in avanti, ma rimanere appoggiata allo schienale," +
                    "i gomiti NON vanno tenuti ALTI, come si sente spesso dire.",
            R.id.bicBtx to "I <a href='https://www.youtube.com/watch?v=7ECvCFpsOik'>Bicipiti col bilanciere:</a>" +
                    "<br>Se eseguita sulla panca scott bisogna prestare attenzione alla posizione delle braccia che devono essere parallele tra loro" +
                    " e al movimento del braccio che non deve un angolo piatto (180°) ma uno acuto (circa 100/120°)." +
                    "<br>Mentre se eseguiti da in piedi bisogna prestare attenzione a bloccare il motito al busto e non muoverlo durante l'esecuzione.",
            R.id.bicCbtx to "I <a href='https://www.youtube.com/watch?v=ykJmrZ5v0Oo'>Bicipiti curl coi manubri:</a> " +
                    "<br>Stessa esecuzione dei curl col bilanciere unica differenza è che non limiteremo il movimento quindi partiamo con il braccio" +
                    " a riposo e porteremo i manubri fino all'altezza delle spalle, possiamo aggiungere una rotazione alla partenza per attivare anche gli avambracci.",
            R.id.crunchtx to "I <a href='https://www.youtube.com/watch?v=MKmrqcoCZ-M'>Crunch:</a><br>" +
                    "Il crunch viene eseguito stendendosi in posizione supina e sollevando il busto in direzione del bacino che invece deve restare in saldo appoggio. "
        )

        // per l'invio delle schede
        val testo = """${prog.text}
            ${risc.text} ${rec1.text}
            ${link1.text} ${rec2.text}
            ${link2.text} ${rec2.text}
            ${link3.text} ${rec2.text}
            ${link4.text} ${rec3.text}
            ${link5.text} ${rec2.text}
            ${link6.text} ${rec2.text}
            ${link7.text} ${rec2.text}
            ${link8.text} ${rec2.text}
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

        // questa funzione prende dalla linksmap (tramite id) i  rispettivi testi con i link in html
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

    // A seconda del valore htmltext passato l'alertdialog avrà il rispettivo "contenuto"
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