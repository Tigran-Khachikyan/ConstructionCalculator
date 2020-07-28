package com.txsoft.constructioncalculator.ui.main.shapes


import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.txsoft.constructioncalculator.R

class SectionsPagerAdapterShapes(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

     private val titles = arrayOf(
        R.string.tab_shapes_cons,
        R.string.tab_shapes_geo
    )

    override fun getItem(position: Int): Fragment {
        return ShapePagerFragment();
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(titles[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}