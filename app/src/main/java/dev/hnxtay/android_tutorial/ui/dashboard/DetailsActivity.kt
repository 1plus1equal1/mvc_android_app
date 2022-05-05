package dev.hnxtay.android_tutorial.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.hnxtay.android_tutorial.R
import dev.hnxtay.android_tutorial.databinding.ActivityDetailsBinding
import dev.hnxtay.android_tutorial.models.Image
import dev.hnxtay.android_tutorial.ui.signin.SignInActivity

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showInfoAndLogout()
    }

    private fun showInfoAndLogout() {
        val image = intent.getParcelableExtra<Image>("image")

        with(binding) {
            textDescription.text = image?.description

            Glide.with(imageView.context).load(image?.urls?.small)
                .placeholder(R.drawable.ic_replay).into(imageView)

            buttonSignOut.setOnClickListener {
                Firebase.auth.signOut()
                startActivity(Intent(this@DetailsActivity, SignInActivity::class.java))
                finish()
            }
        }
    }
}
