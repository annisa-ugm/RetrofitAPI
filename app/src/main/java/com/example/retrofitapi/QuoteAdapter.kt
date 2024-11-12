package com.example.retrofitapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapi.databinding.ItemQuoteBinding
import model.Quote

class QuoteAdapter : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {
    private var quotes: List<Quote> = listOf()

    fun setQuotes(quotes: List<Quote>) {
        this.quotes = quotes
        notifyDataSetChanged()
    } //mengupdate daftar quote di RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.bind(quote)
    }

    override fun getItemCount() = quotes.size

    inner class QuoteViewHolder(private val binding: ItemQuoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quote: Quote) {
            binding.tvCharacter.text = "Character: ${quote.character}"
            binding.tvAnime.text = "Anime: ${quote.anime}"
            binding.tvEnglish.text = "English: ${quote.english}"
            binding.tvIndo.text = "Indo: ${quote.indo}"
        }
    } //inner class untuk menampilkan setiap quote
    //bind untuk menampilkan teks seperti character, anime, dll di setiap textview
}
