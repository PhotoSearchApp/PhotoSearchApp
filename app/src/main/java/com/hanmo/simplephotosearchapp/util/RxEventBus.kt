package com.hanmo.simplephotosearchapp.util

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


object RxEventBus {

    private val mBusSubject: PublishSubject<Any> = PublishSubject.create()

    fun post(event: Any) {
        mBusSubject.onNext(event)
    }

    fun <T> filteredObservable(eventClass: Class<T>): Observable<T> {
        return mBusSubject.ofType(eventClass)
    }
}