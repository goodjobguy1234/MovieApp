package com.example.movieproject


import android.util.Log
import android.view.View

import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.movieproject.data.data.entity.MovieData
import com.example.movieproject.presentation.DataViewModel
import com.example.movieproject.presentation.MovieAdapter
import com.google.android.material.appbar.AppBarLayout

class HomeFragment : BaseFragment() {

    private val model by activityViewModels<DataViewModel> {
        Injection.viewModelFactory
    }
    override var layoutRes = R.layout.home_fragment
    private var movieList = ArrayList<MovieData>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var refresher: SwipeRefreshLayout
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var appBar: AppBarLayout

    override fun setViewId(view: View) {
        with(view) {
            recyclerView = findViewById(R.id.recyclerView)
            progressBar = findViewById(R.id.hm_pg)
            refresher = findViewById(R.id.refresher)
            appBar = findViewById(R.id.appBar)
            toolbar = findViewById(R.id.toolbar)
        }
        recyclerView.visibility = View.GONE
        (requireActivity() as? MainActivity)?.setSupportActionBar(toolbar)
    }

    override fun observeView() {

        with(model) {
            liveData.observe(viewLifecycleOwner, {
                progressBar.visibility = View.GONE
                fetchData(it)
                recyclerView.visibility = View.VISIBLE
            })
            selectedData.observe(viewLifecycleOwner, { data ->
                if (data != null) findNavController().navigate(R.id.action_home_to_detailFragment)
            })

        }

    }

    override fun initView() {
        model.getData()

        recyclerView.apply {

            adapter = MovieAdapter(movieList) { movie ->
                model.selectedDate(movie)

            }

            layoutManager = GridLayoutManager(requireContext(), 2)
        }

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

    companion object {
        fun newInstance() = HomeFragment()
    }
}