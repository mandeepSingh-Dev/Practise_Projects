package com.mandeep.practise_projects.project1.Fragments

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Transition
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.mandeep.practise_projects.R
import com.mandeep.practise_projects.databinding.FragmentDialogBottomBinding
import com.mandeep.practise_projects.project1.MVVM.MainRepositry
import com.mandeep.practise_projects.project1.MainActivity
import com.mandeep.practise_projects.project1.Retrofit.DataClasses.Hit
import com.mandeep.practise_projects.project1.Room.EntityItem
import com.mandeep.practise_projects.project1.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class Bottom_Sheet_Dialog_Fragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentDialogBottomBinding
     var hit:Hit?=null
     val name = System.currentTimeMillis()


    @Inject
    lateinit var mainRepositry:MainRepositry

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentDialogBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d("4t8g4hg","difndf")

        if(arguments!=null)
        {
            hit =  arguments?.getParcelable<Hit>("HIT") as Hit
        }

        binding.downloadButotn.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    hit?.let {
                        mainRepositry.insertData(EntityItem(name.toString(),it.largeImageURL,it.user,it.views))

                     val isDownloaded = Utils.saveToExternalStorage(requireContext(),name.toString(),it.largeImageURL)
                        if(isDownloaded)
                        {
                            Snackbar.make(binding.text4,"Downloaded",500).show()
                        }
                            }
            }
        }

        binding.imageBottom.setOnClickListener {
            findNavController().navigate(R.id.savedWall_Fragment)
        }


        binding.text1.text = hit?.user
        Glide.with(requireContext()).asBitmap().load(hit?.largeImageURL).error(R.drawable.ic_launcher_background).into(object:CustomTarget<Bitmap>(){
            override fun onResourceReady(bitmap: Bitmap, transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?) {

               binding.imageBottom.setImageBitmap(bitmap)

            //  Log.d("4ogj4egv",binding.imageBottom.layoutParams.height.toString())
              //  Log.d("4ogj4egv",bitmap.height.toString()+"            "+hit?.imageHeight.toString())
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })


    }

}