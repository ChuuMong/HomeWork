package space.chuumong.homework.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import org.koin.android.viewmodel.ext.android.getViewModel
import space.chuumong.homework.R
import space.chuumong.homework.databinding.ActivityMainBinding
import space.chuumong.homework.ui.BaseActivity
import space.chuumong.homework.ui.github.GithubUserActivity
import space.chuumong.homework.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun getLayoutId() = R.layout.activity_main

    private val mainViewModel: MainViewModel by lazy { getViewModel() as MainViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainViewModel = mainViewModel

        mainViewModel.onClickSearchUser.observe(this, Observer {
            GithubUserActivity.start(this)
        })

        mainViewModel.onClickUI.observe(this, Observer {
        })
    }
}
