[![](https://jitpack.io/v/chandreshandroid/MaterialProgressButton.svg)](https://jitpack.io/#chandreshandroid/MaterialProgressButton)


# MaterialProgressButton
Material Button With Progress Bar

<img alt="Demo" src="https://github.com/chandreshandroid/MaterialProgressButton/blob/master/demo1.gif" />

#   List of attributes:

        
 1) ```app:p_style with values "circleBar" , "progressBar"  [default - circleBar]```
 
 Use this for defining style of your progresbar 
 
 2) ```app:p_progressBGColor  [default - button background color]```
 
 3) ```app:p_arrowVisible  [default - true]```
 
Use this for defining style of your material button
        
 4) ```app:p_text  [default - ""]```
 
 5) ```app:p_textColor ```
 
 6) ```app:p_bgColor [Background color]```
 
 7) ```app:p_backgroundTint [Background color]```
      
 8) ```app:p_textSize ```
 
 9) ```app:p_elevation ```
 
 10) ```app:p_icon [Set icon drawable]```
 
 11) ```app:p_iconSize [Set icon size]```
 
 12) ```app:p_iconPadding ```
 
 13) ```app:p_iconGravity [ value "start","textStart","end","textEnd"]```
 
 14) ```app:p_iconTint [change icon color]```

 15) ```app:p_strokeColor ```

 16) ```app:p_rippleColor ```

 17) ```app:p_strokeWidth ```
 
 18) ```app:p_cornerRadius ```
 
 19) ```app:p_fontFamily [Font Name in String i.e  app:p_fontFamily="brandon_reg_it" ] ```
 
 20) ```app:p_layoutDirection [Value ltr,rtl ] ```
        
 21) ```app:p_capsText [default - false]```
        

#   Example of usage:

    xmlns:app="http://schemas.android.com/apk/res-auto"

    <com.chand.progressbutton.ProgressButton android:layout_width="250dp"
                                             android:layout_height="wrap_content"
                                             android:layout_gravity="center"
                                             app:p_backgroundTint="@color/colorAccent"
                                             app:p_text="@string/label"
                                             app:p_progressBGColor="@color/colorSecondary"
                                             app:p_progressColor="@color/colorPrimaryVariant"
                                             app:p_arrowVisible="false"
                                             app:p_bgColor="@color/colorSecondary"
                                             app:p_textColor="@color/white"
                                             app:p_cornerRadius="3dp"
                                             app:p_fontFamily="brandon_reg_it"
                                             app:p_style="progressBar"
                                             app:p_strokeWidth="3dp"
                                             app:p_strokeColor="@color/colorSecondary"
                                             app:p_rippleColor="@color/colorPrimaryVariant"
                                             app:p_layoutDirection="ltr"
                                             app:p_textSize="14sp"
                                             android:id="@+id/pb6"/>
       
        
        ProgressButton button1 = (ProgressButton) findViewById(R.id.pb6);

      
#   List of Listener:

 //OnClick
 
 pb.setOnClickListener {
 
            Toast.makeText(this,"Click",Toast.LENGTH_SHORT).show()
 
 }
        
 //Animation start and stop       
        
        pb.setOnAnimationListener(object: ProgressButton.AnimationListener{
            override fun startAnimationListener() {

            }

            override fun endAnimationListener() {

            }
        } )
        
#   List of Method:        

//Start Animation

 pb.startAnimation()

//End Animation

 pb.endAnimation()
        
#   Adding to project:        
   ##      Gradle
   


Step 1: Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {

                    maven { 
			url 'https://jitpack.io' 
			}
		}
	}


Step 2. Add the dependency

	        implementation 'com.github.chandreshandroid:MaterialProgressButton:1.4'
	     
      
```
Copyright 2019

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
