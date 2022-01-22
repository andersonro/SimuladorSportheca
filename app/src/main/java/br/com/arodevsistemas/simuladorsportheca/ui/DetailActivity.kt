package br.com.arodevsistemas.simuladorsportheca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.arodevsistemas.simuladorsportheca.R
import br.com.arodevsistemas.simuladorsportheca.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}