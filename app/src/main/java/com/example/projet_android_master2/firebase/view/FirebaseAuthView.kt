package com.example.projet_android_master2.firebase.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projet_android_master2.databinding.FragmentFirebaseAuthBinding
import com.example.projet_android_master2.firebase.viewmodel.FirebaseAuthViewModel
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthView : Fragment() {

    private lateinit var mViewModel: FirebaseAuthViewModel
    private var _binding: FragmentFirebaseAuthBinding? = null
    private val binding get() = _binding!!

    private val mObserverUser = Observer<FirebaseUser> {
        updateUser(it)
    }

    private val mObserverError = Observer<Int> {
        updateError(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialiser le View Binding
        _binding = FragmentFirebaseAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialisation du ViewModel
        mViewModel = ViewModelProvider(this)[FirebaseAuthViewModel::class.java]

        // Mise en place des listeners pour les boutons
        binding.firebaseButtonRegister.setOnClickListener { register() }
        binding.firebaseButtonLogin.setOnClickListener { login() }
        binding.firebaseButtonDisconnect.setOnClickListener { disconnect() }
    }

    override fun onStart() {
        super.onStart()
        mViewModel.mCurrentUser.observe(viewLifecycleOwner, mObserverUser)
        mViewModel.mErrorProcess.observe(viewLifecycleOwner, mObserverError)
    }

    override fun onStop() {
        mViewModel.mCurrentUser.removeObserver(mObserverUser)
        mViewModel.mErrorProcess.removeObserver(mObserverError)
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Éviter les fuites de mémoire
    }

    private fun checkConformityFields(): Boolean {
        var isValid = true
        val email = binding.firebaseUserEmail.text.toString()
        val password = binding.firebaseUserPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            isValid = false
            binding.firebaseError.text = "empty field"
        }
        return isValid
    }

    private fun login() {
        if (checkConformityFields()) {
            mViewModel.loginUser(
                binding.firebaseUserEmail.text.toString(),
                binding.firebaseUserPassword.text.toString()
            )
        }
    }

    private fun register() {
        if (checkConformityFields()) {
            mViewModel.registerNewUser(
                binding.firebaseUserEmail.text.toString(),
                binding.firebaseUserPassword.text.toString()
            )
        }
    }

    private fun disconnect() {
        mViewModel.disconnectUser()
        binding.firebaseLog.text = "none"
        binding.firebaseError.text = "disconnected"
    }

    private fun updateUser(user: FirebaseUser) {
        user.let {
            binding.firebaseLog.text = "${user.uid}-${user.email}"
        }
    }

    private fun updateError(code: Int) {
        when (code) {
            5 -> {
                binding.firebaseError.text = "disconnected"
                binding.firebaseLog.text = "none"
            }
            9 -> binding.firebaseError.text = "current user null"
            10 -> binding.firebaseError.text = "Error when creating"
            11 -> binding.firebaseError.text = "Error when login"
            else -> binding.firebaseError.text = "All is good"
        }
    }
}
