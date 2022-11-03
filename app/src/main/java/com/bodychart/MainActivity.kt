package com.bodychart

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bodychart.databinding.ActivityMainBinding
import com.bodychart.databinding.DialogBodyBinding
import com.devs.vectorchildfinder.VectorChildFinder
import com.devs.vectorchildfinder.VectorDrawableCompat
import com.google.android.material.bottomsheet.BottomSheetDialog


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val part: ArrayList<String> = ArrayList<String>()
    private lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mContext = this

        binding.icNotifications.setOnPathClickListener { richPath ->
            try {
                L.showError(richPath.name)
                L.showToast(mContext, richPath.name)

                part.add(richPath.name)
                setPartSelected(richPath.name)
            } catch (e: NullPointerException) {
                L.showError(e.toString())
            }


        }

    }




    private fun setPartSelected(name: String) {
        val vector = VectorChildFinder(this, R.drawable.body, binding.icNotifications)
        part.map {
            val path1: VectorDrawableCompat.VFullPath = vector.findPathByName(it)
            path1.fillColor = Color.RED
        }


        part.map {
            if(!it.equals(name,ignoreCase = false)){
                showBottomSheetDialogForBodyChart()
            }
        }


    }
    private fun showBottomSheetDialogForBodyChart() {

        val mBottomSheetDialog = BottomSheetDialog(mContext, R.style.BottomSheetDialog)
        val dialogBodyBinding: DialogBodyBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R
            .layout.dialog_body,
                null, false)
            mBottomSheetDialog.setContentView(dialogBodyBinding.root)


        dialogBodyBinding.imgClose.setOnClickListener {
            mBottomSheetDialog.dismiss()

        }

        mBottomSheetDialog.show()
        }

}