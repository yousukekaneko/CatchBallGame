package com.example.android.sample.catchballgame

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Display
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.floor

class MainActivity : AppCompatActivity() {

    //position
    var boxY : Float = 0.0f
    var point1X : Float = 0.0f
    var point1Y : Float = 0.0f
    var point2X : Float = 0.0f
    var point2Y : Float = 0.0f
    var blackX : Float = 0.0f
    var blackY : Float = 0.0f


    //size
    private var flameHeight : Int = 0
    private var boxSize : Int = 0

    //screen size
//    private val wm = windowManager
    private val size = Point()
//    var display = wm.defaultDisplay.getSize(size)

    private var screenWidth = size.x
    private var screenHeight = size.y

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

        //point1
        point1X -= 16
        if (point1X < 0) {
            point1X = screenWidth + 500.toFloat()
            point1Y = floor(Math.random() * (flameHeight - point1.height)).toFloat()
        }
        point1.x = point1X
        point1.y = point1Y

        //black
        blackX -= 16
        if (blackX < 0) {
            blackX = screenWidth + 10.toFloat()
            blackX = floor(Math.random() * (flameHeight - black.height)).toFloat()
        }
        black.x = blackX
        black.y = blackY

        //point2
        point2X -= 20
        if (point2X < 0) {
            point2X = screenWidth + 5000.toFloat()
            point2Y = floor(Math.random() * (flameHeight - point2.height)).toFloat()
        }
        point2.x = point2X
        point2.y = point2Y

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
