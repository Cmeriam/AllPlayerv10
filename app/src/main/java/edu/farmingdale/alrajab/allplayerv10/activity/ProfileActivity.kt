package edu.farmingdale.alrajab.allplayerv10.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import edu.farmingdale.alrajab.allplayerv10.MainActivity
import edu.farmingdale.alrajab.allplayerv10.Model.UserModel
import edu.farmingdale.alrajab.allplayerv10.R
import edu.farmingdale.alrajab.allplayerv10.databinding.ActivityProfileBinding
import java.util.*

class ProfileActivity : AppCompatActivity() {


    private lateinit var binding : ActivityProfileBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var storage : FirebaseStorage
    private lateinit var selectImg : Uri
    private lateinit var dialog: AlertDialog.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dialog = AlertDialog.Builder(this,)
            .setMessage("updating Profile")
            .setCancelable(false)

        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()

        binding.userImage.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

        binding.continueBtn.setOnClickListener {
            if (binding.userName.text!!.isEmpty()) {
                Toast.makeText(this, "please enter your name", Toast.LENGTH_SHORT).show()
            } else if (selectImg == null) {
                Toast.makeText(this, "please select your image", Toast.LENGTH_SHORT).show()
            } else uploadData()
        }
    }
    private fun uploadData(){
        val reference = storage.reference.child("profile").child(Date().time.toString())
        reference.putFile(selectImg).addOnCompleteListener {
            if (it.isSuccessful)
                reference.downloadUrl.addOnSuccessListener { task ->
                    uploadInfo(task.toString())

                }

        }


    }
    private fun uploadInfo(imgUrl: String){
        val user = UserModel(auth.uid.toString(), binding.userName.text.toString(),auth.currentUser!!.phoneNumber.toString(),imgUrl)

        database.reference.child("users")
            .child(auth.uid.toString())
            .setValue(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))

            }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(data !=null){
        if(data.data != null){
            selectImg =data.data!!

            binding.userImage.setImageURI(selectImg)
        }

        }
    }


}