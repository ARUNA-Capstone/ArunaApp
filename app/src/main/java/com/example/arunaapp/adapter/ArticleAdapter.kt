package com.example.arunaapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.arunaapp.data.response.DataItem
import com.example.arunaapp.databinding.ItemLayoutBinding
import com.example.arunaapp.ui.DetailActivity

class ArticleAdapter: ListAdapter<DataItem, ArticleAdapter.ArticleListViewHolder>(DIFF_CALLBACK) {
    inner class ArticleListViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(article: DataItem){
            binding.tvName.text = article.name

            Glide
                .with(itemView.context)
                .load(article.image)
                .into(binding.ivPhoto)

            binding.root.setOnClickListener{
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DETAIL, article.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleListViewHolder {
        val view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleListViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    companion object{
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<DataItem>(){
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}