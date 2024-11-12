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
import androidx.appcompat.app.AlertDialog


class AvanzatoGg1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_avanzato_gg1, container, false)

        val btncondividi = view.findViewById<ImageButton>(R.id.condividi)
        val prog = view.findViewById<TextView>(R.id.prog1)
        val risc = view.findViewById<TextView>(R.id.risc)
        val rec1 = view.findViewById<TextView>(R.id.recup1)
        val rec2 = view.findViewById<TextView>(R.id.recup2)
        val rec3 = view.findViewById<TextView>(R.id.recup3)
        val rec4 = view.findViewById<TextView>(R.id.recup4)
        val rec5 = view.findViewById<TextView>(R.id.recup5)


        val link1 = view.findViewById<TextView>(R.id.squattx)
        val link2 = view.findViewById<TextView>(R.id.legpresstx)
        val link3 = view.findViewById<TextView>(R.id.legExtx)
        val link4 = view.findViewById<TextView>(R.id.legCurltx)
        val link45 = view.findViewById<TextView>(R.id.calftx)
        val link5 = view.findViewById<TextView>(R.id.shoulderPresstx)
        val link6 = view.findViewById<TextView>(R.id.alzateLattx)
        val link7 = view.findViewById<TextView>(R.id.frenchPtx)
        val link8 = view.findViewById<TextView>(R.id.trictx)
        val link9 = view.findViewById<TextView>(R.id.crunchtx)

        val linksMap = mapOf(
            R.id.squattx to "Lo <a href='https://www.youtube.com/watch?v=N2nKCnguWFo'>Squat:<br></a>" +
                    "Posizione: piedi a larghezza delle spalle, punte leggermente verso l'esterno.<br>" +
                    "Fletti le ginocchia e scendi con i fianchi indietro, mantenendo la schiena dritta e il petto sollevato.<br>" +
                    "Scendi fino a quando le cosce sono parallele al pavimento o oltre, senza sollevare i talloni.<br>" +
                    "Risali spingendo sui talloni e contrai i glutei alla fine del movimento.",
            R.id.legpresstx to "<a href='https://www.youtube.com/watch?v=uEsZWWiYNAQ'>Leg Press:</a>" +
                    "<br>Appoggiare i piedi sulla pedana alla larghezza delle anche, assicurandoti che i talloni siano piatti." +
                    "Il sedere dovrebbe essere piatto contro il sedile piuttosto che sollevato. Le gambe dovrebbero formare un angolo di circa 90 gradi alle ginocchia." +
                    "Le ginocchia dovrebbero essere in linea con i piedi e non essere né piegate né verso l’interno né verso l’esterno." +
                    "Mentre spingete, assicuratevi di mantenere tale allineamento.",
            R.id.legExtx to "La <a href='https://www.youtube.com/watch?v=4ZDm5EbiFI8'>Leg Extension:</a> <br> Posiziona il cuscinetto anteriore sulle tibie vicino alla caviglia e non a contatto coi piedi." +
                    "Le ginocchia devono posizionarsi al limite della seduta, in modo che il ginocchio non sporga eccessivamente dalla stessa ma non sia nemmeno troppo indietro." +
                    "I gradi del movimento vanno decisi in base anche ad eventuali problematiche che possiedi. Generalmente se le tue ginocchia stanno bene, potresti partire in un range compreso tra 100-120° di flessione di ginocchia." +
                    "Il movimento finisce quando le ginocchia sono in completa estensione." +
                    "I movimenti devono essere sempre controllati in ogni singola fase dell’esercizio.",
            R.id.legCurltx to "I <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Leg curl: <br></a>" +
                    "Sdraiato o in piedi sulla macchina apposita, aggancia i piedi ai cuscinetti.<br>" +
                    "Fletti le ginocchia, portando i talloni verso i glutei.<br>" +
                    "Rilascia lentamente alla posizione di partenza, controllando il movimento.",
            R.id.calftx to "I <a href='https://www.youtube.com/watch?v=TSWcyXFxdvo'>Calf: <br></a>" +
                    "In piedi, con i piedi a larghezza spalle e le punte dei piedi leggermente rivolte in avanti.<br>" +
                    "Sollevati lentamente sulle punte dei piedi, concentrandoti sulla contrazione dei polpacci.<br>" +
                    "Mantieni la posizione per un secondo nella parte alta del movimento.<br>" +
                    "Abbassati lentamente fino a tornare alla posizione di partenza, senza far toccare i talloni a terra se vuoi maggiore intensità.",
            R.id.shoulderPresstx to "La <a href='https://www.youtube.com/watch?v=UtQiS_rNg7M'>Shoulder press:<br> </a>" +
                    "In piedi o seduto, tieni i manubri o il bilanciere all'altezza delle spalle.<br>" +
                    "Spingi verso l'alto estendendo le braccia sopra la testa, mantenendo i gomiti leggermente in avanti.<br>" +
                    "Abbassa lentamente il peso fino alla posizione iniziale senza lasciare cadere le braccia troppo indietro.",
            R.id.alzateLattx to "Le <a href='https://www.youtube.com/watch?v=5Dyh1z6E6rM'>Alzate laterali:<br></a>" +
                    "In piedi, con un manubrio in ciascuna mano e le braccia lungo i fianchi.<br>" +
                    "Solleva le braccia lateralmente fino a portarle all'altezza delle spalle, mantenendo un leggero piegamento dei gomiti.<br>" +
                    "Rilascia lentamente fino a tornare alla posizione iniziale, mantenendo il controllo.",
            R.id.frenchPtx to "La <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>French press con bilanciere:<br></a>" +
                    "Sdraiati su una panca piana con il bilanciere tenuto sopra il petto.<br>" +
                    "Fletti i gomiti portando il bilanciere verso la fronte, mantenendo i gomiti fermi.<br>" +
                    "Estendi le braccia tornando alla posizione iniziale, concentrandoti sui tricipiti.",
            R.id.trictx to "I <a href='https://www.youtube.com/watch?v=89heg4k6Vps'>Tricipiti ai cavi:<br></a>" +
                    "In piedi di fronte alla macchina con cavi.<br>" +
                    "Afferra la barra con presa stretta e spingi verso il basso, estendendo completamente i gomiti.<br>" +
                    "Rilascia lentamente fino a tornare alla posizione iniziale, tenendo i gomiti fermi.",
            R.id.crunchtx to "I <a href='https://www.youtube.com/watch?v=MKmrqcoCZ-M'>Crunch:</a><br>" +
                    "Il crunch viene eseguito stendendosi in posizione supina e sollevando il busto in direzione del bacino che invece deve restare in saldo appoggio. "
        )

        val testo = """${prog.text}
            ${risc.text} ${rec1.text}
            ${link1.text} ${rec2.text}
            ${link2.text} ${rec2.text}
            ${link3.text} ${rec3.text}
            ${link4.text} ${rec3.text}
            ${link45.text} ${rec3.text}
            ${link5.text} ${rec3.text}
            ${link6.text} ${rec3.text}
            ${link7.text} ${rec4.text}
            ${link8.text} ${rec3.text}
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
        link45.setOnClickListener(linkClickListener)
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