package space.chuumong.homework.ui.github

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.functions.Consumer
import org.koin.android.viewmodel.ext.android.getViewModel
import space.chuumong.data.Result
import space.chuumong.domain.entities.GithubSearchUser
import space.chuumong.domain.entities.GithubUser
import space.chuumong.domain.usecase.UseCase
import space.chuumong.homework.R
import space.chuumong.homework.databinding.FragmentSearchUserBinding
import space.chuumong.homework.event.LikeUserEvent
import space.chuumong.homework.event.UnlikeUserEvent
import space.chuumong.homework.ui.BaseFragment
import space.chuumong.homework.ui.adapter.GithubUserAdapter
import space.chuumong.homework.ui.view.LoadMoreScrollListener
import space.chuumong.homework.ui.view.showNoTitleTwoButtonsDialog
import space.chuumong.homework.ui.view.toast
import space.chuumong.homework.utils.RxBus
import space.chuumong.homework.utils.SoftKeyboardUtils
import space.chuumong.homework.viewmodel.LikeUserViewModel
import space.chuumong.homework.viewmodel.SearchUserViewModel

class SearchUserFragment : BaseFragment<FragmentSearchUserBinding>() {

    companion object {
        const val TAG = "SearchUserFragment"

        private const val START_SEARCH_PAGE = 2
    }

    override fun getLayoutId() = R.layout.fragment_search_user

    private val searchUserViewModel: SearchUserViewModel by lazy { getViewModel() as SearchUserViewModel }
    private val likeUserViewModel: LikeUserViewModel by lazy { getViewModel() as LikeUserViewModel }

    private val githubUserAdapter by lazy {
        GithubUserAdapter { user, position ->
            if (user.isLike) {
                deleteLikeUser(user, position)
            } else {
                saveLikeUser(user, position)
            }
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

        RxBus.register(this@SearchUserFragment, UnlikeUserEvent::class.java, Consumer {
            githubUserAdapter.updateItem(it.user)
        })
    }

    private fun searchUsers() {
        val name = binding.etUser.text.toString()
        if (name.isEmpty()) {
            binding.etUser.error = getString(R.string.search_user_empty_name)
            return
        }

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
                Log.e(TAG, t.message, t)
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
        if (name.isEmpty()) {
            binding.etUser.error = getString(R.string.search_user_empty_name)
            return
        }

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

    private fun saveLikeUser(user: GithubUser, position: Int) {
        likeUserViewModel.saveLikeUser(user, object : Result<UseCase.None> {
            override fun onSuccess(result: UseCase.None) {
                user.isLike = true
                githubUserAdapter.notifyItemChanged(position)
                RxBus.post(LikeUserEvent(user))
            }

            override fun onFail(t: Throwable) {
                toast(getString(R.string.save_like_user_error))
            }
        })
    }

    private fun deleteLikeUser(user: GithubUser, position: Int) {
        likeUserViewModel.deleteLikeUser(user, object : Result<UseCase.None> {
            override fun onSuccess(result: UseCase.None) {
                user.isLike = false
                githubUserAdapter.notifyItemChanged(position)
                RxBus.post(LikeUserEvent(user))
            }

            override fun onFail(t: Throwable) {
                toast(getString(R.string.delete_like_user_error))
            }
        })
    }

    private fun clearSearchEditFocus() {
        binding.etUser.clearFocus()
        SoftKeyboardUtils.hideKeyboard(binding.etUser)
    }

    override fun onDestroy() {
        RxBus.unregister(this@SearchUserFragment)

        super.onDestroy()
    }
}