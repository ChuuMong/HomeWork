package space.chuumong.homework.ui.search

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.getViewModel
import space.chuumong.data.Result
import space.chuumong.domain.entities.GithubSearchUser
import space.chuumong.homework.R
import space.chuumong.homework.databinding.FragmentSearchUserBinding
import space.chuumong.homework.ui.BaseFragment
import space.chuumong.homework.ui.adapter.GithubUserAdapter
import space.chuumong.homework.ui.view.LoadMoreScrollListener
import space.chuumong.homework.ui.view.showNoTitleTwoButtonsDialog
import space.chuumong.homework.utils.SoftKeyboardUtils
import space.chuumong.homework.viewmodel.SearchUserViewModel

class SearchUserFragment : BaseFragment<FragmentSearchUserBinding>() {

    companion object {
        const val TAG = "SearchUserFragment"

        private const val START_SEARCH_PAGE = 2

        fun newInstance(): SearchUserFragment {
            return SearchUserFragment()
        }
    }

    override fun getLayoutId() = R.layout.fragment_search_user

    private val searchUserViewModel: SearchUserViewModel by lazy { getViewModel() as SearchUserViewModel }
    private val githubUserAdapter by lazy {
        GithubUserAdapter {

        }
    }

    private val loadMoreScrollListener = object : LoadMoreScrollListener() {
        override fun loadMore() {
            moreSearchUsers()
        }
    }

    private var searchPage = START_SEARCH_PAGE
    private var currentSearchItemCount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchUserViewModel = searchUserViewModel

        binding.etUser.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                clearSearchEditFocus()
                searchUsers()
                true
            } else {
                false
            }
        }

        binding.btnSearch.setOnClickListener {
            clearSearchEditFocus()
            searchUsers()
        }

        binding.rvUsers.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvUsers.adapter = githubUserAdapter
    }

    private fun searchUsers() {
        val name = binding.etUser.text.toString()
        searchUserViewModel.searchUsers(name, object : Result<GithubSearchUser> {
            override fun onSuccess(result: GithubSearchUser) {
                githubUserAdapter.addAll(result.users)

                searchPage = START_SEARCH_PAGE
                currentSearchItemCount = result.users.size

                if (currentSearchItemCount < result.totalCount) {
                    binding.rvUsers.addOnScrollListener(loadMoreScrollListener)
                }
            }

            override fun onFail(t: Throwable) {
                showNoTitleTwoButtonsDialog(
                    getString(R.string.network_error_retry),
                    getString(R.string.retry),
                    { searchUsers() },
                    getString(android.R.string.cancel),
                    { requireActivity().finish() }
                )
            }
        })
    }

    private fun moreSearchUsers() {
        val name = binding.etUser.text.toString()

        searchUserViewModel.moreSearchUsers(name, searchPage, object : Result<GithubSearchUser> {
            override fun onSuccess(result: GithubSearchUser) {
                githubUserAdapter.addMore(result.users)

                searchPage += 1
                currentSearchItemCount += result.users.size
                loadMoreScrollListener.isLoading = false

                if (currentSearchItemCount >= result.totalCount) {
                    binding.rvUsers.removeOnScrollListener(loadMoreScrollListener)
                }
            }

            override fun onFail(t: Throwable) {
                showNoTitleTwoButtonsDialog(
                    getString(R.string.network_error_retry),
                    getString(R.string.retry),
                    { searchUsers() },
                    getString(android.R.string.cancel),
                    { requireActivity().finish() }
                )
            }
        })
    }

    private fun clearSearchEditFocus() {
        binding.etUser.clearFocus()
        SoftKeyboardUtils.hideKeyboard(binding.etUser)
    }
}