package com.example.covid2019

import androidx.appcompat.widget.Toolbar

interface MainActivityDelegate {
    fun setupNavDrawer(toolbar: Toolbar)

    fun enableNavDrawer(enable: Boolean)
}