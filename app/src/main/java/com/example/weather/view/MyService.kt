package com.example.weather.view
import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    private var mBinder : IBinder? = null

    override fun onCreate() {
        // Создание службы
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Служба стартовала
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        // Привязка клиента
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        // Удаление привязки
        return true
    }

    override fun onRebind(intent: Intent?) {
        // Перепривязка клиента
    }

    override fun onDestroy() {
        // Уничтожение службы
    }
}