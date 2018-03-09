package com.example.kangning.wordrecordindevidual.model

data class RecordDriverItem(val name: String,
                            val telephone: String,
                            val onWorkTime: String,
                            val offWorkTime: String,
                            val bikeRecycle: Int,
                            val bikeDistribute: Int,
                            val bikeIn: Int,
                            val bikeOut: Int)

data class RecordRoadItem(val name: String,
                          val telephone: String,
                          val onWorkTime: String,
                          val offWorkTime: String,
                          val bikeSearch: Int,
                          val bikeDistribute: Int,
                          val bikeCluster: Int)