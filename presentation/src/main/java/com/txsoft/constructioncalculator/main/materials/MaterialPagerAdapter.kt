package com.txsoft.constructioncalculator.main.materials

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.txsoft.constructioncalculator.interfaces.OnResourceInflater
import com.txsoft.constructioncalculator.main.SectionsPagerAdapter
import com.txsoft.constructioncalculator.models.IMaterial

class MaterialPagerAdapter(
    resource: OnResourceInflater<IMaterial>,
    fm: FragmentManager
) :
    SectionsPagerAdapter<IMaterial>(resource, fm) {

    override fun getItem(position: Int): Fragment {
        return MaterialsPagerFragment(resourceTypeMap[typesList[position]])
    }
}