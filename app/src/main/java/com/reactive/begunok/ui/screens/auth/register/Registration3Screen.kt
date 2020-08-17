package com.reactive.begunok.ui.screens.auth.register

import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import com.reactive.begunok.ui.screens.auth.ChooseModeScreen
import com.reactive.begunok.utils.extensions.blockClickable
import com.reactive.begunok.utils.extensions.loadImage
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.screen_reg3.*

class Registration3Screen : BaseFragment(R.layout.screen_reg3) {

    private var path = ""
    override fun initialize() {

        back.setOnClickListener { finishFragment() }

        next.setOnClickListener {
            replaceFragment(
                ChooseModeScreen(),
                id = viewModel.parentLayoutId
            )
        }

        addImage.setOnClickListener {
            it.blockClickable()
            TedImagePicker.with(requireContext())
                .start { uri ->
                    path = mainActivity.getFilePath(uri)
                    addImage.loadImage(path)
                }
        }
    }
}