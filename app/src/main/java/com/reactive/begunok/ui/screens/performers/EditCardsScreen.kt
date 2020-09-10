package com.reactive.begunok.ui.screens.performers

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.reactive.begunok.R
import com.reactive.begunok.base.BaseFragment
import kotlinx.android.synthetic.main.content_header.*
import kotlinx.android.synthetic.main.screen_edit_cards.*

class EditCardsScreen : BaseFragment(R.layout.screen_edit_cards) {
    private var data = arrayListOf<Data>()
    override fun initialize() {
        initClicks()

        initViews()

        data = arrayListOf(
            Data(R.drawable.card, "card ", EditPhoneScreen()),
            Data(R.drawable.card, "card", EditPhoneScreen()),
            Data(R.drawable.card, "card ", EditPhoneScreen())
        )
        viewPager.adapter = DataPagerAdapter(data, childFragmentManager)
    }

    private fun initClicks() {
        add.setOnClickListener { addFragment(AddCardsScreen()) }

        remove.setOnClickListener { addFragment(RemoveCardsScreen()) }

        close.setOnClickListener { finishFragment() }
    }

    private fun initViews() {
        header.text = "Мои платежные карты"
    }
}

data class Data(@DrawableRes val icon: Int, val title: String, val item: Fragment)

data class DataPagerAdapter(private val data: ArrayList<Data>, val fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = data[position].item

    override fun getCount(): Int = data.size

    override fun getPageTitle(position: Int) = data[position].title

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
        try {
            super.restoreState(state, loader)
        } catch (e: Exception) {
        }
    }

    override fun saveState(): Parcelable? = null
}

