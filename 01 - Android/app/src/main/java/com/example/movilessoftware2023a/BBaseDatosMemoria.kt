package com.example.movilescomputacion2023a
// BbaseDatosMemoria.kt
class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Betsabe", "betsyaby99@gmail.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Abigail", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Carolina", "c@c.com")
                )
        }
    }
}