package com.example.taskdemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.taskdemo.Api
import com.example.taskdemo.AppDatabase
import com.example.taskdemo.R
import com.example.taskdemo.RetrofitHelper
import com.example.taskdemo.databinding.ActivityDashboardBinding
import com.example.taskdemo.fragment.HomeFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)


        // Open Accessibility settings page to enable the accessibility service
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        startActivity(intent)

        val apiResult = RetrofitHelper.getInstance().create(Api::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = apiResult.getApiResult()
            if (result != null)
            // Checking the results
                result.body()
                    ?.let { AppDatabase.getDatabase(this@DashboardActivity).dataDao().insertData(it) }
                Log.e("vibhu", result.body().toString())

        }

        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.relativeLayout, homeFragment)
            .commit()

    }

}