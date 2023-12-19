package com.example.arunaapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.arunaapp.databinding.ActivityDetailBinding
import com.example.arunaapp.viewmodel.DetailVM

class DetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detail = intent.getIntExtra(DETAIL, 0)
        val bundle = Bundle()
        bundle.putInt(DETAIL, detail)
        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailVM::class.java]
        detailViewModel.articleDetail(detail)
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
        detailViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        setAction()
    }

    private fun setAction() {
        binding.back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    companion object{
        const val DETAIL = "DETAIL"
    }
}