package com.godlonton.livedataexample.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.content.res.Configuration
import com.godlonton.livedataexample.model.Blog
import com.godlonton.livedataexample.R
import com.godlonton.livedataexample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.arch.lifecycle.Observer

/**
 * Created on : June 16, 2019
 * Author     : Ryan Godlonton-Shaw
 */

class MainActivity : AppCompatActivity() {

    var mRecyclerView: RecyclerView? = null
    var swipeRefresh: SwipeRefreshLayout? = null
    var mainViewModel: MainViewModel? = null

    var mBlogAdapter: BlogAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefresh = swiperefresh
        mRecyclerView = blogRecyclerView
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        getPopularBlog()
        swipeRefresh!!.setOnRefreshListener { getPopularBlog() }
    }

    fun getPopularBlog() {
        swipeRefresh!!.setRefreshing(false)
        mainViewModel!!.allBlog.observe(this, Observer {  blogList ->
            prepareRecyclerView(blogList)
        })

    }

    private fun prepareRecyclerView(blogList: List<Blog>?) {

        mBlogAdapter = BlogAdapter(blogList)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView!!.setLayoutManager(LinearLayoutManager(this))
        } else {
            mRecyclerView!!.setLayoutManager(GridLayoutManager(this, 4))

        }
        mRecyclerView!!.setItemAnimator(DefaultItemAnimator())
        mRecyclerView!!.setAdapter(mBlogAdapter)
    }
}
