package br.com.arodevsistemas.simuladorsportheca.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.arodevsistemas.simuladorsportheca.core.createDialog
import br.com.arodevsistemas.simuladorsportheca.core.createProgressDialog
import br.com.arodevsistemas.simuladorsportheca.databinding.ActivityMainBinding
import br.com.arodevsistemas.simuladorsportheca.presentation.MatchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val matchAdapter by lazy { MatchAdapter(this) }
    private val dialog by lazy { createProgressDialog() }
    private val matchViewModel by viewModel<MatchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvMain.adapter = matchAdapter
        binding.rvMain.setHasFixedSize(true)
        binding.rvMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listenerMatch()
        listenerMatchObserve()
        listenerMatchRefresh()
        listenerMatchFloatingButton()
    }

    private fun listenerMatchRefresh(){
        binding.srplMain.setOnRefreshListener(this::listenerMatch)
    }

    private fun listenerMatchFloatingButton(){
        binding.fbMain.setOnClickListener {
            it.animate()
                .rotationBy(360f)
                .setDuration(500)
                .setListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {

                        for (i in 1..matchAdapter.itemCount){
                            var match = matchAdapter.currentList.get(i-1)
                            match.homeTeam.score = Random().nextInt(match.homeTeam.star.toInt() + 1)
                            match.awayTeam.score = Random().nextInt(match.awayTeam.star.toInt() + 1)
                            matchAdapter.notifyItemChanged(i-1)
                        }
                    }
                })
        }
    }

    private fun listenerMatch(){
        matchViewModel.getMatchListRepository()
    }

    private fun listenerMatchObserve(){
        matchViewModel.state.observe(this){
            when(it){
                MatchViewModel.State.Loading -> {
                    matchAdapter.submitList(emptyList())
                    binding.srplMain.isRefreshing = true
                }
                is MatchViewModel.State.Error -> {
                    binding.srplMain.isRefreshing = false
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                }
                is MatchViewModel.State.Success -> {
                    binding.srplMain.isRefreshing = false
                    matchAdapter.submitList(it.list)
                }
            }
        }
    }
}