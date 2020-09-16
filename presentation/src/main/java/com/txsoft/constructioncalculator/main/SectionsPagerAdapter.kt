package com.txsoft.constructioncalculator.main

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.txsoft.constructioncalculator.interfaces.OnResourceInflater
import com.txsoft.constructioncalculator.models.IResource

abstract class SectionsPagerAdapter<T : IResource>(
    resource: OnResourceInflater<T>,
    fm: FragmentManager
) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    protected val typesList = resource.getTypeSet().toList()
    protected val resourceTypeMap = resource.getTypedResourceMap()


    override fun getPageTitle(position: Int): CharSequence? =
        typesList[position]

    override fun getCount(): Int = typesList.size
}