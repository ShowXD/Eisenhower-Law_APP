package com.example.schedule

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_matter_window.*
import java.util.*

class AddMatterWindow : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_add_matter_window)

        // 設置日期按鈕文字
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        btn_date.text = setDateFormat(year, month, day)

        // 設置日期按鈕 DatePickerDialogue
        btn_date.setOnClickListener {
            DatePickerDialog(this, { _, year, month, day ->
                run {
                    btn_date.text = setDateFormat(year, month, day)
                }
            }, year, month, day).show()
        }

        // 設置時間按鈕文字
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        btn_time.text = setTimeFormat(hour, minute)

        // 設置時間按鈕
        btn_time.setOnClickListener {
            TimePickerDialog(this, { _, hour, minute ->
                run {
                    btn_time.text = setTimeFormat(hour, minute)
                }
            }, hour, minute, false).show()
        }

        // 設置取消按鈕
        btn_cancel.setOnClickListener {
            onBackPressed()
        }

        // 設置新增按鈕
        btn_add.setOnClickListener { view ->
            Snackbar.make(view, "成功新增 ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            onSave()
            onBackPressed()
        }
    }

    // 將取得的日期轉換成白話文
    private fun setDateFormat(year: Int, month: Int, day: Int): String {
        return "${year}年${month + 1}月${day}日 "
    }

    // 將取得的時間轉換成白話文
    private fun setTimeFormat(hour: Int, minute: Int): String {
        return if (hour < 12) {
            "上午${hour}:${minute}"
        } else {
            "下午${hour}:${minute}"
        }
    }

    // 創建儲存資料檔案
    private fun onSave() {
        // create file
        val pref = getPreferences(Context.MODE_PRIVATE)
        val editor = pref.edit()

        // save name
         editor.putString("Title", editText_title.text.toString())
        // save date
        editor.putString("Date", btn_date.text.toString())
        // save time
        editor.putString("Time", btn_time.text.toString())
        // save important type
        editor.putBoolean("Important", switch_important.isChecked)
        // save urgent
        editor.putBoolean("Urgent", switch_urgent.isChecked)
        // commit change
        editor.apply()
    }
}