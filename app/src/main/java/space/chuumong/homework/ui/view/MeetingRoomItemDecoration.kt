package space.chuumong.homework.ui.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Home on 2019-11-10.
 */
class MeetingRoomItemDecoration : RecyclerView.ItemDecoration() {

    companion object {
        private const val ITEM_BOTTOM_SIZE = 10
        private const val LAST_ITEM_BOTTOM_SIZE = 20
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.getChildAdapterPosition(view)) {
            state.itemCount - 1 -> outRect.bottom += LAST_ITEM_BOTTOM_SIZE.toPx()
            else -> outRect.bottom += ITEM_BOTTOM_SIZE.toPx()
        }
    }
}