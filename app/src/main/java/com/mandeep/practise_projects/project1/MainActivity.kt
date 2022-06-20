package com.mandeep.practise_projects.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.mandeep.practise_projects.R
import com.mandeep.practise_projects.databinding.ActivityMainBinding
import com.mandeep.practise_projects.project1.Fragments.Bottom_Sheet_Dialog_Fragment
import com.mandeep.practise_projects.project1.Retrofit.DataClasses.Hit
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    lateinit var binding:ActivityMainBinding
    lateinit var hit:Hit
    lateinit var onMyListenr: OnMyListenr

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        Log.d("fe9if","wewwwwwwwwwwwdofmdf")

        setContentView(binding.root)


    }

    fun showDialog(hit: Hit){

        this.hit = hit
        val bottomSheetDialogFragment = Bottom_Sheet_Dialog_Fragment()
        val bundle = Bundle()
        bundle.putParcelable("HIT",hit)
        bottomSheetDialogFragment.arguments = bundle

        val fragmentMnaager = supportFragmentManager
        bottomSheetDialogFragment.show(fragmentMnaager,bottomSheetDialogFragment.tag)
    }

    interface OnMyListenr{
        fun onMYClick(hit:Hit)
    }
}