package space.chuumong.homework.ui.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import space.chuumong.homework.R
import space.chuumong.homework.databinding.ActivityGithubUserBinding
import space.chuumong.homework.ui.BaseActivity

class GithubUserActivity : BaseActivity<ActivityGithubUserBinding>(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, GithubUserActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun getLayoutId() = R.layout.activity_github_user

    private val searchUserFragment = SearchUserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(
            R.id.fl_container,
            searchUserFragment,
            SearchUserFragment.TAG
        ).commitAllowingStateLoss()

        binding.bvTab.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.navigation_search -> transaction.replace(
                R.id.fl_container,
                searchUserFragment,
                SearchUserFragment.TAG
            ).commitAllowingStateLoss()
//            R.id.navigation_like ->
        }

        return true
    }
}
