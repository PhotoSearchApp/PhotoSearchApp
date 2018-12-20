package com.hanmo.simplephotosearchapp

import android.util.Log
import com.hanmo.simplephotosearchapp.data.realm.RealmService
import com.hanmo.simplephotosearchapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration

class PhotoSearchApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val applicationComponent =
                DaggerAppComponent
                        .builder()
                        .application(this)
                        .build()

        applicationComponent.inject(this)

        return applicationComponent
    }


    override fun onCreate() {
        super.onCreate()

        initRealm()
    }

    private fun initRealm() {

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("photo_search.realm")
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)

        Realm.getDefaultInstance()

        checkRealmFirst()
    }

    private fun checkRealmFirst() {
        val pref = getSharedPreferences(resources.getString(R.string.createRealm), MODE_PRIVATE)
        val checkCreateRealm = pref.getBoolean(resources.getString(R.string.createRealm), false)
        if (!checkCreateRealm) {

            val editor = pref.edit()
            editor.putBoolean(resources.getString(R.string.createRealm), true)
            editor.apply()

            RealmService.createKeywordList()
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        if (!Realm.getDefaultInstance().isClosed) {
            Realm.getDefaultInstance().close()
        }
    }
}