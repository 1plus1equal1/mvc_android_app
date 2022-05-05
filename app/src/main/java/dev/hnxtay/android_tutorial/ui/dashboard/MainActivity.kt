package dev.hnxtay.android_tutorial.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.hnxtay.android_tutorial.models.Image
import dev.hnxtay.android_tutorial.data.Client
import dev.hnxtay.android_tutorial.databinding.ActivityMainBinding
import dev.hnxtay.android_tutorial.ui.dashboard.recyclerview.PostAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val postAdapter = PostAdapter {
        toImageDetail(it)
    }

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
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    private fun toImageDetail(image: Image) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("image", image)
        startActivity(intent)
    }
}