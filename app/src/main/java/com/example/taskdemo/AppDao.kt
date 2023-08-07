package com.example.taskdemo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskdemo.data.ApiResult



@Dao
interface AppDao {
    @Query("SELECT * FROM data_table")
    suspend fun getAllData(): List<ApiResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: List<ApiResult>)
}