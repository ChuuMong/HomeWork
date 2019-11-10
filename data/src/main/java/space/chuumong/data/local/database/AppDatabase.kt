package space.chuumong.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import space.chuumong.data.local.dao.LikeGithubUserDao
import space.chuumong.data.local.entities.LikeGithubUser

@Database(version = 1, entities = [LikeGithubUser::class])
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "app.db"

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            ).build()
    }

    abstract fun likeGithubUserDao(): LikeGithubUserDao
}