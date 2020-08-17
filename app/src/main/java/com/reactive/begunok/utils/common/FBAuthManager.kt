package com.reactive.begunok.utils.common

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import com.reactive.begunok.utils.extensions.loge
import java.security.MessageDigest

class FBAuthManager(private val activity: FragmentActivity) {
    private val callbackManager: CallbackManager = CallbackManager.Factory.create()
    private val TAG = this::class.java.simpleName

    init {
        FacebookSdk.sdkInitialize(activity.applicationContext)
    }

    fun login(action: () -> Unit) {
        LoginManager.getInstance()
            .logInWithReadPermissions(activity, listOf("email", "public_profile"))
        LoginManager.getInstance().registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Log.e(TAG, "onSuccess: " + result?.getAccessToken())
                action()
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(
                    activity,
                    "Что-то пошло не так. Пожалуйста, попробуйте еще раз",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e(TAG, "Error in FB Auth")
                error?.let {
                    Log.e(TAG, error.localizedMessage)
                    it.printStackTrace()
                }

            }

        })
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    @SuppressLint("PackageManagerGetSignatures")
    fun getHashkey(activity: Activity) {
        try {
            val info: PackageInfo = activity.packageManager.getPackageInfo(
                activity.packageName,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.e(TAG, "Base64 >> " + Base64.encodeToString(md.digest(), Base64.NO_WRAP))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "Name not found" + e.message, e)
        }
    }
}
