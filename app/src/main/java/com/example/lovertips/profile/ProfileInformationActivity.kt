package com.example.lovertips.profile

import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.lovertips.R


class ProfileInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_personal_info)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_personal_info)
        setSupportActionBar(toolbar)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

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