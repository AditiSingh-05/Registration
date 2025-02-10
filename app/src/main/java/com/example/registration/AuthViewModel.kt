package com.example.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel: ViewModel() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>(AuthState.Unauthenticated)
    val authState : LiveData<AuthState> = _authState

    fun checkAuthStatus(){
        if(auth.currentUser == null){
            _authState.value = AuthState.Unauthenticated
        }else{
            _authState.value = AuthState.Authenticated
        }
    }
    fun login(email:String,password:String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Please fill all fields")
            return
        }
        _authState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error("Login failed")
                }

            }
    }

    fun signup(email:String,password:String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Please fill all fields")
            return
        }

        _authState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error("Signup failed")
                }

            }
    }

    fun signout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }


}
sealed class AuthState{
    object Authenticated :AuthState()
    object Unauthenticated :AuthState()
    object Loading :AuthState()
    data class Error(val message : String ) :AuthState()
}