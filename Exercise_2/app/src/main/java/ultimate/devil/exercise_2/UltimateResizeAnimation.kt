package ultimate.devil.exercise_2

import android.view.animation.Transformation
import android.util.Log
import android.view.View
import android.view.animation.Animation


/**
 * Created by Vikas on 17-Sep-17.
 */
class UltimateResizeAnimation(private val view: View, private val targetWidth: Int, private val targetHeight: Int) : Animation() {




    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {

        val startWidth:Int = view.width
        val startHeight:Int = view.height

//        Log.d("Height ", "$startHeight , $startWidth,  $targetWidth")

//        val newWidth = (startWidth + (targetWidth - startWidth) * interpolatedTime) .toInt()
        val newHeight = (startHeight + (targetHeight - startHeight) * interpolatedTime) .toInt()
//        view.layoutParams.width = newWidth
        view.layoutParams.height = newHeight
        view.requestLayout()
    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
    }

    override fun willChangeBounds(): Boolean {
        return true
    }
}
