package space.chuumong.data.local.datasource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import space.chuumong.domain.entities.MeetingRoomInfo

private val MEETING_ROOM_JSON_FILE_PATH = "json/MUSINSA.json"

class MeetingRoomLocalDataSource(private val context: Context) {

    fun getMeetingRoomInfos(): Single<List<MeetingRoomInfo>> {
        return Single.create {
            try {
                val inputStream = context.assets.open(MEETING_ROOM_JSON_FILE_PATH)
                val jsonString = inputStream.bufferedReader().use { it.readText() }

                val infos: List<MeetingRoomInfo> =
                    Gson().fromJson(jsonString, object : TypeToken<List<MeetingRoomInfo>>() {}.type)

                it.onSuccess(infos)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }
}