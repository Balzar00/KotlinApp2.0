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


class PrincipianteGg3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Espandi il layout per questo frammento
        val view = inflater.inflate(R.layout.fragment_principiante_gg3, container, false)

        val btncondividi = view.findViewById<ImageButton>(R.id.condividi)
        val prog = view.findViewById<TextView>(R.id.prog3)
        val risc = view.findViewById<TextView>(R.id.risc)
        val rec1 = view.findViewById<TextView>(R.id.recup1)
        val rec2 = view.findViewById<TextView>(R.id.recup2)
        val rec3 = view.findViewById<TextView>(R.id.recup3)
        val rec4 = view.findViewById<TextView>(R.id.recup4)
        val rec5 = view.findViewById<TextView>(R.id.recup5)

        val link1 = view.findViewById<TextView>(R.id.legpresstx)
        val link2 = view.findViewById<TextView>(R.id.squattx)
        val link3 = view.findViewById<TextView>(R.id.legExtx)
        val link4 = view.findViewById<TextView>(R.id.affonditx)
        val link5 = view.findViewById<TextView>(R.id.shoulderPresstx)
        val link6 = view.findViewById<TextView>(R.id.alzateLattx)
        val link7 = view.findViewById<TextView>(R.id.alzateSintx)
        val link8 = view.findViewById<TextView>(R.id.crunchInvtx)
        val link9 = view.findViewById<TextView>(R.id.sitUpstx)

        val linksMap = mapOf(
            R.id.legpresstx to "<a href='https://www.youtube.com/watch?v=uEsZWWiYNAQ'>Leg Press:</a>" +
                    "<br>Appoggiare i piedi sulla pedana alla larghezza delle anche, assicurandoti che i talloni siano piatti." +
                    "Il sedere dovrebbe essere piatto contro il sedile piuttosto che sollevato. Le gambe dovrebbero formare un angolo di circa 90 gradi alle ginocchia." +
                    "Le ginocchia dovrebbero essere in linea con i piedi e non essere né piegate né verso l’interno né verso l’esterno." +
                    "Mentre spingete, assicuratevi di mantenere tale allineamento.",
            R.id.squattx to "Lo <a href='https://www.youtube.com/watch?v=N2nKCnguWFo'>Squat:<br></a>" +
                    "Posizione: piedi a larghezza delle spalle, punte leggermente verso l'esterno.<br>" +
                    "Fletti le ginocchia e scendi con i fianchi indietro, mantenendo la schiena dritta e il petto sollevato.<br>" +
                    "Scendi fino a quando le cosce sono parallele al pavimento o oltre, senza sollevare i talloni.<br>" +
                    "Risali spingendo sui talloni e contrai i glutei alla fine del movimento.",
            R.id.legEx to "La <a href='https://www.youtube.com/watch?v=4ZDm5EbiFI8'>Leg Extension:</a> <br> Posiziona il cuscinetto anteriore sulle tibie vicino alla caviglia e non a contatto coi piedi." +
                    "Le ginocchia devono posizionarsi al limite della seduta, in modo che il ginocchio non sporga eccessivamente dalla stessa ma non sia nemmeno troppo indietro." +
                    "I gradi del movimento vanno decisi in base anche ad eventuali problematiche che possiedi. Generalmente se le tue ginocchia stanno bene, potresti partire in un range compreso tra 100-120° di flessione di ginocchia." +
                    "Il movimento finisce quando le ginocchia sono in completa estensione." +
                    "I movimenti devono essere sempre controllati in ogni singola fase dell’esercizio.",
            R.id.affonditx to "<a href='https://www.youtube.com/watch?v=Cbqjuj3N7Zo'>Affondi:</a><br>Gli affondi si eseguono in una posizione di partenza eretta, " +
                    "con i piedi ben saldi a terra e le mani sui fianchi oppure lungo i fianchi. " +
                    "Dopo aver assunto la posizione corretta, si esegue un lungo passo o avanti, o indietro o laterale e si flette un ginocchio, mantenendo la parte superiore del corpo ben dritta.",
            R.id.shoulderPresstx to "La <a href='https://www.youtube.com/watch?v=UtQiS_rNg7M'>Shoulder press:<br> </a>" +
                    "In piedi o seduto, tieni i manubri o il bilanciere all'altezza delle spalle.<br>" +
                    "Spingi verso l'alto estendendo le braccia sopra la testa, mantenendo i gomiti leggermente in avanti.<br>" +
                    "Abbassa lentamente il peso fino alla posizione iniziale senza lasciare cadere le braccia troppo indietro.",
            R.id.alzateLattx to "Le <a href='https://www.youtube.com/watch?v=5Dyh1z6E6rM'>Alzate laterali:<br></a>" +
                    "In piedi, con un manubrio in ciascuna mano e le braccia lungo i fianchi.<br>" +
                    "Solleva le braccia lateralmente fino a portarle all'altezza delle spalle, mantenendo un leggero piegamento dei gomiti.<br>" +
                    "Rilascia lentamente fino a tornare alla posizione iniziale, mantenendo il controllo.",
            R.id.alzateSintx to "Le <a href='https://www.youtube.com/watch?v=nwBSKOpMOdo'>Alzate laterali singole inclinate:</a>" +
                    "Posizione: inclinati in avanti con il busto quasi parallelo al pavimento, un braccio appoggiato su una panca per supporto.<br>" +
                    "Con l'altro braccio, solleva un manubrio lateralmente, concentrandoti sul deltoide posteriore.<br>" +
                    "Mantieni il gomito leggermente flesso e controlla il movimento sia in salita che in discesa.",
            R.id.crunchInvtx to "I <a href='https://www.youtube.com/watch?v=2UMZfWtY4mY'>Crunch inversi:<br></a>" +
                    "Sdraiato sulla schiena, con le gambe piegate e i piedi sollevati da terra.<br>" +
                    "Porta le ginocchia verso il petto, sollevando il bacino dal pavimento e contrai gli addominali.<br>" +
                    "Rilascia lentamente abbassando le gambe senza far toccare i piedi a terra, mantenendo il controllo del movimento.",
            R.id.sitUpstx to "I <a href='https://www.youtube.com/watch?v=jDwoBqPH0jk'>Sit ups:</a>" +
                    "Sdraiato sulla schiena con le ginocchia piegate e i piedi piatti a terra.<br>" +
                    "Metti le mani dietro la testa o incrocia le braccia sul petto.<br>" +
                    "Solleva il busto fino a sederti, contrarre gli addominali, poi torna lentamente alla posizione di partenza."
        )

        val testo = """${prog.text}
            ${risc.text} ${rec1.text}
            ${link1.text} ${rec2.text}
            ${link2.text} ${rec2.text}
            ${link3.text} ${rec3.text}
            ${link4.text} ${rec4.text}
            ${link5.text} ${rec5.text}
            ${link6.text} ${rec2.text}
            ${link7.text} ${rec2.text}
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