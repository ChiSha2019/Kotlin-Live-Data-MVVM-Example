package com.godlonton.livedataexample.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView
import android.widget.TextView
import com.godlonton.livedataexample.R
import com.godlonton.livedataexample.model.Blog
import com.bumptech.glide.Glide


/**
 * Created on : June 16, 2019
 * Author     : Ryan Godlonton-Shaw
 */

class BlogAdapter(val blogList: List<Blog>?) : RecyclerView.Adapter<BlogAdapter.ViewHolder>() {


    override fun getItemCount()=blogList!!.size

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        this.mContext=parent.context;

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.blog_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val mBlog = blogList!!.get(position);

        if (mBlog.thumbnail != null) {
            Glide.with(mContext!!)
                .load(mBlog.thumbnail)
                .into(holder.ivThumbnail);
        }
        if (mBlog.title != null) {
            holder.tvTitle.setText(mBlog.title);
        }

        if (mBlog.description != null) {
            holder.tvDescription.setText(mBlog.description);
        }

        if (mBlog.link != null) {
            holder.tvLink.setText(mBlog.link);
        }

        holder.tvLink.setOnClickListener{
            if (mBlog.link != null) {
                try {
                    val intent = Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(mBlog.link));
                    mContext!!.startActivity(intent);
                } catch (e:Exception ) {

                }
            }
        }
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val ivThumbnail:ImageView = itemView.findViewById(R.id.ivThumbnail);
        val tvTitle:TextView = itemView.findViewById(R.id.tvTitle);
        val tvDescription:TextView = itemView.findViewById(R.id.tvDescription);
        val tvLink:TextView = itemView.findViewById(R.id.tvLink);
    }
}