package com.jane.practice.Motty.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jane.practice.Motty.R
import com.jane.practice.Motty.data.entities.MortyCharacter

class CharacterAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: MortyCharacter)
    }


    private val items = mutableListOf<MortyCharacter>()

    fun setItems(items: List<MortyCharacter>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int)  {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(items[position])
        }
    }

    override fun getItemCount() = items.size

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.name)
        val details: TextView = itemView.findViewById(R.id.species_and_status)


        fun bind(character : MortyCharacter) {
            name.text = character.name
            val imgUrl = character.image
            details.text = character.species
            Glide.with(imageView.context)
                .load(imgUrl)
                .into(imageView)
        }

    }

}