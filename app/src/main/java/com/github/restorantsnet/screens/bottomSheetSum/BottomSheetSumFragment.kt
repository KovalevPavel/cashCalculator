package com.github.restorantsnet.screens.bottomSheetSum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.github.restorantsnet.databinding.FragmentBottomSheetSumBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetSumFragment : BottomSheetDialogFragment() {
    private var _binder: FragmentBottomSheetSumBinding? = null
    private val binder: FragmentBottomSheetSumBinding
        get() = _binder!!
    private val args: BottomSheetSumFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binder = FragmentBottomSheetSumBinding.inflate(inflater, container, false)
        return binder.root
    }

    override fun onResume() {
        super.onResume()
        bindUI()
    }

    private fun bindUI() {
        val cashData = args.dayCash
        binder.apply {
            textDayStart.text = cashData.startDayCash.toString()
            cashData.endDayCash.toString().also { textDayEnd.text = it }
            textTotal.text = cashData.getProfit()
        }
    }
}