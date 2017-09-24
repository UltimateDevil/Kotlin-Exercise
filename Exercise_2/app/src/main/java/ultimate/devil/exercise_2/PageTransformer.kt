package ultimate.devil.exercise_2

import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View



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

         if (position < -0) {
             page.alpha = 0.3f
             val pageWidth = page.width
             val pageHeight = page.height
            // Values less than -1 means the page is out of the screen to the left
             // This alpha will be set to page visible on the left side
//            page.alpha = 1.5f + position

            // Modify the default slide transition to shrink the page as well
             val scaleFactor = Math.max(MIN_SCALE, 1- Math.abs(position))
             page.scaleY = scaleFactor
             page.scaleX= scaleFactor
             Log.d("left","H : $pageHeight. W : $pageWidth")

        } else if (position <= 1.5) {

             // Focused page
//             page.alpha = position
//             Log.d("Position of focused : ", position.toString())
             // Modify the default slide transition to shrink the page as well
           /*  val scaleFactor = Math.max(MIN_SCALE, 2 - Math.abs(position)) - 0.5f
             page.scaleY = scaleFactor*/

             val pageWidth = page.width
             val pageHeight = page.height

             var scaleFactor = Math.max(MIN_SCALE, 2 - Math.abs(position))
             var vertMargin = pageHeight * (1 - scaleFactor) / 2
             var horzMargin = pageWidth * (1 - scaleFactor) / 2
             if (position < 0.5) {
                 scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))+0.3f
                 vertMargin = pageHeight * (1 - scaleFactor) / 2
                 horzMargin = pageWidth * (1 - scaleFactor) / 2
                 page.translationX=(horzMargin - vertMargin / 2)
                 Log.d("front","H : "+(horzMargin - vertMargin / 2))
             } else {
                 page.translationY=(-horzMargin + vertMargin / 2)
             }

             // Scale the page down (between MIN_SCALE and 1)
             page.scaleX= scaleFactor
             page.scaleY = scaleFactor
             // Fade the page relative to its size.
             page.alpha=  MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)


         } else {
             val pageWidth = page.width
             val pageHeight = page.height
             Log.d("right","H : $pageHeight. W : $pageWidth")
            // values greater than 1 means page is out of the screen to the right
            // This alpha will be set to page visible on the right side
            page.alpha = 0.3f


            // Modify the default slide transition to shrink the page as well
             val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))

            page.scaleY = scaleFactor
            page.scaleX = scaleFactor
        }
    }
}