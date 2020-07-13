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
    var handler = Handler()
    var timer = Timer()

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


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_DOWN) {
            boyX -= 20
        }
        lion.setY(boyX)

        return true
    }
}
