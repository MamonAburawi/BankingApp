package com.mamon.bankingapp.screens.home

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.mamon.bankingapp.R
import com.mamon.bankingapp.data.Transaction
import com.mamon.bankingapp.databinding.HomeBinding
import com.mamon.bankingapp.databinding.TransactionsBottomSheetBinding
import com.mamon.bankingapp.screens.profile.ProfileViewModel
import com.mamon.bankingapp.utils.daysList
import com.mamon.bankingapp.utils.navigateHomeToProfile
import com.mamon.bankingapp.utils.showMessage
import com.mamon.bankingapp.utils.transactionsData
import com.naqdi.chart.model.Line

class Home : Fragment() {


    private lateinit var binding: HomeBinding
    private lateinit var viewModel: HomeViewModel
    private val profileViewModel by activityViewModels<ProfileViewModel>()

    private val transactionsSheet = TransactionsBottomSheet()



    // fake data
    companion object{
        val LAST_WEEK_DATA = listOf(18f, 280f, 88f, 70f, 23f, 33f)
        val LAST_MONTH_DATA = listOf(18f, 142f, 241f, 70f, 80f, 22f)
        val ALL_TIME_DATA = listOf(10f, 32f, 55f, 420f, 23f, 48f,200f)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = HomeBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]


        setViews()


        return binding.root
    }


    private fun setViews(){
        setTaps()
        setButtons()
        binding.apply {
            profileVM = profileViewModel
            lifecycleOwner = this@Home
        }
    }

    private fun setButtons() {

        binding.apply {
            /** button show transaction **/
            btnShowTransactions.setOnClickListener {
                transactionsSheet.show(requireActivity().supportFragmentManager, transactionsSheet.tag)
            }

            /** button go to profile **/
            btnProfile.setOnClickListener {
                navigateHomeToProfile()
//                findNavController().navigate(R.id.action_home_to_profile)
            }
        }
    }


    private fun setTaps(){
        binding.apply {
            // to init the chart
            setBalanceChart()

            val tabTitles = arrayOf("LAST WEEK", "LAST MONTH", "ALL TIME")
            tabTitles.forEachIndexed { index, title ->
                tabLayout.addTab(tabLayout.newTab().setText(title))

                tabLayout.getTabAt(index)?.view?.setOnClickListener {
                    when (index) {
                        0 -> { setBalanceChart(LAST_WEEK_DATA) }
                        1 -> { setBalanceChart(LAST_MONTH_DATA) }
                        2 -> { setBalanceChart(ALL_TIME_DATA) }
                    }
                }

            }

        }
    }



    private fun setBalanceChart(data: List<Float> = LAST_WEEK_DATA) {
        val intervalList = daysList
        val rangeList = listOf("0-1K", "100K", "200K", "500K")
        val lineList = arrayListOf<Line>().apply {
            add(Line("Balance", Color.RED,data))
        }
        binding.chainChartView.setData(lineList, intervalList, emptyList())
    }



    class TransactionsBottomSheet: SuperBottomSheetFragment() {

        private lateinit var binding: TransactionsBottomSheetBinding
        private val transactionsAdapter = TransactionsAdapter()

        companion object {
            private const val CORNER_RADIUS = 120f
            private const val STATUS_BAR_COLOR = Color.WHITE
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
            super.onCreateView(inflater, container, savedInstanceState)
            binding = TransactionsBottomSheetBinding.inflate(inflater,container,false)

            setViews()

            return binding.root
        }

        override fun getCornerRadius() = CORNER_RADIUS

        override fun getStatusBarColor() = STATUS_BAR_COLOR

        private fun setViews(){
            setTransactionAdapter()
            binding.apply {

            }
        }


        private fun setTransactionAdapter(){
            transactionsAdapter.submitList(transactionsData())
            transactionsAdapter.listener = object: TransactionsAdapter.TransactionListener{
                override fun onClick(data: Transaction) {
                    showMessage(data.name)
                }
            }

            binding.rvTransactions.adapter = transactionsAdapter

        }
    }





}