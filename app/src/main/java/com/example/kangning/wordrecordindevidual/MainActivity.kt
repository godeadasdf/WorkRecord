package com.example.kangning.wordrecordindevidual

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.engine.library.http.callback.RequestCallBack
import com.example.kangning.wordrecordindevidual.model.RecordDriverItem
import com.example.kangning.wordrecordindevidual.model.RecordRoadItem
import com.example.kangning.wordrecordindevidual.ui.WordRecordView
import java.util.*

class MainActivity : AppCompatActivity(), WordRecordView.DatePickerListener {

    private lateinit var wordRecordView: WordRecordView

    companion object {
        private val TAG = MainActivity::javaClass.name
    }


    override fun onDatePicked(date: Date, character: WordRecordView.Character) {
        Log.d(TAG, date.toString())
        loadData(date, character)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordRecordView = findViewById(R.id.main)
        wordRecordView.datePickerListener = this
        wordRecordView.initData()

    }

    private val driverCallback by lazy {
        object : RequestCallBack<List<RecordDriverItem>>() {
            override fun onSuccess(result: List<RecordDriverItem>?) {
                wordRecordView.setDriverData(result!!)
            }
        }
    }

    private val roadCallBack by lazy {
        object : RequestCallBack<List<RecordRoadItem>>() {
            override fun onSuccess(result: List<RecordRoadItem>?) {
                wordRecordView.setRoadData(result!!)
            }
        }
    }

    private fun loadData(date: Date, character: WordRecordView.Character) {
        Log.d(TAG, "loadDateFromNet" + date.toString() + character.name)
        //todo 接网络回调后 setDriverData setRoadData
        when (character) {
            WordRecordView.Character.DRIVER -> {

            }
            WordRecordView.Character.ROAD -> {

            }
        }
    }
}
