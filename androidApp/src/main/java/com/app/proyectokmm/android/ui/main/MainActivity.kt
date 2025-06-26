package com.app.proyectokmm.android.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.proyectokmm.android.ui.characters.CharactersAdapter
import com.app.proyectokmm.android.ui.common.ScreenState
import kotlinx.coroutines.launch
import com.app.proyectokmm.android.databinding.ActivityMainBinding
import com.app.proyectokmm.android.ui.common.VerticalSpaceItemDecoration
import com.app.proyectokmm.domain.model.Character

class MainActivity : AppCompatActivity() {

    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate iniciado")

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup del listado
        charactersAdapter = CharactersAdapter()
        val verticalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        with(binding.charactersList) {
            this.adapter = charactersAdapter
            this.layoutManager = verticalLayoutManager
            this.addItemDecoration(VerticalSpaceItemDecoration(16))
        }
        Log.d("MainActivity", "RecyclerView configurado")

        // Listen to Retrofit response
        val viewModel =
            ViewModelProvider(this, CharactersViewModelFactory())[CharactersViewModel::class.java]
        Log.d("MainActivity", "ViewModel creado")
        
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                Log.d("MainActivity", "Iniciando observación del ViewModel")
                viewModel.screenState.collect { state ->
                    Log.d("MainActivity", "Estado recibido: $state")
                    when (state) {
                        ScreenState.Loading -> {
                            Log.d("MainActivity", "Mostrando loading")
                            showLoading()
                        }
                        is ScreenState.ShowCharacters -> {
                            Log.d("MainActivity", "Mostrando ${state.list.size} personajes")
                            showCharacters(state.list)
                        }
                    }
                }
            }
        }
    }

    private fun showLoading() {
        Log.d("MainActivity", "showLoading llamado")
    }

    private fun showCharacters(list: List<Character>) {
        Log.d("MainActivity", "showCharacters llamado con ${list.size} personajes")
        charactersAdapter.submitList(list)
        Log.d("MainActivity", "Lista enviada al adapter")
    }
}