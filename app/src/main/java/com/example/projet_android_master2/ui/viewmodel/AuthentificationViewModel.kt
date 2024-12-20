package com.example.projet_android_master2.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projet_android_master2.firebase.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseUser

class AuthentificationViewModel : ViewModel() {


    private val mFirebaseAuthRepository: FirebaseAuthRepository by lazy { FirebaseAuthRepository() }
    var mCurrentUser = MutableLiveData<FirebaseUser>()
    var mErrorProcess = MutableLiveData<Int>()


    init {
        mCurrentUser = mFirebaseAuthRepository.mCurrentUser
        mErrorProcess = mFirebaseAuthRepository.mErrorProcess
    }


    fun loginUser(email: String, password: String) {
        mFirebaseAuthRepository.loginUser(email, password)
    }

    fun registerNewUser(email: String, password: String) {
        mFirebaseAuthRepository.registerNewUser(email, password)
    }


    fun disconnectUser() {
        mFirebaseAuthRepository.disconnectUser()
    }
}
