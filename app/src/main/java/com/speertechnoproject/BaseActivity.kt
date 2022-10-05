package com.speertechnoproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity  <VDB : ViewDataBinding> constructor(
@LayoutRes
private val layoutResId: Int) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<VDB>(this, layoutResId).run {
            this.lifecycleOwner = this@BaseActivity
            initComponents(savedInstanceState, this)
        }


    }

    abstract fun initComponents(savedInstanceState: Bundle?, binding: VDB)

}