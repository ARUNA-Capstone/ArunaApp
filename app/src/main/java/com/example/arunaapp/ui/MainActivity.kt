package com.example.arunaapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arunaapp.adapter.ArticleAdapter
import com.example.arunaapp.data.response.DataItem
import com.example.arunaapp.databinding.ActivityMainBinding
import com.example.arunaapp.viewmodel.MainVM

class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.rvAruna.layoutManager = LinearLayoutManager(this)
        setContentView(binding.root)
        try {
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
        } catch (e: Error) {
            Log.d("catch error", e.toString())
            Toast.makeText(this@MainActivity, "Error : $e", Toast.LENGTH_SHORT).show()
        }
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MainVM::class.java]
        mainViewModel.dataItem.observe(this) { dataItem ->
            setData(dataItem)
        }
        mainViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        setAddButton()

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData(data: List<DataItem>){
        val adapter = ArticleAdapter().apply {
            this.notifyDataSetChanged()
            submitList(data)
        }
        binding.rvAruna.adapter = adapter
    }

    private fun setAddButton(){
        binding.fabCamera.setOnClickListener {
            startActivity(Intent(this, SelectActivity::class.java))
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finishAffinity()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}