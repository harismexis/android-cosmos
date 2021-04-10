package com.harismexis.cosmos.mrp.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.ActivityMrpBinding
import com.harismexis.cosmos.mrp.adapter.MRPItemAdapter
import com.harismexis.cosmos.mrp.model.ui.MRPItemModel
import com.harismexis.cosmos.mrp.viewholder.MRPItemVh
import com.harismexis.cosmos.mrp.viewmodel.MRPVm
import com.harismexis.cosmos.workshared.activity.BaseFragment

/**
 * Showing a list of Mars Rover Photos (MRP)
 */
class MRPFragment : BaseFragment(), MRPItemVh.MRPItemClickListener {

    private val viewModel: MRPVm by viewModels { viewModelFactory }
    private var binding: ActivityMrpBinding? = null
    private lateinit var adapter: MRPItemAdapter
    private var mrpItems: MutableList<MRPItemModel> = mutableListOf()

    override fun onViewCreated() {
        observeLiveData()
        viewModel.fetchCuriosityLatestMRP()
    }

    override fun initialiseViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = ActivityMrpBinding.inflate(inflater, container, false)
    }

    override fun getRootView(): View? {
        return binding?.root
    }

    override fun observeLiveData() {
        viewModel.models.observe(viewLifecycleOwner, {
            updateUI(it)
        })
    }

    override fun initialiseView() {
        setupToolbar()
        initRecycler()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph)
        binding?.toolbar?.setupWithNavController(navController, appBarConf)
        binding?.toolbar?.setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
    }

    private fun initRecycler() {
        adapter = MRPItemAdapter(mrpItems, this)
        adapter.setHasStableIds(true)
        binding?.rvList?.let {
            it.layoutManager = LinearLayoutManager(requireActivity())
            it.adapter = adapter
        }
    }

    private fun updateUI(uiModel: List<MRPItemModel>) {
        mrpItems.clear()
        mrpItems.addAll(uiModel)
        adapter.notifyDataSetChanged()
    }

    override fun onMRPItemClick(
        item: MRPItemModel,
        position: Int
    ) {

    }

}