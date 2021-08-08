package com.app.mortyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mortyapp.Model.CharacterRepository
import com.app.mortyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private val characters = mutableListOf<Character>()
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerAdapter: RecyclerAdapter

    private val viewModel: MainViewModel by viewModels(
        factoryProducer = {MainViewModelFactory()}
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressBar = binding.ProgressBar
        progressBar.visibility = View.INVISIBLE
        
        setObservers()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvCharacters.adapter = recyclerAdapter
        with(binding.rvCharacters){

            layoutManager = LinearLayoutManager(context)
            recyclerAdapter = RecyclerAdapter(characters).apply {
                setCharacterList(characters)
            }
        }
    }

    private fun setObservers(){
        viewModel.characterList.observe(this, Observer {
            when(it.status){
                NetworkStatus.LOADING ->{
                    //show loading state
                    progressBar.visibility = View.VISIBLE
                }
                NetworkStatus.SUCCESS -> {
                    //hide loading state
                    progressBar.visibility = View.INVISIBLE
                    //render character list
                    recyclerAdapter.setCharacterList(characters)
                    recyclerAdapter.notifyDataSetChanged()

                }
                NetworkStatus.ERROR -> {
                    //show error message
                    Toast.makeText(this,"Error loading content", Toast.LENGTH_SHORT).show()
                    //hide loading state
                    progressBar.visibility = View.INVISIBLE

                }
            }
        })
    }
}