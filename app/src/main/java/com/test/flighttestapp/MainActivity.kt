package com.test.flighttestapp

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.slider.RangeSlider
import com.test.flighttestapp.adapters.FlightsAdapter
import com.test.flighttestapp.data_fetching.FlightsRepository
import com.test.flighttestapp.data_fetching.Response
import com.test.flighttestapp.databinding.ActivityMainBinding
import com.test.flighttestapp.databinding.LayoutPriceBinding
import com.test.flighttestapp.databinding.LayoutSortBinding
import com.test.flighttestapp.lib.toRupees
import com.test.flighttestapp.lib.top_sheet.TopSheetDialog
import com.test.flighttestapp.view_models.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val adapter: FlightsAdapter by lazy { FlightsAdapter(viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
        setResponseObserver()
        viewModel.fetchFlightData(FlightsRepository(applicationContext))
    }

    private fun setResponseObserver() {
        viewModel.responseLiveData.observe(
            this,
            {
                when (it) {
                    is Response.Loading -> {
                        dataBinding.progressbar.visibility = View.VISIBLE
                    }
                    is Response.Success -> {
                        dataBinding.progressbar.visibility = View.GONE
                        it.response?.apply {
                            dataBinding.recyclerview.layoutManager =
                                LinearLayoutManager(this@MainActivity)
                            dataBinding.recyclerview.adapter = adapter
                            adapter.setFlightList(this)
                        }
                    }
                    is Response.Failure -> {
                        dataBinding.progressbar.visibility = View.GONE
                        Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        dataBinding.viewModel = viewModel
    }

    fun openSortWindow(view: View) {
        val dialog = TopSheetDialog(this, R.style.Theme_Design_TopSheetDialog)
        val dialogBinding = DataBindingUtil.inflate<LayoutSortBinding>(
            layoutInflater,
            R.layout.layout_sort,
            view.parent as ViewGroup,
            false
        )
        dialog.setContentView(dialogBinding.root)

        dialogBinding.sortApplied = viewModel.sortApplied

        dialogBinding.btnApply.setOnClickListener {
            val checkedRB =
                dialogBinding.root.findViewById<RadioButton>(dialogBinding.radioGroup.checkedRadioButtonId)
            val sortEnum = checkedRB.tag as SortingEnum
            viewModel.applySorting(sortEnum)
            dialog.dismiss()
        }

        dialog.show()
    }

    fun openPriceWindow(view: View) {
        val dialog = TopSheetDialog(this, R.style.Theme_Design_TopSheetDialog)
        val dialogBinding = DataBindingUtil.inflate<LayoutPriceBinding>(
            layoutInflater,
            R.layout.layout_price,
            view.parent as ViewGroup,
            false
        )
        dialog.setContentView(dialogBinding.root)

        val minPrice = viewModel.getMinPrice()
        val maxPrice = viewModel.getMaxPrice()
        dialogBinding.minPrice = minPrice
        dialogBinding.maxPrice = maxPrice
        //dialogBinding.rangeSlider.values = viewModel.getPriceList() as MutableList<Float>
        dialogBinding.rangeSlider.values =
            mutableListOf(viewModel.fromPriceSelected, viewModel.toPriceSelected)
        dialogBinding.tvMinPrice.text = viewModel.fromPriceSelected.toString().toRupees()
        dialogBinding.tvMaxPrice.text = viewModel.toPriceSelected.toString().toRupees()


        dialogBinding.btnApply.setOnClickListener {
            viewModel.setPriceRange(
                true,
                dialogBinding.rangeSlider.values[0],
                dialogBinding.rangeSlider.values[1]
            )
            dialog.dismiss()
        }

        dialogBinding.rangeSlider.addOnSliderTouchListener(object :
            RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: RangeSlider) {
            }

            override fun onStopTrackingTouch(slider: RangeSlider) {
                dialogBinding.tvMinPrice.text = slider.values[0].toString().toRupees()
                dialogBinding.tvMaxPrice.text = slider.values[1].toString().toRupees()
            }
        })

        dialog.show()
    }
}