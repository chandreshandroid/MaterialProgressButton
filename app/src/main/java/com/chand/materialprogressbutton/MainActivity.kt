package com.chand.materialprogressbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chand.progressbutton.ProgressButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.setOnClickListener {
            if(!pb.isStartAnim)
            {
                textView.text="End Animation"
                pb.startAnimation()
                pb1.startAnimation()
                pb2.startAnimation()
                pb3.startAnimation()
                pb4.startAnimation()
                pb5.startAnimation()
                pb6.startAnimation()
            }
            else {
                textView.text="Start Animation"
                pb.endAnimation()
                pb1.endAnimation()
                pb2.endAnimation()
                pb3.endAnimation()
                pb4.endAnimation()
                pb5.endAnimation()
                pb6.endAnimation()
            }
        }

        pb.setOnClickListener {
            Toast.makeText(this,"Click", Toast.LENGTH_SHORT).show()
        }
        pb.setOnAnimationListener(object: ProgressButton.AnimationListener{
            override fun startAnimationListener() {

            }

            override fun endAnimationListener() {

            }
        } )
    }
}
