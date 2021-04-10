package com.harismexis.cosmos.mrp.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.FragmentMrpBinding
import com.harismexis.cosmos.mrp.adapter.MRPItemAdapter
import com.harismexis.cosmos.mrp.model.ui.MRPUiModel
import com.harismexis.cosmos.mrp.viewholder.MRPItemVh
import com.harismexis.cosmos.mrp.viewmodel.MRPVm
import com.harismexis.cosmos.workshared.activity.BaseFragment

/**
 * Shows a list of Mars Rover Photos (MRP)
 */
class MRPFragment : BaseFragment(), MRPItemVh.MRPItemClickListener {

    private val viewModel: MRPVm by viewModels { viewModelFactory }
    private var binding: FragmentMrpBinding? = null
    private lateinit var rvAdapter: MRPItemAdapter
    private var uiModels: MutableList<MRPUiModel> = mutableListOf()

    override fun onViewCreated() {
        observeLiveData()
        viewModel.fetchCuriosityLatestMRP()
    }

    override fun initialiseViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentMrpBinding.inflate(inflater, container, false)
    }

    override fun getRootView(): View? {
        return binding?.root
    }

    override fun initialiseView() {
        setupToolbar()
        initRecycler()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph)
        binding?.toolbar?.apply {
            setupWithNavController(navController, appBarConf)
            setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
        }
    }

    private fun initRecycler() {
        rvAdapter = MRPItemAdapter(uiModels, this)
        rvAdapter.setHasStableIds(true)
        binding?.rvList?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = rvAdapter
        }
    }

    override fun observeLiveData() {
        viewModel.models.observe(viewLifecycleOwner, {
            updateUI(it)
        })
    }

    private fun updateUI(models: List<MRPUiModel>) {
        binding?.apply {
            progressBar.visibility = View.GONE
            rvList.visibility = View.VISIBLE
        }
        this.uiModels.clear()
        this.uiModels.addAll(models)
        rvAdapter.notifyDataSetChanged()
    }

    override fun onMRPItemClick(
        item: MRPUiModel,
        position: Int
    ) {

    }

}