package com.example.u1_sebastian_contreras.model

import java.text.NumberFormat
import java.util.Locale

class Pedido(private val platos: List<Plato>) {

    private var incluirPropina: Boolean = false

    fun calcularSubtotal(cantidades: List<Int>): Double {
        return platos.zip(cantidades) { plato, cantidad ->
            plato.calcularSubtotal(cantidad)
        }.sum()
    }

    fun calcularPropina(subtotal: Double): Double {
        return if (incluirPropina) subtotal * 0.1 else 0.0
    }

    fun calcularTotal(subtotal: Double, propina: Double): Double {
        return subtotal + propina
    }

    fun setIncluirPropina(valor: Boolean) {
        incluirPropina = valor
    }

    fun formatearMoneda(valor: Double): String {
        val formato = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        return formato.format(valor)
    }
}