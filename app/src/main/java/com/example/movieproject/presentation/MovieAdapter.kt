package com.example.movieproject.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieproject.R
import com.example.movieproject.data.data.entity.MovieData

class MovieAdapter(val users:ArrayList<MovieData>, val callback: (MovieData) -> Unit ) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    private lateinit var mcontext:Context
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title_en = itemView.findViewById<TextView>(R.id.title_en)
        val title_th = itemView.findViewById<TextView>(R.id.title_th)
        val image = itemView.findViewById<ImageView>(R.id.imageView)
        val duration = itemView.findViewById<TextView>(R.id.duration)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mcontext = parent.context
        val view = LayoutInflater.from(mcontext)
                .inflate(R.layout.movie_holder, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title_en.text = users[position].title_en
            title_th.text = users[position].title_th
            duration.text = "Video duration:${users[position].duration}"
            Glide.with(mcontext).load(users[position].url).into(image)
        }
        holder.itemView.setOnClickListener {
            callback(users[position])
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }


}