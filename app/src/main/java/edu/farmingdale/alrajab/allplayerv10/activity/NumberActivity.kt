package edu.farmingdale.alrajab.allplayerv10.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import edu.farmingdale.alrajab.allplayerv10.MainActivity
import edu.farmingdale.alrajab.allplayerv10.R
import edu.farmingdale.alrajab.allplayerv10.databinding.ActivityMainBinding
import edu.farmingdale.alrajab.allplayerv10.databinding.ActivityNumberBinding

class NumberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNumberBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)

        binding = ActivityNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.button.setOnClickListener{
            if(binding.phoneNumber.text!!.isEmpty()){
                Toast.makeText(this, "please enter your phone number!!", Toast.LENGTH_SHORT).show()
            }
            else{
                var intent = Intent(this, OTPActivity::class.java)
                intent.putExtra("number", binding.phoneNumber.text!!)
                startActivity(intent)
            }

        }

    }
}