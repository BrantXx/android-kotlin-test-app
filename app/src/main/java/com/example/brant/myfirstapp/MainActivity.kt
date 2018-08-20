package com.example.brant.myfirstapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.textView
import kotlinx.android.synthetic.main.activity_main.increment
import kotlinx.android.synthetic.main.activity_main.decrement
import android.view.View.OnTouchListener


class MainActivity : AppCompatActivity() {
    val incrementHandler = Handler()
    val incrementRunnable = object: Runnable {
        override fun run() {
            incrementValue()
            incrementHandler.postDelayed(this, 100)
        }
    }
    val decrementHandler = Handler()
    val decrementRunnable = object: Runnable {
        override fun run() {
            decrementValue()
            decrementHandler.postDelayed(this, 100)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createListeners()
    }

    fun createListeners() {
        increment.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> startIncrement()
                    MotionEvent.ACTION_UP -> stopIncrement()
                }
                return view?.onTouchEvent(event) ?: true
            }
        })
        decrement.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> startDecrement()
                    MotionEvent.ACTION_UP -> stopDecrement()
                }
                return view?.onTouchEvent(event) ?: true
            }
        })
    }

    fun startIncrement() {
        incrementValue()
        incrementHandler.postDelayed(incrementRunnable, 300);
    }

    fun stopIncrement() {
        incrementHandler.removeCallbacks(incrementRunnable)
    }

    fun incrementValue() {
        val countString = textView.text.toString()
        var count: Int = Integer.parseInt(countString)
        count++
        textView.text = count.toString()
    }

    fun startDecrement() {
        decrementValue()
        decrementHandler.postDelayed(decrementRunnable, 300);
    }

    fun stopDecrement() {
        decrementHandler.removeCallbacks(decrementRunnable)
    }

    fun decrementValue() {
        val countString = textView.text.toString()
        var count: Int = Integer.parseInt(countString)
        if(count > 0) {
            count--
            textView.text = count.toString()
        } else {
            val alert = Toast.makeText(this, "Cannot go below 0!", Toast.LENGTH_LONG)
            alert.show()
        }
    }

    fun alertMe(view: View) {
        val alert = Toast.makeText(this, "Hello World!", Toast.LENGTH_LONG)
        alert.show()
    }

    fun randomMe(view: View) {
        val randomIntent = Intent(this, SecondActivity::class.java)
        val countString = textView.text.toString()
        var count: Int = Integer.parseInt(countString)
        randomIntent.putExtra(SecondActivity.TOTAL_COUNT, count)
        startActivity(randomIntent)
    }
}
