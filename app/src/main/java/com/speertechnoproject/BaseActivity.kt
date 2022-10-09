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
    fun groupDevices(inputs:List<String>): String{


        val map = hashMapOf<String,String>()

        for (data in inputs){
            val splitData = data.split(":")

            if(map.containsKey(splitData[1])){
                map[splitData[1]]= map[splitData[1]]+"," + splitData[0]

            } else {
                map[splitData[1]]= splitData[0]
            }
        }

        val builder = StringBuilder()
        for (key in map.keys){
            builder.appendLine("$key:${map[key]}")
        }
        return builder.toString()
    }

    abstract fun initComponents(savedInstanceState: Bundle?, binding: VDB)

}