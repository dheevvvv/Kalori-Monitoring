package com.dheevvvv.kalorimonitoring.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dheevvvv.kalorimonitoring.R
import com.dheevvvv.kalorimonitoring.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        Log.d("FragmentLogin", "onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentLogin", "onViewCreate")

        binding.btnLinkregist.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()



        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailLogin.text.toString()
            val password = binding.etPasswordLogin.text.toString()

            if (email.isEmpty()) {
                binding.etEmailLogin.error = "Email harus diisi"
                binding.etEmailLogin.requestFocus()

            }

            if (password.isEmpty()) {
                binding.etPasswordLogin.error = "Password harus diisi"
                binding.etPasswordLogin.requestFocus()

            } else{
                loginUser(email, password)
            }


        }
    }

    private fun loginUser(email: String, password: String) {


        val db = FirebaseFirestore.getInstance()

        // Mencari user dengan email yang diberikan
        db.collection("users")
            .whereEqualTo("email", email)
            .limit(1)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val userDoc = querySnapshot.documents[0]
                    val storedPassword = userDoc.getString("password")
                    val role = userDoc.getString("role")

                    // Verifikasi password
                    if (storedPassword == password) {
                        // Login berhasil
                        when (role) {
                            "user" -> {
                                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                            }
                            "admin" -> {
                                findNavController().navigate(R.id.action_loginFragment_to_adminHomeFragment)
                            }
                            else -> {
                                //
                            }
                        }
                    } else {

                        binding.etPasswordLogin.error = "Password salah"
                        binding.etPasswordLogin.requestFocus()
                    }
                } else {

                    binding.etEmailLogin.error = "Email tidak ditemukan"
                    binding.etEmailLogin.requestFocus()
                }
            }
            .addOnFailureListener { e ->
                //
            }
    }


}