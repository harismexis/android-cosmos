package com.harismexis.cosmos.niavl.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.cosmos.R
import com.harismexis.cosmos.databinding.FragmentNiavlBinding
import com.harismexis.cosmos.niavl.adapter.NIAVLItemAdapter
import com.harismexis.cosmos.niavl.model.remote.NIAVLDataEntry
import com.harismexis.cosmos.niavl.model.ui.NIAVLUiModel
import com.harismexis.cosmos.niavl.viewholder.NIAVLItemVh
import com.harismexis.cosmos.niavl.viewmodel.NIAVLVm
import com.harismexis.cosmos.workshared.activity.BaseFragment
import com.harismexis.cosmos.workshared.util.ui.hideKeyboard

/**
 * Screen to search for image / video / audio in
 * NASA Image and Video Library (NIAVL)
 */
class NIAVLFragment : BaseFragment(), NIAVLItemVh.NIAVLItemClickListener,
    android.widget.SearchView.OnQueryTextListener {

    private val viewModel: NIAVLVm by viewModels { viewModelFactory }
    private var binding: FragmentNiavlBinding? = null
    private lateinit var rvAdapter: NIAVLItemAdapter
    private var uiModels: MutableList<NIAVLUiModel> = mutableListOf()

    override fun onViewCreated() {
        observeLiveData()
    }

    override fun initialiseViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentNiavlBinding.inflate(inflater, container, false)
    }

    override fun getRootView(): View? {
        return binding?.root
    }

    override fun initialiseView() {
        setupToolbar()
        initRecycler()
        initialiseSearchView()
    }

    private fun initialiseSearchView() {
        binding?.searchView?.setOnQueryTextListener(this)
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
        rvAdapter = NIAVLItemAdapter(uiModels, this)
        rvAdapter.setHasStableIds(true)
        binding?.rvList?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = rvAdapter
        }
    }

    private fun observeLiveData() {
        viewModel.models.observe(viewLifecycleOwner, {
            updateUI(it)
        })
    }

    private fun updateUI(models: List<NIAVLUiModel>) {
        binding?.apply {
            progressBar.visibility = View.GONE
            rvList.visibility = View.VISIBLE
        }
        this.uiModels.clear()
        this.uiModels.addAll(models)
        rvAdapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        requireActivity().hideKeyboard()
        query?.let {
            viewModel.searchNIAVL(it)
        }
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return false
    }

    override fun onNIAVLItemClick(item: NIAVLUiModel, position: Int) {
        if (item.mediaType == NIAVLDataEntry.MEDIA_TYPE_VIDEO) {
            val action = NIAVLFragmentDirections.actionNiavlDestToMediaPlayerDest(item.href)
            findNavController().navigate(action)
        }
        // TODO: If item type is audio also load it on player
        // TODO: If item type is image open screen to show images of the item
    }

}