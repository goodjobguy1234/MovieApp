package com.example.movieproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.movieproject.data.data.entity.MovieData
import com.example.movieproject.presentation.DataViewModel
import com.example.movieproject.presentation.MovieAdapter
import com.google.android.material.appbar.AppBarLayout

class HomeFragment : BaseFragment() {
    override var layoutRes = R.layout.home_fragment
    private var movieList = ArrayList<MovieData>()
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var refresher: SwipeRefreshLayout
    lateinit var movieAdapter: MovieAdapter
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var appBar: AppBarLayout

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun setViewId(view: View) {
        with(view) {
            recyclerView = findViewById(R.id.recyclerView)
            progressBar = findViewById(R.id.hm_pg)
            refresher = findViewById(R.id.refresher)
            appBar = findViewById(R.id.appBar)
            toolbar = findViewById(R.id.toolbar)
        }
        recyclerView.visibility = View.GONE
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        toolbar.title = "Movie"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            movieAdapter = MovieAdapter(movieList) {
                Toast.makeText(
                    requireContext(),
                    "this is ${it.title_en}",
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(R.id.detailFragment)
            }
            adapter = movieAdapter
            val mlayout = GridLayoutManager(requireContext(), 2)
            layoutManager = mlayout
        }


        val model by viewModels<DataViewModel> {
            Injection.viewModelFactory
        }
        model.liveData.observe(requireActivity(), Observer {
            progressBar.visibility = View.GONE
            fetchData(it)
            recyclerView.visibility = View.VISIBLE


        })
        model.getData()
        refresher.setOnRefreshListener {
            model.getData()
            refresher.isRefreshing = false
        }

    }

    private fun fetchData(list: ArrayList<MovieData>) {
        movieList.clear()
        movieList.addAll(list)
        recyclerView.adapter!!.notifyDataSetChanged()
    }

}