package com.assignment1.helloworld.details

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.assignment1.helloworld.*

class ForecastDetailsActivity : AppCompatActivity() {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

        setTitle(R.string.forecast_details)

        val tempText = findViewById<TextView>(R.id.tempText)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)


        val temp = intent.getFloatExtra("key_temp", 0f)

        tempText.text = formatTempForDisplay(temp, tempDisplaySettingManager.getTempDisplaySetting())
        descriptionText.text = intent.getStringExtra("key_description")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handke item selection
        return when(item.itemId){
            R.id.tempDisplaySetting->{
                // do sth
                showTempDisplaySettingDialog(
                    this,
                    tempDisplaySettingManager
                )                //Toast.makeText(this, "clicked Menu Item", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showTempDisplaySettingDialog(){
        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle("choose Display Units")
            .setMessage("chosse which tempetature unit to use for temperature display")
            .setPositiveButton("F°"){ _,_ ->
                tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenheit)
                //Toast.makeText(this,"show using F", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("C°") { _, _ ->
                tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celsius)
                //Toast.makeText(this, "show using C", Toast.LENGTH_SHORT).show()
            }
            .setOnDismissListener{
                Toast.makeText(this,"setting will take effect on App restart", Toast.LENGTH_SHORT).show()
            }
        dialogBuilder.show()
    }

}