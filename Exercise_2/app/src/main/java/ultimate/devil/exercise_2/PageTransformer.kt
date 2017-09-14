package ultimate.devil.exercise_2

import android.support.v4.view.ViewPager
import android.view.View
import android.support.v4.view.ViewCompat.setScaleY
import android.support.v4.view.ViewCompat.setAlpha



/**
 * Created by Vikas on 14-Sep-17.
 */
class PageTransformer : ViewPager.PageTransformer {

    private val MIN_SCALE = 0.85f
    private val MIN_ALPHA = 0.5f
    /**
     * Apply a property transformation to the given page.

     * @param page Apply the transformation to this page
     * *
     * @param position Position of page relative to the current front-and-center
     * *                 position of the pager. 0 is front and center. 1 is one full
     * *                 page position to the right, and -1 is one page position to the left.
     */
    override fun transformPage(page: View, position: Float) {

         if (position < -0.5) {
            // Values less than -1 means the page is out of the screen to the left
            // This alpha will be set to page visible on the left side
            page.alpha = 1.5f + position

            // Modify the default slide transition to shrink the page as well
            val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
            page.scaleY = scaleFactor

        } else if (position <= 0.5) {
            // Focused page
            page.alpha = 1f - position
            // Modify the default slide transition to shrink the page as well
            val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
            page.scaleY = scaleFactor

        } else {
            // values greater than 1 means page is out of the screen to the right
            // This alpha will be set to page visible on the right side
            page.alpha = 0.5f


            // Modify the default slide transition to shrink the page as well
            val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
            page.scaleY = scaleFactor
        }


    }
}