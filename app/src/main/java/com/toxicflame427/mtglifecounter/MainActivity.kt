package com.toxicflame427.mtglifecounter

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.toxicflame427.mtglifecounter.MainActivity

var p1up : Button? = null
var p1down : Button? = null
var p1count : TextView? = null

var p2up : Button? = null
var p2down : Button? = null
var p2count : TextView? = null

//Views for changing the themes
var p1countview : LinearLayout? = null
var p1theme : LinearLayout? = null
var p2countview : LinearLayout? = null
var p2theme : LinearLayout? = null

var startingCount : Int = 20
var p1current : Int = startingCount
var p2current : Int = startingCount

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectViews()
        startCounter()
    }

    fun connectViews(){
        p1up = findViewById<Button>(R.id.p1plus)
        p1down = findViewById<Button>(R.id.p1minus)
        p1count = findViewById<TextView>(R.id.p1count)

        p2up = findViewById<Button>(R.id.p2plus)
        p2down = findViewById<Button>(R.id.p2minus)
        p2count = findViewById<Button>(R.id.p2count)

        p1countview = findViewById<LinearLayout>(R.id.p1countview)
        p1theme = findViewById<LinearLayout>(R.id.p1theme)
        p2countview = findViewById<LinearLayout>(R.id.p2countview)
        p2theme = findViewById<LinearLayout>(R.id.p2theme)
    }

    fun startCounter(){
        p1count?.text = p1current.toString()
        p2count?.text = p2current.toString()
    }

    fun plusOrMinusCountP1(view : View){
        if((view as Button).text == "+"){
            p1current++
            p1count?.text = p1current.toString()
        } else if((view as Button).text == "-"){
            p1current--
            p1count?.text = p1current.toString()
        } else {
            //Nothing will happen, compiler continues on the next line
        }
    }

    fun plusOrMinusCountP2(view : View){
        if((view as Button).text == "+"){
            p2current++
            p2count?.text = p2current.toString()
        } else if((view as Button).text == "-"){
            p2current--
            p2count?.text = p2current.toString()
        } else {
            //Nothing will happen, compiler continues on the next line
        }
    }

    fun resetCounters(view: View){
        p1count?.text = startingCount.toString()
        p2count?.text = startingCount.toString()
        p1current = startingCount
        p2current = startingCount
    }

    fun themeChange(view : View){
        //Detects of it is player 1 and the mana type
        if((view as ImageButton).id == (R.id.blueMana1))
        {
            p1countview?.setBackgroundColor(resources.getColor(R.color.blueMana))
            p1theme?.setBackgroundColor(resources.getColor(R.color.blueMana))
        }
        else if((view as ImageButton).id == (R.id.redMana1))
        {
            p1countview?.setBackgroundColor(resources.getColor(R.color.redMana))
            p1theme?.setBackgroundColor(resources.getColor(R.color.redMana))
        }
        else if((view as ImageButton).id == (R.id.greenMana1))
        {
            p1countview?.setBackgroundColor(resources.getColor(R.color.greenMana))
            p1theme?.setBackgroundColor(resources.getColor(R.color.greenMana))
        }
        else if((view as ImageButton).id == (R.id.blackMana1))
        {
            p1countview?.setBackgroundColor(resources.getColor(R.color.blackMana))
            p1theme?.setBackgroundColor(resources.getColor(R.color.blackMana))
        }
        else if((view as ImageButton).id == (R.id.whiteMana1)){
            p1countview?.setBackgroundColor(resources.getColor(R.color.whiteMana))
            p1theme?.setBackgroundColor(resources.getColor(R.color.whiteMana))
        }
        //Detects if it is player 2 and the mana type
        else if((view as ImageButton).id == (R.id.blueMana2))
        {
            p2countview?.setBackgroundColor(resources.getColor(R.color.blueMana))
            p2theme?.setBackgroundColor(resources.getColor(R.color.blueMana))
        }
        else if((view as ImageButton).id == (R.id.redMana2))
        {
            p2countview?.setBackgroundColor(resources.getColor(R.color.redMana))
            p2theme?.setBackgroundColor(resources.getColor(R.color.redMana))
        }
        else if((view as ImageButton).id == (R.id.greenMana2))
        {
            p2countview?.setBackgroundColor(resources.getColor(R.color.greenMana))
            p2theme?.setBackgroundColor(resources.getColor(R.color.greenMana))
        }
        else if((view as ImageButton).id == (R.id.blackMana2))
        {
            p2countview?.setBackgroundColor(resources.getColor(R.color.blackMana))
            p2theme?.setBackgroundColor(resources.getColor(R.color.blackMana))
        }
        else if((view as ImageButton).id == (R.id.whiteMana2)){
            p2countview?.setBackgroundColor(resources.getColor(R.color.whiteMana))
            p2theme?.setBackgroundColor(resources.getColor(R.color.whiteMana))
        }
        else {
            //Nothing happens, compiler continues on the next line
        }
    }

    //Creates the text dialog box to be able to change that starting life count
    //Attached to the life counters for each player
    fun showDialogBox(view : View){
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.edit_text_layout, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.startingCountEditText)

        with(builder){
            setTitle("Enter the starting life count")
            setPositiveButton("Ok"){dialog, which ->
                if(editText.text.toString().toInt() <= 99) {
                    startingCount = editText.text.toString().toInt()
                    resetCounters(view)
                } else {
                    Toast.makeText(this@MainActivity, "You cannot enter value above 99", Toast.LENGTH_LONG).show()
                }
            }
            setNegativeButton("Cancel"){dialog, which ->
                //Nothing will happen, but it will close
                Log.d("Main", "Negative button clicked")
            }
            setView(dialogLayout)
            show()
        }
    }
}