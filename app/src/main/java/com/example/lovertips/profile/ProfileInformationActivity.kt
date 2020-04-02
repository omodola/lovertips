package com.example.lovertips.profile

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.lovertips.R
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileInformationActivity : AppCompatActivity() {
    private val IMAGE_CAPTURE_CODE: Int = 1001
    private val PERMISSION_CODE: Int = 1000
    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_personal_info)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_personal_info)
        setSupportActionBar(toolbar)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        }


      //  val photo = findViewById<ImageButton>(R.id.profile_image)

        profile_image.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                    || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)

                {
                    //permission was not enabled
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

                    requestPermissions(permissions, PERMISSION_CODE)

                }else{
                    //permission already granted
                    openCamera()

                }
            }else{
                openCamera()
            }
        }
        val editGender: EditText = findViewById<EditText>(R.id.gender)
        editGender.setFocusable(false);
        editGender.setClickable(true)
        /*editGender.setOnTouchListener(v : View, m:Motiion) {

        })*/


        /*editGender.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus){
                val listItems = arrayOf("Male", "Female", "Other")
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Gender")
                alertDialog.setSingleChoiceItems(listItems, -1) {
                        dialogInterface, i ->
                    editGender.setText(listItems[i])
                    dialogInterface.dismiss()
                }
                // Set the neutral/cancel button click listener
                /*alertDialog.setNeutralButton("Cancel") { dialog, which ->
                    // Do something when click the neutral button
                    dialog.cancel()
                }*/

                val mDialog = alertDialog.create()
                mDialog.show()

            }
        }*/

        editGender.setOnClickListener{
            val listItems = arrayOf("Male", "Female", "Other")
            val textView = TextView(this)
            textView.text = "Gender"
            textView.setPadding(20, 30, 20, 30)
            textView.textSize = 20f
            textView.setBackgroundColor(Color.DKGRAY)
            textView.setTextColor(Color.WHITE)
            val alertDialog = AlertDialog.Builder(this)
            //alertDialog.setTitle("Gender")
            alertDialog.setCustomTitle(textView)
            alertDialog.setSingleChoiceItems(listItems, -1) {
                    dialogInterface, i ->
                editGender.setText(listItems[i])
                dialogInterface.dismiss()
            }

            // Set the neutral/cancel button click listener
            alertDialog.setNeutralButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
            val mDialog = alertDialog.create()
            mDialog.show()
        }

    }

    private fun openCamera() {

        val listItems = arrayOf("Take Photo", "Choose from gallery")
        val textView = TextView(this)
        textView.text = "Choose your profile photo"
        textView.setPadding(20, 30, 20, 30)
        textView.textSize = 20f
        textView.setBackgroundColor(Color.DKGRAY)
        textView.setTextColor(Color.WHITE)
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setCustomTitle(textView)

        alertDialog.setSingleChoiceItems(listItems, -1) {
                dialogInterface, i ->
            if (listItems[i].equals("Take Photo")) {
                val values = ContentValues()
                values.put(MediaStore.Images.Media.TITLE, "New Picture")
                values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
                imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

                startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
                startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
            } else if (listItems[i].equals("Choose from gallery")) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, IMAGE_CAPTURE_CODE)
            }

            dialogInterface.dismiss()
        }

        // Set the neutral/cancel button click listener
        /*alertDialog.setNeutralButton("Cancel") { dialog, which ->
            dialog.cancel()
        }*/
        val mDialog = alertDialog.create()
        mDialog.show()
        // Set the neutral/cancel button click listener
        alertDialog.setNeutralButton("Cancel") { dialog, which ->
            dialog.cancel()
        }




       /* val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)*/
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // called when user presses allow or deny
        when(requestCode){
            PERMISSION_CODE ->{
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    openCamera()

                }else{
                    Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle image pict result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        /*if(resultCode == Activity.RESULT_OK){
            //set image captued to image view
            profile_image.setImageURI(imageUri)
        }*/

        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_CAPTURE_CODE){
            //set image captued to image view
            profile_image.setImageURI(data?.data)
        }
    }

    override fun onBackPressed(){
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}