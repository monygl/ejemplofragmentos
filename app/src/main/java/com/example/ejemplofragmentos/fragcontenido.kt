package com.example.ejemplofragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * A simple [Fragment] subclass.
 */
class fragcontenido : Fragment() {

    var vista:View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista =inflater.inflate(R.layout.fragment_fragcontenido, container, false)
        CambiarFoto()
        return vista!!
    }

    fun nuevaInstancia(index:Int):fragcontenido{
        val f=fragcontenido();
        val args=Bundle();
        args.putInt("INDEX",index);
        f.arguments=args
        return f;
    }
    fun obtenerIndex(): Int{
        val index=arguments?.getInt("INDEX",0)!!;
        return index
    }
    fun CambiarFoto(){
        val foto=vista!!.findViewById<ImageView>(R.id.ivfoto)
        foto.setImageResource(fraglista.peliculas?.get(obtenerIndex())?.imagen!!)
    }

}
