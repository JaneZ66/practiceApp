package com.jane.practice.Motty.ui.main.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jane.practice.Motty.R
import com.jane.practice.Motty.data.entities.MortyCharacter

class DetailsFragment: Fragment(){
    private lateinit var viewModel: DetailsViewModel
    private var data: MortyCharacter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Jane", "OnCreate Details")
        super.onCreate(savedInstanceState)
        val id = this.arguments?.getInt("Character_id") ?: -1
        val viewModelFactory = DetailsViewModelFactory(id)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
    }

    fun setData(contentdata: MortyCharacter) {
        data = contentdata
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.details_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.character.observe(this.viewLifecycleOwner, {
            data = it
            val imageView: ImageView = view.findViewById(R.id.image)
            val name: TextView = view.findViewById(R.id.name)
            val details: TextView = view.findViewById(R.id.species_and_status)
            name.text = data?.name
            val imgUrl = data?.image
            details.text = data?.status
            Glide.with(imageView.context)
                .load(imgUrl)
                .into(imageView)
        }
        )

    }
}