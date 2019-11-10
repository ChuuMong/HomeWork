package space.chuumong.homework.ui.meeting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import org.koin.android.viewmodel.ext.android.getViewModel
import space.chuumong.data.Result
import space.chuumong.domain.entities.MeetingRoomInfo
import space.chuumong.homework.R
import space.chuumong.homework.databinding.ActivityMeetingRoomBinding
import space.chuumong.homework.ui.BaseActivity
import space.chuumong.homework.viewmodel.MeetingRoomViewModel

class MeetingRoomActivity : BaseActivity<ActivityMeetingRoomBinding>() {

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, MeetingRoomActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun getLayoutId() = R.layout.activity_meeting_room

    private val meetingRoomViewModel: MeetingRoomViewModel by lazy { getViewModel() as MeetingRoomViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getMeetingRoomInfo()
    }

    private fun getMeetingRoomInfo() {
        meetingRoomViewModel.getMeetingRoomInfo(object : Result<List<MeetingRoomInfo>> {
            override fun onSuccess(result: List<MeetingRoomInfo>) {

            }

            override fun onFail(t: Throwable) {
                Log.e("test", t.message, t)
            }
        })
    }
}