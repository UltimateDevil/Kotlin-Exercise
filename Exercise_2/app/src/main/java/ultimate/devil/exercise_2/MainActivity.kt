package ultimate.devil.exercise_2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import android.util.SparseArray
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import android.view.View
import android.os.Build
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.animation.AnimationUtils


class MainActivity : AppCompatActivity() {

    var NUM_PAGES = 10
    var loaderHeight: Int = 0
    var loaderWidth: Int = 0
    var changingText:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gettingHeight()

        val pager: ViewPager = viewPager
        pager.pageMargin = 16
        pager.setPadding(208, 8, 200, 8)
        pager.offscreenPageLimit = 3
        pager.clipToPadding = false
        pager.setPageTransformer(true, PageTransformer())
        pager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)

        pager.currentItem= (1)

        clickButton.setOnClickListener {
            startLoading(pager)
        }

    }

    private fun gettingHeight() {
        val vto = mainLayout.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    mainLayout.viewTreeObserver.removeGlobalOnLayoutListener(this)
                } else {
                    mainLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
                loaderWidth = mainLayout.measuredWidth
                loaderHeight = mainLayout.measuredHeight

                Log.d("Height  -----", "$loaderWidth , $loaderHeight")

            }
        })
    }

    private fun startLoading(page:ViewPager) {

        val currentPage = (page.currentItem)+1
        changingText = currentPage*10

        Log.d("Text ", changingText.toString())

        loader.visibility = View.VISIBLE
        viewPager.animate().alpha(0F)
        viewPager.visibility = View.GONE
        clickButton.visibility = View.GONE
        loader.layoutParams.height = loaderHeight
        loader.layoutParams.width = loaderWidth
        timeStamp.visibility = View.VISIBLE
        timeStamp.animate().alpha(1F)
        timeStamp.text = (currentPage*10).toString()
        startTextLoading(currentPage)


        val handler = Handler()


            handler.postDelayed({
                val loadingAnimation = UltimateResizeAnimation(loader, 0, 0)
                loadingAnimation.duration = ((currentPage*1000)+2000).toLong()
                mainLayout.startAnimation(loadingAnimation)
            }, 300L)


            val handler2 = Handler()
            handler2.postDelayed({

                loader.visibility = View.GONE
                timeStamp.animate().alpha(0F)
//                timeStamp.visibility = View.GONE
                clickButton.visibility = View.VISIBLE
                val myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce2)

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                val interpolator = BounceAnimator(0.5, 20.0)
                myAnim.interpolator = interpolator
                myAnim.duration = 1000
                clickButton.startAnimation(myAnim)

                viewPager.visibility = View.VISIBLE
                viewPager.animate().alpha(1F)

            }, (currentPage*500).toLong())
    }

    private fun startTextLoading(time:Int) {
        Log.d("Text in Method ", changingText.toString())
        Handler().postDelayed({
            changingText--
            timeStamp.text = (changingText).toString()
            if (changingText>0){
                startTextLoading(time)
            }
        },(((time*1000)+2000)/(time*45)).toLong())


    }

    private inner class ScreenSlidePagerAdapter internal constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        internal var registeredFragments = SparseArray<Fragment>()

        override fun getItem(position: Int): Fragment {
            val frag = ScreenSlidePageFragment()
            val data = Bundle()
            data.putString("text", ((position + 1) * 10).toString())
            frag.arguments = data
            registeredFragments.put(position, frag)
            return frag
        }

        override fun getCount(): Int {
            return NUM_PAGES
        }
    }


    private inner class BounceAnimator internal constructor(amplitude: Double, frequency: Double) : android.view.animation.Interpolator {
        private var mAmplitude = 1.0
        private var mFrequency = 10.0

        init {
            this.mAmplitude = amplitude
            this.mFrequency = frequency
        }


        override fun getInterpolation(time: Float): Float {
            return (-1.0 * Math.pow(Math.E, -time / mAmplitude) *
                    Math.cos(mFrequency * time) + 1).toFloat()
        }
    }
}
