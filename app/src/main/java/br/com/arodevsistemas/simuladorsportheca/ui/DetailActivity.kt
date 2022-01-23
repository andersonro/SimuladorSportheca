package br.com.arodevsistemas.simuladorsportheca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import br.com.arodevsistemas.simuladorsportheca.R
import br.com.arodevsistemas.simuladorsportheca.data.model.Match
import br.com.arodevsistemas.simuladorsportheca.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    object Extras {
        const val MATCH = "EXTRA_MATCH"
    }

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadMatchDetail()
    }

    private fun loadMatchDetail() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH).let {
            supportActionBar?.title = it?.place?.name
            Glide.with(this)
                .load(it?.place?.image)
                .into(binding.ivPlace)

            binding.tvHomeTeamName.text = it?.homeTeam?.name
            binding.tvHomeTeamScore.text = it?.homeTeam?.score.toString()
            binding.rbHomeTeamStars.rating = it?.homeTeam?.star?:0f
            Glide.with(this)
                .load(it?.homeTeam?.image)
                .circleCrop()
                .into(binding.ivHomeTeam)

            binding.tvAwayTeamName.text = it?.awayTeam?.name
            binding.tvAwayTeamScore.text = it?.awayTeam?.score.toString()
            binding.rbAwayTeamStars.rating = it?.awayTeam?.star?:0f
            Glide.with(this)
                .load(it?.awayTeam?.image)
                .circleCrop()
                .into(binding.ivAwayTeam)
        }
    }
}