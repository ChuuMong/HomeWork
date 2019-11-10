package space.chuumong.homework.ui.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ReservationMeetingRoomDecoration : RecyclerView.ItemDecoration() {

    companion object {
        private const val FIRST_ITEM_LEFT_SIZE = 15
        private const val ITEM_RIGHT_SIZE = 5
        private const val LAST_ITEM_RIGHT_SIZE = 15
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        outRect.right += ITEM_RIGHT_SIZE.toPx()

        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            outRect.left = FIRST_ITEM_LEFT_SIZE.toPx()
        }

        if (position == state.itemCount - 1) {
            outRect.right = LAST_ITEM_RIGHT_SIZE.toPx()
        }
    }
}