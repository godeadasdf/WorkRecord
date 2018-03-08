package com.example.kangning.wordrecordindevidual.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kangning.wordrecordindevidual.model.RecordDriverItem


class RecordDriverAdapter : BaseQuickAdapter<RecordDriverItem, BaseViewHolder> {

    constructor(layoutResId: Int, data: MutableList<RecordDriverItem>?) : super(layoutResId, data)

    override fun convert(helper: BaseViewHolder?, item: RecordDriverItem?) {
    }
}

class RecordRoadAdapter : BaseQuickAdapter<RecordDriverItem, BaseViewHolder> {

    constructor(layoutResId: Int, data: MutableList<RecordDriverItem>?) : super(layoutResId, data)

    override fun convert(helper: BaseViewHolder?, item: RecordDriverItem?) {
    }
}