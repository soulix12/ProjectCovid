package com.example.covid2019.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController

import com.example.covid2019.R
import com.example.covid2019.databinding.FragmentLoginBinding
import com.example.covid2019.util.hideKeyboard

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val v = binding.root

        binding.btnLogin.setOnClickListener {
            it.hideKeyboard()
            login()
        }

        binding.btnSignup.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
        return v
    }

    private fun login() {
//        if (binding.editTextEmail.text!!.isBlank()) {
//            binding.editTextEmail.error = "Please enter your login email"
//            return
//        }
//
//        if (binding.editTextPassword.text!!.isBlank()) {
//            binding.editTextPassword.error = "Please enter your password"
//            return
//        }
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
