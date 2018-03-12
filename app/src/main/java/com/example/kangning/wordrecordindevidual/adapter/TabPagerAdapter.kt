package com.example.kangning.wordrecordindevidual.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

class TabPagerAdapter : PagerAdapter {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    private var views: MutableList<View>

    constructor(views: MutableList<View>) : super() {
        this.views = views
    }

    override fun getCount(): Int {
        return views.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(views[position])
        return views[position]
    }

}