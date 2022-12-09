package edu.farmingdale.alrajab.allplayerv10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import edu.farmingdale.alrajab.allplayerv10.adapter.ViewPagerAdapter
import edu.farmingdale.alrajab.allplayerv10.UI.CallFragment
import edu.farmingdale.alrajab.allplayerv10.UI.ChatFragment
import edu.farmingdale.alrajab.allplayerv10.UI.PostsFragment
import edu.farmingdale.alrajab.allplayerv10.UI.StatusFragment
import edu.farmingdale.alrajab.allplayerv10.activity.NumberActivity
import edu.farmingdale.alrajab.allplayerv10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val fragmentArrayList = ArrayList<Fragment>()

        fragmentArrayList.add(ChatFragment())
        fragmentArrayList.add(StatusFragment())
        fragmentArrayList.add(CallFragment())
        fragmentArrayList.add(PostsFragment())

        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            startActivity(Intent(this, NumberActivity::class.java))
            finish()
        }

        val adapter = ViewPagerAdapter(this, supportFragmentManager, fragmentArrayList)
        binding!!.viewPager.adapter = adapter

        binding!!.tabs.setupWithViewPager(binding!!.viewPager)

    }
}