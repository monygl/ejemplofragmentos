package com.example.ejemplofragmentos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction

/**
 * A simple [Fragment] subclass.
 */
class fraglista : Fragment() {

    companion object{
        var peliculas:ArrayList<Pelicula>? = null
    }
    var nombrePeliculas:ArrayList<String>? = null
    var lista: ListView? = null
    var doblepanel =false
    var posicionActual=0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configurarListView()
        val frameDetalles=activity!!.findViewById<FrameLayout>(R.id.detalles)
        doblepanel=frameDetalles != null && frameDetalles.visibility==View.VISIBLE
        if (savedInstanceState != null){
            posicionActual=savedInstanceState.getInt("INDEX",0)
        }
        if(doblepanel){
            lista?.choiceMode= ListView.CHOICE_MODE_SINGLE
            mostrarDetalles(posicionActual)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista= inflater.inflate(R.layout.fragment_fraglista, container, false)
        return vista
    }
    private fun configurarListView(){
        peliculas=ArrayList()
        peliculas?.add(Pelicula("Wonder Woman", R.drawable.poster1))
        peliculas?.add(Pelicula("Nemo", R.drawable.poster2))
        peliculas?.add(Pelicula("Spiderman", R.drawable.poster3))
        peliculas?.add(Pelicula("Avengers", R.drawable.poster4))

        nombrePeliculas=obtenerPeliculas(peliculas!!)
        val adaptador=
            ArrayAdapter(activity!!,android.R.layout.simple_list_item_activated_1, nombrePeliculas!!)
        lista=activity!!.findViewById(R.id.lista)
        lista?.adapter=adaptador
        lista?.setOnItemClickListener{adapterView, view,i,l ->
            mostrarDetalles(i)
        }

    }
    fun mostrarDetalles(index:Int){
        posicionActual=index
        if(doblepanel){
            var fDetalles=activity!!.supportFragmentManager.findFragmentById(R.id.detalles) as? fragcontenido
            if(fDetalles == null || fDetalles.obtenerIndex() != index){
                fDetalles=fragcontenido().nuevaInstancia(index)
                val fragmentTransaction= activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.detalles,fDetalles)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                fragmentTransaction.commit()
            }
        }else{
            val intent= Intent(activity,Detalles::class.java)
            intent.putExtra("INDEX",index)
            startActivity(intent)
        }
    }
    fun obtenerPeliculas(peliculas:ArrayList<Pelicula>):ArrayList<String>{
        val nombres=ArrayList<String>()
        for(pelicula in peliculas){
            nombres.add(pelicula.nombre)
        }
        return nombres
    }


}
