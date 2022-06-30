package com.example.atildeneme


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class LandmarkAdapter(landmarkList: ArrayList<Landmark>) :
    RecyclerView.Adapter<LandmarkAdapter.LandmarkHolder>() {
    var listem = landmarkList


    class LandmarkHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView = view.findViewById<TextView>(R.id.textName)
        val surnameTextView = view.findViewById<TextView>(R.id.textSurname)
        val numberTextView = view.findViewById<TextView>(R.id.textNumber)

        fun bindItems(item: Landmark) {
            nameTextView.text = item.name
            surnameTextView.text = item.surname
            numberTextView.text = item.number
        }
    }

    fun setData(landmarkList: ArrayList<Landmark>){
        listem = landmarkList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkHolder {
        val binding = LayoutInflater.from(parent.context)
        val view = binding.inflate(R.layout.recycler_row, parent, false)

        return LandmarkHolder(view)
    }

    override fun onBindViewHolder(holder: LandmarkHolder, position: Int) {
        holder.bindItems(listem.get(position))
    }

    override fun getItemCount(): Int {
        return listem.size


    }

        }