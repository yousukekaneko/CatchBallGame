package com.example.android.sample.catchballgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var boyX : Float = 0.0f

    //Handler Timer
    val handler = Handler()
    private val timer = Timer()
    private val timerTask = object : TimerTask() {
        override fun run() {
            handler.post {
                this.run {
                    changePos()
                }
            }
        }
    }

    //Status
    private var actionFlg = false
    private var startFlg = false

    private fun changePos() {

        if (actionFlg) {
            //触っている時
            boyX -= 20
        } else {
            //離している時
            boyX += 20
        }
        lion.setY(boyX)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        point1.setX(-80.0f)
        point1.setY(-80.0f)
        point2.setX(-80.0f)
        point2.setY(-80.0f)
        black.setX(-80.0f)
        black.setY(-80.0f)

        startLabel.visibility = View.INVISIBLE
        boyX = 500.0f

        timer.schedule(timerTask, 0, 20)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_DOWN) {
            actionFlg = true
        } else if (event?.action == MotionEvent.ACTION_UP){
            actionFlg = false
        }
        return true
    }
}
