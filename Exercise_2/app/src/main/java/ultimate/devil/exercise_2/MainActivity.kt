package ultimate.devil.exercise_2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import android.util.SparseArray
import android.support.v4.app.FragmentStatePagerAdapter



class MainActivity : AppCompatActivity() {

    var NUM_PAGES =10



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pager:ViewPager = viewPager
        pager.pageMargin = 24
        pager.setPadding(48,8,48,8)
        pager.offscreenPageLimit = 3
        pager.clipToPadding = false
        pager.setPageTransformer(true,PageTransformer())
        pager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)

    }

    private inner class ScreenSlidePagerAdapter internal constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        internal var registeredFragments = SparseArray<Fragment>()

        override fun getItem(position: Int): Fragment {
            val frag = ScreenSlidePageFragment()
            val data = Bundle()
            data.putString("text", (position + 1).toString())
            frag.arguments=data
            registeredFragments.put(position, frag)
            return frag
        }

        override fun getCount(): Int {
            return NUM_PAGES
        }
    }
}
