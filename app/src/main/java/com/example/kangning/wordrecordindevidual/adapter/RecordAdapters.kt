package com.example.kangning.wordrecordindevidual.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kangning.wordrecordindevidual.R
import com.example.kangning.wordrecordindevidual.model.RecordDriverItem
import com.example.kangning.wordrecordindevidual.model.RecordRoadItem


class RecordDriverAdapter : BaseQuickAdapter<RecordDriverItem, BaseViewHolder> {

    constructor(layoutResId: Int, data: List<RecordDriverItem>) : super(layoutResId, data)

    override fun convert(helper: BaseViewHolder, item: RecordDriverItem) {
        helper.setText(R.id.start, item.onWorkTime)
        helper.setText(R.id.end, item.offWorkTime)
        helper.setText(R.id.name, item.name)
        helper.setText(R.id.tel, item.telephone)
        helper.setText(R.id.recycleNum, item.bikeRecycle.toString())
        helper.setText(R.id.distributeNum, item.bikeDistribute.toString())
        helper.setText(R.id.inNum, item.bikeIn.toString())
        helper.setText(R.id.outNum, item.bikeOut.toString())
    }
}

class RecordRoadAdapter : BaseQuickAdapter<RecordRoadItem, BaseViewHolder> {

    constructor(layoutResId: Int, data: List<RecordRoadItem>) : super(layoutResId, data)

    override fun convert(helper: BaseViewHolder, item: RecordRoadItem) {
        helper.setText(R.id.start, item.onWorkTime)
        helper.setText(R.id.end, item.offWorkTime)
        helper.setText(R.id.name, item.name)
        helper.setText(R.id.tel, item.telephone)
        helper.setText(R.id.searchNum, item.bikeSearch.toString())
        helper.setText(R.id.distributeNum, item.bikeDistribute.toString())
        helper.setText(R.id.clusterNum, item.bikeCluster.toString())
    }
}