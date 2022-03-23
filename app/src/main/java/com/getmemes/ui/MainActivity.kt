package com.getmemes.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.getmemes.R
import com.getmemes.data.model.Meme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    var list: ArrayList<Meme> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()
        mainViewModel.getMemes()
    }

    private fun setupUI() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = MainAdapter(arrayListOf())
        recyclerView.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener {
            list.clear()
            mainViewModel.getMemes()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupObserver() {
        mainViewModel.loading.observe(this, Observer {
            when (it) {
                true -> {
                    loaderLayout.visibility = View.VISIBLE
                }
                false -> {
                    loaderLayout.visibility = View.GONE
                }
            }
        })
        mainViewModel.error.observe(this, Observer {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        })
        mainViewModel.movies.observe(this, Observer {
            renderList(it)
        })
    }

    private fun renderList(list: List<Meme>) {
        this.list.addAll(list)
        adapter.addData(this.list)
    }
}