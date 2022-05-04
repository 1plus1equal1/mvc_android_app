package dev.hnxtay.android_tutorial.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import dev.hnxtay.android_tutorial.R
import dev.hnxtay.android_tutorial.ui.main.MainActivity
import dev.hnxtay.android_tutorial.ui.signin.SignInActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val currentUser = FirebaseAuth.getInstance().currentUser

        lifecycleScope.launch {
            delay(5_000)
            if (currentUser != null) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
            startActivity(Intent(this@SplashActivity, SignInActivity::class.java))
            finish()
        }
    }
}