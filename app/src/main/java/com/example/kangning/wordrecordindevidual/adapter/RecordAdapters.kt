package com.example.kangning.wordrecordindevidual.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kangning.wordrecordindevidual.R
import com.example.kangning.wordrecordindevidual.model.RecordDriverItem
import com.example.kangning.wordrecordindevidual.model.RecordRoadItem


class RecordDriverAdapter : BaseQuickAdapter<RecordDriverItem, BaseViewHolder> {

    constructor(layoutResId: Int, data: MutableList<RecordDriverItem>) : super(layoutResId, data)

    override fun convert(helper: BaseViewHolder, item: RecordDriverItem) {
        helper.setText(R.id.start, item.onWorkTime)
        helper.setText(R.id.end, item.offWorkTime)
        helper.setText(R.id.name, item.name)
        helper.setText(R.id.tel, item.telephone)
        helper.setText(R.id.recycleNum, item.bikeRecycle)
        helper.setText(R.id.distributeNum, item.bikeDistribute)
        helper.setText(R.id.inNum, item.bikeIn)
        helper.setText(R.id.outNum, item.bikeOut)
    }
}

class RecordRoadAdapter : BaseQuickAdapter<RecordRoadItem, BaseViewHolder> {

    constructor(layoutResId: Int, data: MutableList<RecordRoadItem>) : super(layoutResId, data)

    override fun convert(helper: BaseViewHolder, item: RecordRoadItem) {
        helper.setText(R.id.start, item.onWorkTime)
        helper.setText(R.id.end, item.offWorkTime)
        helper.setText(R.id.name, item.name)
        helper.setText(R.id.tel, item.telephone)
        helper.setText(R.id.searchNum, item.bikeSearch)
        helper.setText(R.id.distributeNum, item.bikeDistribute)
        helper.setText(R.id.clusterNum, item.bikeCluster)
    }
}