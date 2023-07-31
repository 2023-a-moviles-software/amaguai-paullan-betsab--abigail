package com.example.facebook

class BaseDatos {
    companion object{
        val arregloPublicaiones= arrayListOf<BPublicaciones>()
        init{
            arregloPublicaiones.add(BPublicaciones("Brandon Ramirez", "100 Me gusta","De una :v","8 abr",R.drawable.perfil1, R.drawable.publicacion1))
            arregloPublicaiones.add(BPublicaciones("Bestabé Amaguai", "10 Me gusta", "Las despedidas no son para siempre, las despedidas no son el final...\uD83D\uDE0C",
                "9 abr", R.drawable.perfil2, R.drawable.publicacion2))
            arregloPublicaiones.add(BPublicaciones("Odalys Benavides", "15 Me gusta", "JAJAJAJAJAJA",
                "Hace 4 dias", R.drawable.perfil3, R.drawable.publicacion3))
            arregloPublicaiones.add(BPublicaciones("Erika Garzon", "11 Me gusta", "Si soy :v",
                "Hace 2 dias", R.drawable.perfil4, R.drawable.publicacion4))
            arregloPublicaiones.add(BPublicaciones("Danilo Montero", "12 Me gusta", "Porque led entregué en primer lugar lo mismo que recibí: que Cristo murió por nuestros pecados, conforme a las Escrituras;",
                "Hace 4 dias", R.drawable.perfil5, R.drawable.publicacion5))
        }
        val arregloHistorias = arrayListOf<BHistorias>()
        init{
            arregloHistorias.add(BHistorias("", R.drawable.musicahistoria))
            arregloHistorias.add(BHistorias("", R.drawable.mihistoria))
            arregloHistorias.add(BHistorias("", R.drawable.historia1))
            arregloHistorias.add(BHistorias("", R.drawable.historia2))
            arregloHistorias.add(BHistorias("", R.drawable.historia3))
            arregloHistorias.add(BHistorias("", R.drawable.historia4))
            arregloHistorias.add(BHistorias("", R.drawable.historia5))
            arregloHistorias.add(BHistorias("", R.drawable.historia6))
            arregloHistorias.add(BHistorias("", R.drawable.historia7))
            arregloHistorias.add(BHistorias("", R.drawable.historia8))
            arregloHistorias.add(BHistorias("", R.drawable.historia9))
        }
    }
}