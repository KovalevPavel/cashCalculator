package com.github.restorantsnet.screens.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.restorantsnet.data.Cash
import com.github.restorantsnet.databinding.ItemCashBinding

class CashAdapter : RecyclerView.Adapter<CashAdapter.CashViewHolder>() {
    private var cashList = mutableListOf<Cash>()
    private var enteredList = mutableListOf<String>()
    private var _binder: ItemCashBinding? = null
    private val binder: ItemCashBinding
        get() = _binder!!

    val getEnteredList: List<String>
        get() = enteredList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CashViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _binder = ItemCashBinding.inflate(inflater, parent, false)
        return CashViewHolder(binder)
    }

    override fun onBindViewHolder(holder: CashViewHolder, position: Int) {
        holder.bindCash(cashList[position])
        holder.binder.editText.apply {
            setText(enteredList[holder.adapterPosition])
            addTextChangedListener(
                MyTextWatcher {newString ->
                    enteredList[holder.adapterPosition] = newString
                }
            )
        }
    }

    override fun getItemCount() = cashList.size

    fun setCashList(newCashList: List<Cash>) {
        cashList = newCashList.toMutableList()
        enteredList = MutableList(cashList.size) { "" }
    }

    fun getItemById(id: Int) = cashList[id]

    class CashViewHolder(
        val binder: ItemCashBinding
    ) : RecyclerView.ViewHolder(binder.root) {
        fun bindCash(item: Cash) {
            binder.apply {
                textValue.text = item.value
                textCurrency.text = item.currency.sign
            }
        }
    }

    class MyTextWatcher(private val onTextEntered: (String) -> Unit) :
        TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            onTextEntered(s.toString())
        }
    }
}