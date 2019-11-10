package space.chuumong.homework.ui.github

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.functions.Consumer
import org.koin.android.viewmodel.ext.android.getViewModel
import space.chuumong.data.Result
import space.chuumong.domain.entities.GithubUser
import space.chuumong.domain.usecase.UseCase
import space.chuumong.homework.R
import space.chuumong.homework.databinding.FragmentLikeUserBinding
import space.chuumong.homework.event.LikeUserEvent
import space.chuumong.homework.event.UnlikeUserEvent
import space.chuumong.homework.ui.BaseFragment
import space.chuumong.homework.ui.adapter.GithubUserAdapter
import space.chuumong.homework.ui.view.showNoTitleTwoButtonsDialog
import space.chuumong.homework.ui.view.toast
import space.chuumong.homework.utils.RxBus
import space.chuumong.homework.viewmodel.LikeUserViewModel

class LikeUserFragment : BaseFragment<FragmentLikeUserBinding>() {

    companion object {
        const val TAG = "LikeUserFragment"
    }

    override fun getLayoutId() = R.layout.fragment_like_user

    private val likeUserViewModel: LikeUserViewModel by lazy { getViewModel() as LikeUserViewModel }

    private val githubUserAdapter by lazy {
        GithubUserAdapter { user, _ ->
            deleteLikeUser(user)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.likeUserViewModel = likeUserViewModel

        binding.rvUser.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvUser.adapter = githubUserAdapter

        getAllLikeUsers()

        RxBus.register(this@LikeUserFragment, LikeUserEvent::class.java, Consumer {
            val user = it.user
            if (user.isLike) {
                githubUserAdapter.add(user)
            } else {
                githubUserAdapter.delete(user)
            }
        })
    }

    private fun getAllLikeUsers() {
        likeUserViewModel.getAllLikeUsers(object : Result<List<GithubUser>> {
            override fun onSuccess(result: List<GithubUser>) {
                githubUserAdapter.addAll(result)
            }

            override fun onFail(t: Throwable) {
                Log.e(TAG, t.message, t)

                showNoTitleTwoButtonsDialog(
                    getString(R.string.network_error_retry),
                    getString(R.string.retry),
                    { getAllLikeUsers() },
                    getString(android.R.string.cancel),
                    { requireActivity().finish() }
                )
            }
        })
    }

    private fun deleteLikeUser(user: GithubUser) {
        likeUserViewModel.deleteLikeUser(user, object : Result<UseCase.None> {
            override fun onSuccess(result: UseCase.None) {
                user.isLike = false
                githubUserAdapter.delete(user)

                RxBus.post(UnlikeUserEvent(user))
            }

            override fun onFail(t: Throwable) {
                toast(getString(R.string.delete_like_user_error))
            }
        })
    }

    override fun onDestroyView() {
        RxBus.unregister(this@LikeUserFragment)
        
        super.onDestroyView()
    }
}