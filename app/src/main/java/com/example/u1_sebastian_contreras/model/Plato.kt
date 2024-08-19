package com.example.u1_sebastian_contreras.model

data class Plato(val nombre: String, val precio: Double) {
    fun calcularSubtotal(cantidad: Int): Double {
        return precio * cantidad
    }
}