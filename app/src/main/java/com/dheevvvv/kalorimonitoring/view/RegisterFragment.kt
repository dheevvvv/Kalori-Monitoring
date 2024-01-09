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
import com.dheevvvv.kalorimonitoring.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        Log.d("FragmentRegister", "onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentRegister", "onViewCreate")


        binding.btnKembali.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.btnLanjutkan.setOnClickListener {
            val email = binding.etEmailRegist.text.toString()
            val username = binding.etUsernameRegist.text.toString()
            val password = binding.etPasswordRegist.text.toString()
            val confirmPassword = binding.etConfirmPasswordRegist.text.toString()

            if (email.isEmpty()) {
                binding.etEmailRegist.error = "Email harus diisi"
                binding.etEmailRegist.requestFocus()

            }

            if (username.isEmpty()) {
                binding.etUsernameRegist.error = "Username harus diisi"
                binding.etUsernameRegist.requestFocus()

            } else{
                if (password!=confirmPassword){
                    binding.etPasswordRegist.error = "password tidak sama dengan confirm password"
                    binding.etPasswordRegist.requestFocus()
                }
                else{
                    val bundle = Bundle()
                    bundle.putString("email", email)
                    bundle.putString("username", username)
                    bundle.putString("password", password)
                    findNavController().navigate(R.id.action_registerFragment_to_getStartedFragment2, bundle)
                }
            }


        }

    }

}