package com.example.cityweather.presentation.presenters

import com.example.cityweather.presentation.BaseView

open class BasePresenter<T : BaseView> {

    var view: T? = null

    fun attachView(view: T) {
        this.view = view

        onViewAttached()
    }

    open fun onViewAttached() {}

    fun destroy() {
        view = null
    }
}