package com.example.aruna.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aruna.data.response.DataItem
import com.example.aruna.databinding.ActivityMainBinding
import com.example.aruna.view.adapter.ArticleAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.rvAruna.layoutManager = LinearLayoutManager(this)
        setContentView(binding.root)

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener{ textView, _, _ ->
                    searchView.hide()
                    val query= textView.text.toString()
                    if (query.isNotEmpty()){
                        mainViewModel.searchArticle(query)
                        binding.searchBar.setText(query)
                    } else{
                        Toast.makeText(this@MainActivity, "Cannot be Empty", Toast.LENGTH_SHORT).show()
                    }
                    false
                }
        }
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
        mainViewModel.dataItem.observe(this){ dataItem ->
            setData(dataItem)
        }

        setAddButton()
    }
    private fun setData(data: List<DataItem>){
        val adapter = ArticleAdapter().apply {
            this.notifyDataSetChanged()
            submitList(data)
        }
        binding.rvAruna.adapter = adapter
    }

    private fun setAddButton(){
        binding.fabCamera.setOnClickListener {
            val intent =
            startActivity(intent)
        }
    }
}