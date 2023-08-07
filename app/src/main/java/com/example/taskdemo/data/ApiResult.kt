package com.example.taskdemo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class ApiResult(
    @PrimaryKey
    val id : Int,
    val userId : Int,
    val title :String,
    val body : String

)
