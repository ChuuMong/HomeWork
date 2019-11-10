package space.chuumong.data.local.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import space.chuumong.data.utils.*
import space.chuumong.domain.entities.MeetingRoomInfo

private const val MEETING_ROOM_JSON_FILE_PATH = "json/MUSINSA.json"

class MeetingRoomLocalDataSource(private val context: Context) {

    fun getMeetingRoomInfos(): Single<List<MeetingRoomInfo>> {
        return Single.create {
            try {
                val inputStream = context.assets.open(MEETING_ROOM_JSON_FILE_PATH)
                val jsonString = inputStream.bufferedReader().use { reader -> reader.readText() }
                val infos: List<MeetingRoomInfo> =
                    Gson().fromJson(jsonString, object : TypeToken<List<MeetingRoomInfo>>() {}.type)

                val currentDate = DateUtil.getCurrentTime()
                var currentDateIndex = currentDate.getSplitTimeIndex(false)
                if (currentDateIndex != TIME_NON_INDEX && currentDateIndex != TIME_18_INDEX) {
                    currentDateIndex = currentDate.getSplitTimeIndex(true)

                    infos.forEach { info ->
                        var canReservation = true
                        info.reservations.forEach reservations@{ reservation ->
                            val startTimeIndex = reservation.startTime.getSplitTimeIndex(true)
                            val endTimeIndex = reservation.endTime.getSplitTimeIndex(true)

                            if (currentDateIndex in startTimeIndex until endTimeIndex) {
                                canReservation = false
                                return@reservations
                            }
                        }

                        info.canReservation = canReservation
                    }
                }

                it.onSuccess(infos)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }
}