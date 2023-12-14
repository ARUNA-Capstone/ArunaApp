package com.example.aruna.view.detail

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.aruna.R
import com.example.aruna.data.response.ArticleResponse
import com.example.aruna.data.response.DataItem
import com.example.aruna.databinding.ActivityDetailBinding
import com.example.aruna.databinding.ActivityMainBinding

class DetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    companion object{
        const val DETAIL = "DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detail = intent.getIntExtra(DETAIL, 0)
        val bundle = Bundle()
        bundle.putInt(DETAIL, detail)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
        detailViewModel.articleDetail(detail!!.toInt())
        detailViewModel.detail.observe(this){
            if(it != null){
                binding.apply {
                    tvDetail.text = it.name
                    description.text = it.description
                    Glide.with(this@DetailActivity)
                        .load(it.image)
                        .into(binding.ivDetail)
                }
            }
        }
    }
}