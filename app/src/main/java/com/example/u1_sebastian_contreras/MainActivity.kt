package com.example.u1_sebastian_contreras

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.u1_sebastian_contreras.model.Pedido
import com.example.u1_sebastian_contreras.model.Plato

class MainActivity : AppCompatActivity() {

    private lateinit var pedido: Pedido
    private lateinit var cantidadJaivaEditText: EditText
    private lateinit var cantidadMariscalEditText: EditText
    private lateinit var propinaSwitch: Switch
    private lateinit var subtotalTextView: TextView
    private lateinit var propinaTextView: TextView
    private lateinit var totalTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de los platos
        val pastelDeJaiva = Plato("Pastel de Jaiva", 12000.0)
        val mariscalGourmet = Plato("Mariscal Gourmet", 10000.0)
        pedido = Pedido(listOf(pastelDeJaiva, mariscalGourmet))

        // Inicialización de vistas
        cantidadJaivaEditText = findViewById(R.id.cantidadJaivaEditText)
        cantidadMariscalEditText = findViewById(R.id.cantidadMariscalEditText)
        propinaSwitch = findViewById(R.id.propinaSwitch)
        subtotalTextView = findViewById(R.id.subtotalTextView)
        propinaTextView = findViewById(R.id.propinaTextView)
        totalTextView = findViewById(R.id.totalTextView)

        // Configuración de vistas y eventos
        setupUI()
    }

    private fun setupUI() {
        cantidadJaivaEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { actualizarTotales() }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        cantidadMariscalEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { actualizarTotales() }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        propinaSwitch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            pedido.setIncluirPropina(isChecked)
            actualizarTotales()
        }
    }

    private fun actualizarTotales() {
        val cantidades = listOf(
            cantidadJaivaEditText.text.toString().toIntOrNull() ?: 0,
            cantidadMariscalEditText.text.toString().toIntOrNull() ?: 0
        )

        val subtotal = pedido.calcularSubtotal(cantidades)
        val propina = pedido.calcularPropina(subtotal)
        val total = pedido.calcularTotal(subtotal, propina)

        subtotalTextView.text = pedido.formatearMoneda(subtotal)
        propinaTextView.text = pedido.formatearMoneda(propina)
        totalTextView.text = pedido.formatearMoneda(total)
    }
}

