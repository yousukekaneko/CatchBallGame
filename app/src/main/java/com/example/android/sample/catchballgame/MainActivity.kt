package com.example.android.sample.catchballgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    //position
    var boxY : Float = 0.0f

    //size
    var flameHeight : Int = 0
    var boxSize : Int = 0

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

    //Status flg
    private var actionFlg = false
    private var startFlg = false

    private fun changePos() {

        if (actionFlg) {
            //触っている時
            boxY -= 20
        } else {
            //離している時
            boxY += 20
        }

        //アイコンを外に出さないようにする処理
        if (boxY < 0.0f) boxY = 0.0f
        if (boxY > flameHeight - boxSize) boxY = flameHeight - boxSize.toFloat()

        lion.setY(boxY)
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

        boxY = 500.0f

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (startFlg == false) {
            startFlg = true

            flameHeight = frame.height

            boxY = lion.y
            boxSize = lion.height

            startLabel.visibility = View.GONE

            timer.schedule(timerTask, 0, 20)

        } else  {

            if (event?.action == MotionEvent.ACTION_DOWN) {
                actionFlg = true
            } else if (event?.action == MotionEvent.ACTION_UP){
                actionFlg = false
            }
        }

        return true
    }
}
