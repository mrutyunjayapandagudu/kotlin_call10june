package com.example.iteradmin.kotlin_call10june

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
val rc=100
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txt=findViewById<EditText>(R.id.text)
        val bt=findViewById<Button>(R.id.button)
        bt.setOnClickListener {

            if(ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED) {


                val i = Intent(Intent.ACTION_CALL)
                val s: String = txt.text.toString()
                i.setData(Uri.parse("tel:"+txt.text.toString()))
                startActivity(i)
            }
            else{
                val ar= arrayOf(Manifest.permission.CALL_PHONE)

                ActivityCompat.requestPermissions(this,ar,rc)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            rc ->{
                if(grantResults.isEmpty()&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"permission allowed",Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this,"permission denied",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
