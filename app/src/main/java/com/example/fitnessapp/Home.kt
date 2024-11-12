package com.example.fitnessapp

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class Home : Fragment() {

    //grafico
    private lateinit var lineChart: LineChart
    //per fa si che i dati salvati nel vettore agiscano da "stack"(FIFO)
    private val dataQueue: Queue<Float> = LinkedList()
    //per salvare i dati quando si cambia frame o si chiude l'app
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val btnAddPeso = view.findViewById<Button>(R.id.btnPeso)
        lineChart = view.findViewById(R.id.lineChart)

        // creo lo SharedPreferences
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        loadDataFromSharedPreferences()

        // carica il grafico con i dati presenti in dataQueue
        initializeChart()

        btnAddPeso.setOnClickListener {
            // Converte la editText in float
            val pesoString = view.findViewById<TextInputEditText>(R.id.testoPeso2).text.toString()
            if (pesoString.isNotEmpty()) {
                try {
                    val casellaPeso = pesoString.toFloat()
                    if (casellaPeso > 0) {
                        aggiornaGrafico(casellaPeso)
                        saveDataToSharedPreferences()
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

    private fun initializeChart() {
        // Crea le Entry ovvero i valori x e y in questo caso x del grafico
        val entries = ArrayList<Entry>()
        var x = 1f
        for (value in dataQueue) {
            entries.add(Entry(x, value))
            x += 1f
        }

        // creo il grafico e configuro l'aspetto del grafico
        val dataSet = LineDataSet(entries, "Andamento peso negli ultimi 30 giorni")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK
        dataSet.setCircleColor(Color.BLUE)
        dataSet.circleRadius = 2f

        val lineData = LineData(dataSet)
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

        // Aggiorna il grafico
        lineChart.invalidate()
    }

    private fun aggiornaGrafico(newValue: Float) {
        if (dataQueue.size >= 30) {
            dataQueue.poll()
        }
        dataQueue.offer(newValue)

        // Aggiorna il grafico
        initializeChart()
    }

    private fun saveDataToSharedPreferences() {
        val editor = sharedPreferences.edit()
        for (i in dataQueue.indices) {
            editor.putFloat("entry$i", dataQueue.elementAt(i))
        }
        editor.apply()
    }

    private fun loadDataFromSharedPreferences() {
        dataQueue.clear()
        for (i in 0 until 30) {
            val value = sharedPreferences.getFloat("entry$i", 0f)
            dataQueue.offer(value)
        }
    }
}