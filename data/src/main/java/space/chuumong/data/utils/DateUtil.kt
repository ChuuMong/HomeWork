package space.chuumong.data.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    private const val DEFAULT_TIME_FORMAT = "HHmm"
    
    fun getCurrentTime(): String {
        val date = Calendar.getInstance().time
        return SimpleDateFormat("$DEFAULT_TIME_FORMAT", Locale.KOREA).format(date)
    }
}