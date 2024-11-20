package com.example.fitnessapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class Home : Fragment() {

    private lateinit var lineChart: LineChart
    private val dataQueue: Queue<Float> = LinkedList()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val btnAddPeso = view.findViewById<Button>(R.id.btnPeso)
        lineChart = view.findViewById(R.id.lineChart)

        // Carica i dati dal Firestore ogni volta che la schermata diventa visibile
        loadDataFromFirestore()

        // Quando viene premuto il bottone per aggiungere il peso
        btnAddPeso.setOnClickListener {
            val pesoString = view.findViewById<TextInputEditText>(R.id.testoPeso2).text.toString()

            if (pesoString.isNotEmpty()) {
                try {
                    val peso = pesoString.toFloat()
                    if (peso > 0) {
                        // Salva il peso nel Firestore e aggiorna il grafico
                        saveDataToFirestore(peso)
                    } else {
                        Toast.makeText(context, "Inserisci un peso valido", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(context, "Inserisci un peso valido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Inserisci un peso", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun saveDataToFirestore(peso: Float) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            val timestamp = System.currentTimeMillis()

            // Crea un oggetto per salvare i dati (peso con timestamp)
            val data = hashMapOf(
                "peso" to peso,
                "timestamp" to timestamp // Aggiungi il timestamp
            )

            // Salva i dati nella collezione specifica dell'utente
            db.collection("users").document(userId).collection("peso")
                .add(data)
                .addOnSuccessListener {
                    // Verifica che i dati siano stati salvati correttamente
                    Log.d("Firestore", "Dati salvati con successo")
                    loadDataFromFirestore() // Ricarica i dati dopo il salvataggio
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Errore durante il salvataggio su Firestore: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }



    private fun loadDataFromFirestore() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userId = currentUser.uid
            val pesosRef = db.collection("users").document(userId).collection("peso")

            // Carica i dati dal Firestore ordinati per timestamp
            pesosRef.orderBy("timestamp", com.google.firebase.firestore.Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener { documents ->
                    dataQueue.clear() // Pulisce i dati precedenti

                    // Carica i dati nel Queue
                    for (document in documents) {
                        val peso = document.getDouble("peso")?.toFloat() ?: 0f
                        dataQueue.add(peso)
                        Log.d("Firestore", "Dati caricati: $peso")
                    }

                    // Dopo aver caricato i dati, aggiorna il grafico
                    initializeChart()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Errore durante il recupero dei dati: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(context, "Utente non autenticato", Toast.LENGTH_SHORT).show()
        }
    }



    private fun initializeChart() {
        val entries = ArrayList<Entry>()
        var x = 1f
        // Popola il grafico con i dati nel queue
        for (value in dataQueue) {
            entries.add(Entry(x, value))
            x += 1f
        }

        // Log per verificare i dati nel grafico
        Log.d("Chart", "Dati nel grafico: ${dataQueue.size}")

        // Crea il dataset e aggiorna il grafico
        val dataSet = LineDataSet(entries, "Andamento peso negli ultimi 30 giorni")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK
        dataSet.setCircleColor(Color.BLUE)
        dataSet.circleRadius = 2f

        val lineData = com.github.mikephil.charting.data.LineData(dataSet)
        lineChart.data = lineData

        lineChart.setBackgroundColor(Color.WHITE)
        lineChart.setDrawGridBackground(true)
        lineChart.axisLeft.textColor = Color.BLACK
        lineChart.axisRight.textColor = Color.BLACK
        lineChart.xAxis.textColor = Color.BLACK
        lineChart.legend.textColor = Color.BLACK
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.axisRight.isEnabled = false
        lineChart.axisLeft.granularity = 1f
        lineChart.xAxis.granularity = 1f
        lineChart.description.isEnabled = false

        lineChart.invalidate() // Rende visibile il grafico
    }



    override fun onResume() {
        super.onResume()
        loadDataFromFirestore() // Carica i dati ogni volta che il Fragment viene visualizzato
    }
}
