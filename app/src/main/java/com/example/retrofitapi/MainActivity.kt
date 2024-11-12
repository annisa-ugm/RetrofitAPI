package com.example.retrofitapi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapi.databinding.ActivityMainBinding
import model.Quote
import model.QuoteResponse
import network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var quoteAdapter: QuoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvQuote.layoutManager = LinearLayoutManager(this)
        quoteAdapter = QuoteAdapter()
        binding.rvQuote.adapter = quoteAdapter

        fetchQuotes()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    } //mengatur RecyclerView, memanggil fetchQuotes() untuk mengambil data quote

    private fun fetchQuotes() {
        val apiService = ApiClient.getInstance()
        val call = apiService.getKata("kuat", 1)

        call.enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                if (response.isSuccessful) {
                    response.body()?.result?.let { quotes ->
                        quoteAdapter.setQuotes(quotes) // Update RecyclerView dengan data
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load quotes", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    } //memanggil ApiClient untuk mengambil data API dan meneruskannya ke QuoteAdapter

    //menggunakan activity_main.xml sbg layout utama, QuoteAdapter untuk menampilkan data
    //di RecyclerView, dan ApiClient untuk mengakses API
}