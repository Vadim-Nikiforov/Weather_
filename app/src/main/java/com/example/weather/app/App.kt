package com.example.weather.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.weather.room.HistoryDao
import com.example.weather.room.HistoryDataBase
import java.lang.IllegalStateException

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
        private var appInstance: App? = null
        private var db: HistoryDataBase? = null
        private const val DB_NAME = "History.db"

        fun getHistoryDao(): HistoryDao {
            if (db == null) {
                synchronized(HistoryDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            HistoryDataBase::class.java,
                            DB_NAME)
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.historyDao()
        }
    }
}
interface IContextProvider{
    val context: Context
}

object ContextProvider: IContextProvider{
    override val context: Context
        get() = App.context
}