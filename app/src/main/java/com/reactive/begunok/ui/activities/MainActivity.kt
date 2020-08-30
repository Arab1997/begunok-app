package com.reactive.begunok.ui.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.view.KeyEvent
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseActivity
import com.reactive.begunok.base.BaseViewModel
import com.reactive.begunok.base.initialFragment
import com.reactive.begunok.network.models.RegisterModel
import com.reactive.begunok.ui.screens.BottomNavScreen
import com.reactive.begunok.ui.screens.auth.AuthScreen
import com.reactive.begunok.ui.screens.auth.ChooseModeScreen
import com.reactive.begunok.ui.screens.auth.login.LoginScreen
import com.reactive.begunok.ui.screens.splash.SplashScreen
import com.reactive.begunok.utils.common.FBAuthManager
import com.reactive.begunok.utils.common.GoogleAuthManager
import com.reactive.begunok.utils.extensions.showGone
import com.reactive.begunok.utils.preferences.SharedManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class MainActivity : BaseActivity(R.layout.activity_main) {

    val viewModel by viewModel<BaseViewModel>()
    val sharedManager: SharedManager by inject()

    companion object {
        var client: Boolean = false
        val data = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, "")
        var googleAuthManager: GoogleAuthManager? = null
        var fbAuthManager: FBAuthManager? = null
    }

    override fun onActivityCreated() {

        viewModel.apply {
            parentLayoutId = R.id.fragmentContainer
            navLayoutId = R.id.navContainer

            fetchData()
        }

//        debug()
        startFragment()

        initSocialAuth()
    }

    private fun register() {
        RegisterModel.apply {
            email = "aa@${System.currentTimeMillis()}.aa"
            name = "${System.currentTimeMillis()}"
            password = "psw"
            phone = "${System.currentTimeMillis()}"
            city = "asjldk"
            avatarFile = null
            documents = null
            contractor = false
        }
        viewModel.register()
    }

    private fun initSocialAuth() {
        googleAuthManager = GoogleAuthManager(this)
        fbAuthManager = FBAuthManager(this)
    }

    private fun debug() = initialFragment(LoginScreen())

    private fun startFragment() {
        val authFragment = AuthScreen().apply {
            setGoogleListener { googleAuthManager?.startRequest() }
            setFBListener {
                fbAuthManager?.login {
                    initialFragment(ChooseModeScreen(), true)
                }
            }
        }
        initialFragment(
            if (sharedManager.token.isEmpty()) SplashScreen().apply {
                setListener { initialFragment(authFragment, false) }
            }
            else BottomNavScreen(), true
        )
    }

    fun showProgress(show: Boolean) {
        progressBar.showGone(show)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (event.keyCode) {
                KeyEvent.KEYCODE_VOLUME_UP -> return false
                KeyEvent.KEYCODE_VOLUME_DOWN -> return false
            }
        }
        return super.dispatchKeyEvent(event)
    }

    fun getFilePath(it: Uri): String {
        //Later we will use this bitmap to create the File.
        val selectedBitmap = getBitmap(this, it)!!

        /*We can access getExternalFileDir() without asking any storage permission.*/
        val selectedImgFile = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            System.currentTimeMillis()
                .toString() + "_selectedImg.jpg"
        )

        convertBitmapToFile(selectedImgFile, selectedBitmap)

        return selectedImgFile.path
    }

    private fun getBitmap(context: Context, imageUri: Uri): Bitmap? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    context.contentResolver,
                    imageUri
                )
            )

        } else {
            context.contentResolver.openInputStream(imageUri)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        }
    }

    private fun convertBitmapToFile(destinationFile: File, bitmap: Bitmap) {
        //create a file to write bitmap data
        destinationFile.createNewFile()
        //Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos)
        val bitmapData = bos.toByteArray()
        //write the bytes in file
        val fos = FileOutputStream(destinationFile)
        fos.write(bitmapData)
        fos.flush()
        fos.close()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        googleAuthManager?.activityResult(requestCode, data) {
            initialFragment(ChooseModeScreen(), true)
        }
        fbAuthManager?.onActivityResult(requestCode, resultCode, data)
    }

}
