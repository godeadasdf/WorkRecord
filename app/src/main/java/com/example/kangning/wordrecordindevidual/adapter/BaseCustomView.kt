package com.example.kangning.wordrecordindevidual.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout

abstract class BaseCustomView : FrameLayout {

    private lateinit var inflater: LayoutInflater
    lateinit var rootView: LinearLayout

    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    fun initView() {
        inflater = LayoutInflater.from(context)
        rootView = inflater.inflate(getLayoutId(), null, false) as LinearLayout
        this.addView(rootView)
    }

    abstract fun getLayoutId(): Int

}