package com.app.farm.farmapp

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.util.*

import kotlinx.android.synthetic.main.register.*

class RegisterActivity : AppCompatActivity() {

    companion object {
        val TAG = "RegisterActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
            register_button_register.setOnClickListener {
            val email =  email_edittext_register.text.toString()
            val password =  password_edittext_register.text.toString()

            Log.d("email",email)
            Log.d("pass",password)

        }

        selectphoto_button_register.setOnClickListener {
            Log.d(TAG, "Try to show photo selector")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        already_have_account_text_view.setOnClickListener {
            val intent = Intent(this,LoginActivity2::class.java)
            startActivity(intent)
        }
    }



var selectedPhotoUri: Uri? = null

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
        // proceed and check what the selected image was....
       // Log.d(TAG, "Photo was selected")

        selectedPhotoUri = data.data

        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

        selectphoto_imageview_register.setImageBitmap(bitmap)

        selectphoto_button_register.alpha = 0f

//      val bitmapDrawable = BitmapDrawable(bitmap)
//      selectphoto_button_register.setBackgroundDrawable(bitmapDrawable)
    }
}


}