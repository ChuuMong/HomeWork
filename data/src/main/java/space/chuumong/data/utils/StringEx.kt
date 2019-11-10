package space.chuumong.data.utils

fun String.timeSplit(): Pair<String, String> {
    val hour = this.substring(0, 2)
    val min = this.substring(2)

    return Pair(hour, min)
}

const val START_TIME = 9
const val END_TIME = 18

const val TIME_NON_INDEX = -1
const val TIME_SKIP_INDEX = 2
const val TIME_18_INDEX = 18

fun String.getTimeIndex(): Int {
    try {
        val time = this.toInt()
        when {
            time < START_TIME -> return TIME_NON_INDEX
            time >= END_TIME -> return TIME_18_INDEX
            else -> {
                for (i in 0 until (END_TIME - START_TIME)) {
                    if (i + START_TIME == time) {
                        return i * TIME_SKIP_INDEX
                    }
                }

                return TIME_NON_INDEX
            }
        }
    } catch (e: Exception) {
        return TIME_NON_INDEX
    }
}

fun String.isHalfTime(): Boolean {
    return try {
        val time = this.toInt()
        time >= 30
    } catch (e: Exception) {
        false
    }
}

fun String.getSplitTimeIndex(isAddHalfTime: Boolean): Int {
    val timeSplit = timeSplit()
    var index = timeSplit.first.getTimeIndex()
    if (isAddHalfTime && timeSplit.second.isHalfTime()) {
        index += 1
    }

    return index
}