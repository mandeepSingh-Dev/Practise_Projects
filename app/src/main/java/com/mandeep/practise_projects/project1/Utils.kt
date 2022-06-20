package com.mandeep.practise_projects.project1

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.mandeep.practise_projects.R
import java.lang.Exception

class Utils {

    companion object{
        fun saveToExternalStorage(context: Context, name:String, imageLink:String):Boolean {


            try {
                val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
                } else {
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                }

                // val cursor =  context.contentResolver.query(uri,null,null,null,null,null)

                val contentValues = ContentValues()
                contentValues.apply {
                    put(MediaStore.Images.Media.MIME_TYPE, "image/*")
                    put(MediaStore.Images.Media.DISPLAY_NAME, name + ".jpg")
                    put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    put(MediaStore.Images.Media.TITLE, name + ".jpg")
                }

                val insertedUri = context.contentResolver.insert(uri, contentValues)

                insertedUri?.let {
                    val out = context.contentResolver.openOutputStream(it)

                    Glide.with(context).asBitmap().load(imageLink).error(R.drawable.ic_launcher_background).into(object : CustomTarget<Bitmap>() {
                            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {

                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                                out?.close()
                            }

                            override fun onLoadCleared(placeholder: Drawable?) {}
                        })
                }

            }catch (e:Exception){
                return false
            }
            return true
            }

        fun readFromExternalStroage(context: Context,name:String){

            val uri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)

            val selection = MediaStore.Images.Media.DISPLAY_NAME + " like ?"
            val selectionArgs = arrayOf("%$name.jpg%")
           val cursor =  context.contentResolver.query(uri,null,selection,selectionArgs,null)

            cursor?.let {
               val idCol = it.getColumnIndex(MediaStore.Images.Media._ID)
                val displaynameCol = it.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)

                while(it.moveToNext())
                {
                    val id= it.getString(idCol)
                    val displayName=  it.getString(displaynameCol)

                    Toast.makeText(context,displayName+" cdfd",Toast.LENGTH_SHORT).show()
                }
            }
            cursor?.close()

        }

    }

}