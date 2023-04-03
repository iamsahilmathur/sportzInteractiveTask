package com.sportzInteractive.task.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sportzInteractive.task.BR
import com.sportzInteractive.task.R
import com.sportzInteractive.task.databinding.FragmentMatchBinding
import com.sportzInteractive.task.extentions.manageLoading
import com.sportzInteractive.task.model.response.MatchInfoData
import com.sportzInteractive.task.ui.adapter.MatchInfoAdapter
import com.sportzInteractive.task.ui.base.BaseFragment
import com.sportzInteractive.task.ui.viewModel.MatchInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchFragment : BaseFragment<FragmentMatchBinding, MatchInfoViewModel>() {
    private lateinit var matchInfoAdapter: MatchInfoAdapter
    private val mViewModel: MatchInfoViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBinding().homeVM = mViewModel
        setupViews()
        setupObservers()

    }

    override fun getLayoutId() = R.layout.fragment_match

    override fun getViewModel() = mViewModel

    override fun getBindingVariable() = BR.homeVM

    override fun observeViewModel() {
        mViewModel.manageLoading(
            requireActivity(), this, mViewModel.delegateMatchInfo,
            mViewModel.errorLiveData
        ).getMatchInfo()
    }

    private fun setupViews() {
        matchInfoAdapter =
            MatchInfoAdapter(requireContext(), object : MatchInfoAdapter.OnItemClickedListener {
                override fun itemClicked(matchInfoData: MatchInfoData) {
                    val action = MatchFragmentDirections.actionMatchFragmentToPlayersFragment(matchInfoData)
                    findNavController().navigate(action)
                }
            })
        getBinding().recyclerViewMatchList.apply {
            adapter = matchInfoAdapter
        }
    }

    private fun setupObservers() {
        mViewModel.delegateMatchInfo.observe(requireActivity()) {
            matchInfoAdapter.updateData(it as ArrayList<MatchInfoData>?)
        }
    }
}