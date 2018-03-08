package com.example.kangning.wordrecordindevidual.adapter

import android.support.v4.view.PagerAdapter
import android.view.View

class TabPagerAdapter : PagerAdapter {

    private lateinit var labels: MutableList<String>

    constructor(labels: MutableList<String>) {
        this.labels = labels
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return labels.size
    }
}