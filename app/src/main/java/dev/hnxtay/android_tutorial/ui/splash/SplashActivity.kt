package dev.hnxtay.android_tutorial.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import dev.hnxtay.android_tutorial.R
import dev.hnxtay.android_tutorial.ui.authenticate.AuthenticationActivity
import dev.hnxtay.android_tutorial.ui.dashboard.post.PostActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val DELAY_TIME : Long = 2_000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateNextScreen()
    }

    private fun navigateNextScreen() {
        val currentUser = FirebaseAuth.getInstance().currentUser

        lifecycleScope.launch {
            delay(DELAY_TIME)
            if (currentUser != null) {
                startActivity(Intent(this@SplashActivity, PostActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, AuthenticationActivity::class.java))
            }
            finish()
        }
    }
}
