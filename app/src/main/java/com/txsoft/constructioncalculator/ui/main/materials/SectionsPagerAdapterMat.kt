package com.txsoft.constructioncalculator.ui.main.materials

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.models.enums.MaterialType.*


class SectionsPagerAdapterMat(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val titles = arrayOf(
        R.string.tab_mat_metals,
        R.string.tab_mat_wood,
        R.string.tab_mat_other
    )


    override fun getItem(position: Int): Fragment {
        val type = when (position) {
            0 -> METAL
            1 -> WOOD
            else -> OTHER
        }
        return MaterialsPagerFragment(type)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(titles[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 3
    }
}