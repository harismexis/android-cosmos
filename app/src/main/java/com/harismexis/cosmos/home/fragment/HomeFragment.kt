package com.harismexis.cosmos.home.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.FragmentHomeBinding
import com.harismexis.cosmos.home.adapter.HomeAdapter
import com.harismexis.cosmos.home.interfaces.HomeClickListener
import com.harismexis.cosmos.home.viewmodel.HomeVm
import com.harismexis.cosmos.workshared.activity.BaseFragment
import com.harismexis.cosmos.workshared.enums.RowType

/**
 * Shows a list of all NASA APIs that can be explored in the project.
 */
class HomeFragment : BaseFragment(), HomeClickListener {

    private val viewModel: HomeVm by viewModels { viewModelFactory }
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeAdapter

    override fun initialiseViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun getRootView(): View? {
        return binding?.root
    }

    private fun observeLiveData() {
        //
    }

    override fun initialiseView() {
        setupRecycler()
    }

    override fun onViewCreated() {}

    override fun onHomeItemClick(rowType: RowType, position: Int) {
        when (rowType) {
            RowType.APOD -> findNavController().navigate(R.id.action_home_dest_to_apod_dest)
            RowType.MRP -> findNavController().navigate(R.id.action_home_dest_to_mrp_dest)
            RowType.NIAVL -> findNavController().navigate(R.id.action_home_dest_to_niavl_dest)
        }
    }

    private fun setupRecycler() {
        adapter = HomeAdapter(viewModel.getHomeEntries(), this)
        binding?.rvHome?.let {
            it.layoutManager = LinearLayoutManager(requireActivity())
            it.adapter = adapter
        }
    }

}