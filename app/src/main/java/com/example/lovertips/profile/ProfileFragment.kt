package com.example.lovertips.profile


import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lovertips.R
import com.example.lovertips.profile.adapters.ProfileAdapter
import com.example.lovertips.profile.model.ProfileViewModel
import com.example.lovertips.profile.providers.ProfileData
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(), ProfileAdapter.OnClickProfile {


    private lateinit var profileViewModel: ProfileViewModel
    private var model: ProfileViewCommunicator?=null
    private val IMAGE_CAPTURE_CODE: Int = 1001
    private val PERMISSION_CODE: Int = 1000
    var imageUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
       // val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        val photo: CircleImageView = root.findViewById(R.id.profile_image)


        val recyclerView: RecyclerView = root.findViewById(R.id.profile_recycler_view)
        recyclerView.setHasFixedSize(true)

        val linearLayoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setLayoutManager(linearLayoutManager)

        val data2 = ProfileData()
        val listViewAdapter =
            ProfileAdapter(
                requireActivity(),
                data2.quick_links(),
                this
            )
        recyclerView.adapter = listViewAdapter

        photo.setOnClickListener{

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {

                if (ActivityCompat.checkSelfPermission(context!!, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                    || ActivityCompat.checkSelfPermission(
                        context!!,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    requestPermissions(
                        activity!!, arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE

                        ),
                        PERMISSION_CODE
                    )
                } else {
                    openCamera()
                }
            }else{
                openCamera()
            }
        }


        return root
    }


    override fun onItemClicked(item: ProfileViewModel, position: Int) {
        if(position == 0){
            val intent = Intent(activity, ProfileInformationActivity::class.java)
            activity?.startActivity(intent)
        }
        //model= ViewModelProviders.of(activity!!).get(ProfileViewCommunicator::class.java)

        //set the message to share to another fragment
       // model!!.setMsgCommunicator(edt.text.toString())
        //Launch the data receiver fragment

    }

    private fun openCamera() {

        val listItems = arrayOf("Take Photo", "Choose from gallery")
        val textView = TextView(activity)
        textView.text = "Choose your profile photo"
        textView.setPadding(20, 30, 20, 30)
        textView.textSize = 20f
        textView.setBackgroundColor(Color.DKGRAY)
        textView.setTextColor(Color.WHITE)
        val alertDialog = AlertDialog.Builder(activity!!)
        alertDialog.setCustomTitle(textView)

        alertDialog.setSingleChoiceItems(listItems, -1) {
                dialogInterface, i ->
            if (listItems[i].equals("Take Photo")) {
                val values = ContentValues()
                values.put(MediaStore.Images.Media.TITLE, "New Picture")
                values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
                imageUri = activity!!.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

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
                    Toast.makeText(requireContext(),"permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle image pict result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            //set image captued to image view
            profile_image.setImageURI(imageUri)
        }

        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_CAPTURE_CODE){
            //set image captued to image view
            profile_image.setImageURI(data?.data)
        }
    }


}