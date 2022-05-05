package dev.hnxtay.android_tutorial.ui.authenticate.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.hnxtay.android_tutorial.databinding.FragmentSignInBinding
import dev.hnxtay.android_tutorial.ui.authenticate.signup.SignUpFragment
import dev.hnxtay.android_tutorial.ui.dashboard.MainActivity

/**
 * @author huypham on 5/6/2022
 */
class SignInFragment : Fragment() {

    companion object {
        private const val TAG = "tag"
        private const val RC_GOOGLE_SIGN_IN = 1111
        private const val TOKEN =
            "1097209996648-89lm7o2q1em77kvncgquctvdpeg9h78k.apps.googleusercontent.com"

        fun newInstance() = SignInFragment()
    }

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAuth()
        signInWithGoogle()
        clickSignIn()
        clickSignUp()
    }

    private fun initAuth() {
        auth = Firebase.auth
    }

    private fun clickSignIn() {
        with(binding) {
            btnSignIn.setOnClickListener {
                signInByEmail()
            }
        }
    }

    private fun signInByEmail() {
        with(binding) {
            val email = edtEmail.text.toString().trim() { it <= ' ' }
            val password = edtPassword.text.toString().trim() { it <= ' ' }

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "Authentication failed. ${task.exception}")
                            Toast.makeText(
                                this@SignInFragment.context,
                                "Authentication failed. ${task.exception}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    this@SignInFragment.context,
                    "Email and password are not empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun signInWithGoogle() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(TOKEN)
            .requestEmail()
            .build()

        binding.imgSignInGoogle.setOnClickListener {
            activity?.let {
                val googleSignInClient = GoogleSignIn.getClient(it, googleSignInOptions)
                startActivityForResult(googleSignInClient.signInIntent, RC_GOOGLE_SIGN_IN)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        activity?.let {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Log.d(TAG, "signInWithCredential:failure ${task.exception}")
                        Toast.makeText(it, "Authentication failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Log.d(TAG, "current user: $user")
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    private fun clickSignUp() {
        binding.tvSignUp.setOnClickListener {
            SignUpFragment.newInstance().show(childFragmentManager, SignUpFragment::class.java.name)
        }
    }
}
