package com.jane.practice.Motty.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jane.practice.Motty.R
import com.jane.practice.Motty.data.entities.MortyCharacter
import com.jane.practice.Motty.ui.main.CharacterAdapter.OnItemClickListener

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: CharacterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val layout = inflater.inflate(R.layout.main_fragment, container, false)
        val recyclerView: RecyclerView = layout.findViewById(R.id.characters_list)
        adapter = CharacterAdapter(object : OnItemClickListener {
            override fun onItemClick(item: MortyCharacter) {
                val detailsFragment = DetailsFragment()
//                val bundle = Bundle()
//                bundle.putInt("Character_id", item.id)
//                detailsFragment.arguments = bundle
                Log.d("Jane", "OnclickListener for " + item.id)
                detailsFragment.setData(item)
                if (activity != null) {
                    Log.d("Jane", "details != null")
                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.main, detailsFragment)
                        .addToBackStack(null)
                        .commit()
                }

            }

        })
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Jane", "onViewCreated")
        viewModel.characters.observe(this.viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapter.setItems(items = it)
            }
        })
    }
}