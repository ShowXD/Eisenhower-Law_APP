package com.example.schedule

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_matter_window.*
import java.util.*

class AddMatterWindow : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_add_matter_window)

//        val alpha = 100
//        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
//        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
//        colorAnimation.duration = 500
//        colorAnimation.addUpdateListener {
//                animator -> addMatter_background.setBackgroundColor(animator.animatedValue as Int)
//        }
//        colorAnimation.start()

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
        btn_add.setOnClickListener {

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
}