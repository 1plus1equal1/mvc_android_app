package dev.hnxtay.android_tutorial.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.hnxtay.android_tutorial.ui.main.recyclerview.PostAdapter
import dev.hnxtay.android_tutorial.data.Client
import dev.hnxtay.android_tutorial.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val postAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.recyclerView) {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
        lifecycleScope.launch {
            try {
                val postsResponse = withContext(Dispatchers.IO) {
                    Client.getPostResponse()
                }
                postAdapter.submitList(postsResponse.results)
                println("dataaaaa: $postsResponse")
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}