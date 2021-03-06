package com.github.restorantsnet.screens.mainfragment

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.restorantsnet.FragmentViewBinding
import com.github.restorantsnet.data.Cash
import com.github.restorantsnet.data.TotalDayCash
import com.github.restorantsnet.databinding.FragmentMainBinding
import com.github.restorantsnet.screens.adapters.CashAdapter

class MainFragment : FragmentViewBinding<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val viewModel: MainViewModel by viewModels()
    private val recAdapter = CashAdapter()

    override fun onResume() {
        super.onResume()
        initUI()
        bindViewModel()
        initList()
    }

    private fun initUI() {
        binder.recView.apply {
            adapter = recAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            setOnTouchListener(
                fun(v: View, _: MotionEvent): Boolean {
                    val imm =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                    return false
                }
            )
        }
        binder.btnCount.setOnClickListener {
            viewModel.countCash(getCashList())
        }
    }

    private fun initList() {
        viewModel.loadCashList()
    }

    private fun bindViewModel() {
        viewModel.apply {
            getCashList.observe(viewLifecycleOwner) { newList ->
                recAdapter.apply {
                    setCashList(newList)
                    notifyDataSetChanged()
                }
            }
            getCashSum.observe(viewLifecycleOwner) { newSum ->
                val startCashAmount = getCashList()[0].cashAmount
                val startDayCash = getCashList()[0].countMethod.countCash(startCashAmount)
                val totalDayCash = TotalDayCash(
                    startDayCash = startDayCash,
                    endDayCash = newSum
                )
                val action =
                    MainFragmentDirections.actionMainFragmentToBottomSheetSumFragment(totalDayCash)
                findNavController().navigate(action)
            }
        }
    }

    private fun getCashList(): List<Cash> {
        //список количества каждой купюры + размен
        return recAdapter.getEnteredList.mapIndexed { index, s ->
            recAdapter.getItemById(index).copy(
                cashAmount = s
            )
        }
    }
}