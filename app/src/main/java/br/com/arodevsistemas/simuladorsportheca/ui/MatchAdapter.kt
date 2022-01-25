package br.com.arodevsistemas.simuladorsportheca.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.arodevsistemas.simuladorsportheca.data.model.Match
import br.com.arodevsistemas.simuladorsportheca.databinding.ItemDetailBinding
import com.bumptech.glide.Glide

class MatchAdapter(private val context: Context): ListAdapter<Match, MatchAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(
        private val binding: ItemDetailBinding
        ): RecyclerView.ViewHolder(binding.root){
        fun bind(match: Match){
            binding.tvDetailTeamHome.text = match.homeTeam.name
            binding.tvDetailScoreHome.text = match.homeTeam.score.toString()
            Glide.with(context)
                .load(match.homeTeam.image)
                .circleCrop()
                .into(binding.ivDetailTeamHome)

            binding.tvDetailTeamAway.text = match.awayTeam.name
            binding.tvDetailScoreAway.text = match.awayTeam.score.toString()
            Glide.with(context)
                .load(match.awayTeam.image)
                .circleCrop()
                .into(binding.ivDetailTeamAway)

            binding.cvItem.setOnClickListener {
                var intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.Extras.MATCH, match)
                context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDetailBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
class DiffCallback: DiffUtil.ItemCallback<Match>() {
    override fun areItemsTheSame(oldItem: Match, newItem: Match) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Match, newItem: Match) = oldItem.description == newItem.description

}