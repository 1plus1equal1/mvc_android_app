package dev.hnxtay.android_tutorial.ui.authenticate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import dev.hnxtay.android_tutorial.R
import dev.hnxtay.android_tutorial.ui.authenticate.signin.SignInFragment

/**
 * @author huypham on 5/6/2022
 */
class AuthenticationActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        initFragment()
    }

    private fun initFragment() {
        supportFragmentManager.commit {
            add<SignInFragment>(R.id.frContainer)
        }
    }
}
