package com.example.kangning.wordrecordindevidual.adapter

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.kangning.wordrecordindevidual.R
import android.widget.TextView
import android.graphics.Typeface
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class WordRecordView : FrameLayout {

    private lateinit var inflater: LayoutInflater

    private lateinit var wholePage: LinearLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var pager: ViewPager

    private lateinit var driverList: RecyclerView
    private lateinit var roadList: RecyclerView
    private var driverAdapter = RecordDriverAdapter(0, Collections.emptyList())
    private var roadAdapter = RecordRoadAdapter(0, Collections.emptyList())
    private lateinit var pagerAdapter: TabPagerAdapter

    private lateinit var driverRecord: RecyclerView
    private lateinit var roadRecord: RecyclerView

    private lateinit var tabList: MutableList<String>
    private lateinit var views: MutableList<View>


    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    private fun initView(context: Context) {
        inflater = LayoutInflater.from(context);
        wholePage = inflater.inflate(R.layout.workrecord_layout_wholepage, null, false) as LinearLayout
        initTab()
        initPager()
        this.addView(wholePage)
    }

    private fun initTab() {
        tabLayout = wholePage.findViewById(R.id.tab)
        tabLayout.post({ TabUtil.setIndicator(tabLayout, 35, 35) })
        tabList = ArrayList()
        tabList.add("维修仓运维")
        tabList.add("外包司机")
        tabLayout.addTab(tabLayout.newTab().setText(tabList[0]))
        tabLayout.addTab(tabLayout.newTab().setText(tabList[1]))
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            if (tab != null) {
                tab.customView = getTabView(i)
            }
        }
        updateTabTextView(tabLayout.getTabAt(tabLayout.selectedTabPosition)!!, true)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                updateTabTextView(tab, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                updateTabTextView(tab, false)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }

        })
    }

    private fun getTabView(currentPosition: Int): View {
        val view = inflater.inflate(R.layout.workrecord_item_tabview, null)
        val textView = view.findViewById(R.id.tab_text) as TextView
        textView.text = (tabList[currentPosition])
        return view
    }


    private fun updateTabTextView(tab: TabLayout.Tab, isSelect: Boolean) {
        if (isSelect) {
            //选中加粗
            val tabSelect = tab.customView!!.findViewById(R.id.tab_text) as TextView
            tabSelect.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            tabSelect.text = tab.text
        } else {
            val tabUnSelect = tab.customView!!.findViewById(R.id.tab_text) as TextView
            tabUnSelect.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
            tabUnSelect.text = tab.text
        }
    }

    private fun initPager() {
        pager = wholePage.findViewById(R.id.pager)
        driverList = RecyclerView(context)
        roadList = RecyclerView(context)
        driverList.layoutManager = LinearLayoutManager(context)
        roadList.layoutManager = LinearLayoutManager(context)
        views = ArrayList()
        views.add(roadList)
        views.add(driverList)
        pagerAdapter = TabPagerAdapter(views)
        pager.adapter = pagerAdapter
    }

}