package space.chuumong.homework.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.flexbox.FlexboxLayout
import space.chuumong.data.utils.*
import space.chuumong.domain.entities.MeetingRoomInfo
import space.chuumong.homework.R


class MeetingRoomTimeView
@JvmOverloads
constructor(context: Context, attr: AttributeSet? = null) : RelativeLayout(context, attr) {

    private val timeLineViews = mutableListOf<View>()

    init {
        val view = View.inflate(context, R.layout.view_meeting_room_time, this)
        val flTimeLine = view.findViewById<FlexboxLayout>(R.id.fl_time_line)
        val flTime = view.findViewById<FlexboxLayout>(R.id.fl_time)

        for (i in START_TIME..END_TIME) {
            flTime.addView(TextView(context).apply {
                text = "$i"
                setTextColor(ContextCompat.getColor(context, R.color.brownish_grey))
                setTextSize(Dimension.SP, 12f)
            })
        }

        for (i in 0 until (END_TIME - START_TIME) * 2) {
            val timeLineView = View(context).apply {
                setBackgroundColor(ContextCompat.getColor(context, R.color.deep_sky_blue))
            }

            timeLineViews.add(timeLineView)
            flTimeLine.addView(timeLineView)
        }

        val clCurrentTime = view.findViewById<ConstraintLayout>(R.id.cl_current_time)

        flTimeLine.post {
            val currentDate = DateUtil.getCurrentTime().timeSplit()
            val timeLineViewWidth = timeLineViews[0].width
            var currentDateIndex = currentDate.first.getTimeIndex()
            if (currentDateIndex >= TIME_18_INDEX) {
                clCurrentTime.visibility = View.INVISIBLE
                disableTimeLineView(0, timeLineViews.size)

                return@post
            }

            if (currentDateIndex == TIME_NON_INDEX) {
                clCurrentTime.visibility = View.INVISIBLE
            }

            currentDateIndex += if (currentDate.second.isHalfTime()) 1 else 0
            disableTimeLineView(0, currentDateIndex)
            clCurrentTime.setStartMargin((timeLineViewWidth * currentDateIndex) - (clCurrentTime.width / 2))
        }
    }

    private fun disableTimeLineView(startIndex: Int, endIndex: Int) {
        for (i in startIndex until endIndex) {
            timeLineViews[i].setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.gray_02
                )
            )
        }
    }

    fun setMeetingRoomInfo(info: MeetingRoomInfo) {
        info.reservations.forEach {
            val startTime = it.startTime.timeSplit()
            val endTime = it.endTime.timeSplit()

            val startTimeIndex = startTime.first.getTimeIndex() +
                    if (startTime.second.isHalfTime()) 1 else 0
            val endTimeIndex = endTime.first.getTimeIndex() +
                    if (endTime.second.isHalfTime()) 1 else 0

            disableTimeLineView(startTimeIndex, endTimeIndex)
        }
    }
}