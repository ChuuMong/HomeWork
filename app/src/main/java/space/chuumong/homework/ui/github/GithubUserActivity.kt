package space.chuumong.homework.ui.github

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
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
    private val likeUserFragment = LikeUserFragment()

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
        val fragment = supportFragmentManager.fragments.find { it.isVisible } ?: return false

        when (item.itemId) {
            R.id.navigation_search -> showFragment(searchUserFragment, fragment)
            R.id.navigation_like -> {
                if (supportFragmentManager.fragments.find { it.tag == LikeUserFragment.TAG } == null) {
                    supportFragmentManager.beginTransaction().add(
                        R.id.fl_container,
                        likeUserFragment,
                        LikeUserFragment.TAG
                    ).commitAllowingStateLoss()
                }

                showFragment(likeUserFragment, fragment)
            }
        }

        return true
    }

    private fun showFragment(showFragment: Fragment, hideFragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .show(showFragment)
            .hide(hideFragment)
            .commitAllowingStateLoss()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it.isVisible && it.tag != SearchUserFragment.TAG) {
                binding.bvTab.selectedItemId = R.id.navigation_search
                return
            }
        }

        super.onBackPressed()
    }
}
