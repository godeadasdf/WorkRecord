package com.example.kangning.wordrecordindevidual.ui

import android.content.Context
import android.graphics.Typeface
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.kangning.wordrecordindevidual.R
import com.example.kangning.wordrecordindevidual.adapter.RecordDriverAdapter
import com.example.kangning.wordrecordindevidual.adapter.RecordRoadAdapter
import com.example.kangning.wordrecordindevidual.adapter.TabPagerAdapter
import com.example.kangning.wordrecordindevidual.model.RecordDriverItem
import com.example.kangning.wordrecordindevidual.model.RecordRoadItem
import kotlinx.android.synthetic.main.workrecord_layout_wholepage.view.*
import java.util.*


class WordRecordView : FrameLayout {

    private lateinit var inflater: LayoutInflater

    private lateinit var wholePage: RelativeLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var pager: NoScrollViewPager
    private lateinit var pagerAdapter: TabPagerAdapter

    private lateinit var driverList: RecyclerView
    private lateinit var roadList: RecyclerView
    private lateinit var driverAdapter: RecordDriverAdapter
    private lateinit var roadAdapter: RecordRoadAdapter

    private lateinit var tabList: MutableList<String>
    private lateinit var views: MutableList<View>

    private lateinit var dateSelected: Date
    //出勤人数
    private var roadNum = 0
    private var driverNum = 0

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    private fun initView(context: Context) {
        inflater = LayoutInflater.from(context);
        wholePage = inflater.inflate(R.layout.workrecord_layout_wholepage, null, false) as RelativeLayout
        val button = wholePage.findViewById(R.id.timeChoose) as Button
        button.setOnClickListener({
            showDatePicker()
        })
        dateSelected = Date()
        initTab()
        initPager()
        this.addView(wholePage)
    }

    fun initData() {
        datePickerListener?.onDatePicked(dateSelected, if (pager.currentItem === 0) Character.ROAD else Character.DRIVER)
    }

    interface DatePickerListener {
        fun onDatePicked(date: Date, character: Character)  //1 代表路面
    }

    var datePickerListener: DatePickerListener? = null

    private fun showDatePicker() {
        //todo 选择时间逻辑 暂且置为做出了选择
        datePickerListener?.onDatePicked(dateSelected, if (pager.currentItem === 0) Character.ROAD else Character.DRIVER)
    }

    private fun initTab() {
        tabLayout = wholePage.findViewById(R.id.tab)
        tabLayout.post({ TabUtil.setIndicator(tabLayout, 35, 35) })
        tabList = ArrayList()
        tabList.add("维修仓运维${roadNum}人")
        tabList.add("外包司机${driverNum}人")
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
                pager.currentItem = tab.position
                datePickerListener?.onDatePicked(dateSelected, if (pager.currentItem === 0) Character.ROAD else Character.DRIVER)
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
        pager.setScroll(false)
        driverList = RecyclerView(context)
        roadList = RecyclerView(context)
        driverList.layoutManager = LinearLayoutManager(context)
        roadList.layoutManager = LinearLayoutManager(context)

        //todo add real data
        driverAdapter = RecordDriverAdapter(R.layout.workrecord_list_item_driver, listOf(
                RecordDriverItem("小明", "12322121111", "09:00:00", "18:00:00",
                        250, 500, 120, 300)
        ))
        roadAdapter = RecordRoadAdapter(R.layout.workrecord_list_item_road, listOf(
                RecordRoadItem("小亮", "12321233211", "09:01:12", "19:01:12",
                        120, 234, 110)
        ))
        //--------------------------------------------------------------------------------------

        driverList.adapter = driverAdapter
        roadList.adapter = roadAdapter

        views = ArrayList()
        views.add(roadList)
        views.add(driverList)
        pagerAdapter = TabPagerAdapter(views)
        pager.adapter = pagerAdapter
    }

    fun setDriverData(list: List<RecordDriverItem>) {
        driverAdapter.setNewData(list)
        tabLayout.getTabAt(1)?.text = "外包司机${list.size}人"
    }

    fun setRoadData(list: List<RecordRoadItem>) {
        roadAdapter.setNewData(list)
        tabLayout.getTabAt(0)?.text = "维修仓运维${list.size}人"
    }

    enum class Character(val category: Int) {
        ROAD(0x00001),
        DRIVER(0x00002)
    }

}