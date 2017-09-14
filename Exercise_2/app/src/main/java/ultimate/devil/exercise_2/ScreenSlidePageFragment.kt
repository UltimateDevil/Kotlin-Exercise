package ultimate.devil.exercise_2

import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat.setScaleY
import android.os.Bundle
import android.graphics.Color.parseColor
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View


/**
 * Created by Vikas on 14-Sep-17.
 */
class ScreenSlidePageFragment : Fragment() {

    var rootView: View?=null


    override fun onCreateView(inflater: LayoutInflater?,  container: ViewGroup?,  savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_screen_slide_page, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val tv_fragTitle = view.findViewById(R.id.tv_fragTitle) as TextView
        val tv_fragTitle = view!!.findViewById<TextView>(R.id.tv_fragTitle)

        val b = arguments
        if (b != null) {
            tv_fragTitle.text = b.getString("text")
    }
   /* fun onViewCreated(view: View,  savedInstanceState: Bundle) {
        super.onViewCreated(view, savedInstanceState)

        val tv_fragTitle = view.findViewById(R.id.tv_fragTitle) as TextView

        tv_fragTitle.setBackgroundColor(Color.parseColor(getRandomColor()))

        val b = arguments
        if (b != null) {
            tv_fragTitle.text = b.getString("text")
        }*/
    }

    /**
     * Gets the random color.

     * @return the random color
     */


    fun scaleImage(scaleX: Float) {
        rootView!!.setScaleY(scaleX)
        rootView!!.invalidate()
    }

    fun removeSpacing() {
        rootView!!.setPadding(0, 0, 0, 0)
        rootView!!.invalidate()
    }
}