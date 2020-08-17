package com.reactive.begunok.utils.common

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.reactive.begunok.BuildConfig

class GoogleAuthManager(private val activity: FragmentActivity) {

    // 1. Create project in console.developers.google.com
    // 2. Create project in firebase
    // 3. Add App SHA1 Debug & Release Key in firebase
    // 4. Add also Email in firebase app settings
    // 5. Download and paste google-services.json to app
    // 6. Copy web client id and paste it here

    companion object {
        private const val AUTH_REQUEST_CODE = 25
    }

    private lateinit var googleSignInClient: GoogleSignInClient

    init {
        initialize()
    }

    private fun initialize() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(BuildConfig.CLIENT_ID)
            .build()

        googleSignInClient = GoogleSignIn.getClient(activity, gso)
    }

    fun startRequest() {
        activity.startActivityForResult(googleSignInClient.signInIntent, AUTH_REQUEST_CODE)
    }

    fun logOut(action: () -> Unit) {
        googleSignInClient.signOut().addOnCompleteListener {
            action()
        }
    }

    fun activityResult(requestCode: Int, data: Intent?, action: (GoogleSignInAccount?) -> Unit) {

        if (requestCode == AUTH_REQUEST_CODE) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                action(account)
            } catch (e: ApiException) {
                val code = e.statusCode
                e.printStackTrace()
                when (code) {
                    7 -> Toast.makeText(
                        activity,
                        "Проверьте подключение к интернету. И попробуй еще раз",
                        Toast.LENGTH_SHORT
                    ).show()
                    10 -> Toast.makeText(
                        activity,
                        "Что-то пошло не так. Пожалуйста, попробуйте еще раз",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}