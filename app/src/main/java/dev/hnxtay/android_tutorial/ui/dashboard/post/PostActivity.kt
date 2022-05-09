package dev.hnxtay.android_tutorial.ui.dashboard.post

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.hnxtay.android_tutorial.data.Client
import dev.hnxtay.android_tutorial.databinding.ActivityPostBinding
import dev.hnxtay.android_tutorial.model.Image
import dev.hnxtay.android_tutorial.ui.authenticate.AuthenticationActivity
import dev.hnxtay.android_tutorial.ui.dashboard.DetailsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding
    private lateinit var postAdapter: PostAdapter
    private var images = listOf<Image>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        initRecyclerView()
        signOut()
    }

    private fun initAdapter() {
        postAdapter = PostAdapter {
            openDetail(it)
        }
    }

    private fun initRecyclerView() {
        with(binding.rvPost) {
            adapter = postAdapter
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            setHasFixedSize(true)
        }

        lifecycleScope.launch {
            try {
                val postsResponse = withContext(Dispatchers.IO) {
                    Client.getPostResponse()
                }
                images = postsResponse.results
                postAdapter.submitList(postsResponse.results)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    private fun openDetail(position: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.IMAGE_DATA_KEY, images[position])
        startActivity(intent)
    }

    private fun signOut() {
        binding.imgSignOut.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this, AuthenticationActivity::class.java))
            finish()
        }
    }
}
