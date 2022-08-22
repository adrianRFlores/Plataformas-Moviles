package com.example.laboratorio6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LibraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibraryFragment : Fragment(R.layout.fragment_library) {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var suma: ImageView
    private lateinit var texto: TextView
    private var liked: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        suma = view.findViewById(R.id.suma)
        texto = view.findViewById(R.id.liked)

        setListeners()

    }

    override fun onResume(){
        super.onResume()
        texto.text = liked.toString() + " songs"
    }

    private fun setListeners(){
        suma.setOnClickListener{
            liked++
            texto.text = liked.toString() + " songs"
        }
    }


}