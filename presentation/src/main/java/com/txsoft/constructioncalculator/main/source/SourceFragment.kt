package com.txsoft.constructioncalculator.main.source

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.txsoft.constructioncalculator.R
import com.txsoft.constructioncalculator.databinding.FragmentSourceBinding

class SourceFragment : Fragment() {

    private lateinit var sourceViewModel: SourceViewModel
    private lateinit var fragmentSourceBinding: FragmentSourceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sourceViewModel = ViewModelProvider(this).get(SourceViewModel::class.java)
        fragmentSourceBinding = FragmentSourceBinding.inflate(inflater, container, false)
        return fragmentSourceBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSourceBinding.apply {
            btnGoToMat.text = "Materials"
            btnGoToShapes.text = "Shapes"
            navController = view.findNavController()
        }
    }
}