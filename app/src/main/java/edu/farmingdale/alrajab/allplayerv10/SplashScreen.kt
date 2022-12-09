package edu.farmingdale.alrajab.allplayerv10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        MoveTo2()
    }
    private fun MoveTo2(){
        val someDelay = Thread{
            Thread.sleep(3000)
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        someDelay.start()
    }
}