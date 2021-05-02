package com.example.guesstheweight

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class penalty : AppCompatActivity() {
    lateinit var button: Button
    lateinit var tv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penalty)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        button=findViewById(R.id.button)
        button.setOnClickListener { goBackToGame() }
        val stake_count= intent.getIntExtra(Game_Activity.EXTRA_IDS, -1)
        tv=findViewById(R.id.stakes)
        tv.setText("stakes count is:"+stake_count.toString())
    }
    fun goBackToGame(){
        finish()
    }
}