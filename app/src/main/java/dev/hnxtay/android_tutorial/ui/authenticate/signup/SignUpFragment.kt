package dev.hnxtay.android_tutorial.ui.authenticate.signup

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.hnxtay.android_tutorial.R
import dev.hnxtay.android_tutorial.databinding.FragmentSignUpBinding
import dev.hnxtay.android_tutorial.ui.dashboard.post.PostActivity

/**
 * @author huypham on 5/6/2022
 */
class SignUpFragment: DialogFragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        val displayMetrics = DisplayMetrics()
        dialog?.window?.run {
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, (displayMetrics.heightPixels / 1.5).toInt())
            setGravity(Gravity.BOTTOM)
            setWindowAnimations(R.style.DialogStyle)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAuth()
        clickSignUp()
    }

    private fun initAuth() {
        auth = Firebase.auth
    }

    private fun clickSignUp() {
        binding.btnSignUp.setOnClickListener {
            signUpByEmail()
        }
    }

    private fun signUpByEmail() {
        with(binding) {
            val email = edtEmail.text.toString().trim() {it <= ' '}
            val password = edtPassword.text.toString().trim() {it <= ' '}

            if (email.isNotEmpty() && password.isNotEmpty()) {
                activity?.let {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(it) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                val user = auth.currentUser
                                updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.d("TAG","Authentication failed. ${task.exception}")
                                Toast.makeText(
                                    it, "Authentication failed. ${task.exception}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            } else {
                Toast.makeText(this@SignUpFragment.context, "Email and password are not empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            println("current user: $user")
            val intent = Intent(activity, PostActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}
