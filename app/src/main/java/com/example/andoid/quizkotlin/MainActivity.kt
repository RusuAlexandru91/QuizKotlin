package com.example.andoid.quizkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var quantity: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Listener for increment
        incrementButton.setOnClickListener {
            if (quantity == 10) {
                Toast.makeText(this, "Max value 10H", Toast.LENGTH_SHORT).show()
            } else {
                quantity++
            }
            display(quantity)
        }
        // Listener for decrement
        decrementButton.setOnClickListener {
            if (quantity == 1) {
                Toast.makeText(this, "Min value 1H", Toast.LENGTH_SHORT).show()
            } else {
                quantity--
            }
            display(quantity)
        }
        // Listener for submitButton
        submitButtom.setOnClickListener {
            // Getting the name
            val editText: String = editText.text.toString()
            // Getting the time
            val time: Int = display(quantity)
            // Takeing the response
            val response: String = createResults(editText, getGender(), getEducation(), getDevice(), time, getPlatform())
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_EMAIL, "Kiwibv@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "Quiz result")
            intent.putExtra(Intent.EXTRA_TEXT, response)
            intent.type = "message/rfc822"
            startActivity(Intent.createChooser(intent, "Send Email using:"))
        }
    }

    // Get platform
    private fun getPlatform(): String {
        var platform = "6. Platforms used: "
        val isYoutube: Boolean = youtubecheckBox.isChecked
        val isNetflix: Boolean = netflixCheckBox.isChecked
        val isFacebook: Boolean = facebookCheckBox.isChecked
        val isInstagram: Boolean = instagramCheckBox.isChecked

        if (isYoutube) {
            platform += "\nYoutubee"
        }
        if (isNetflix) {
            platform += "\n Netflix"
        }
        if (isFacebook) {
            platform += "\n Facebook"
        }
        if (isInstagram) {
            platform += "\n Instagram"
        }

        return platform
    }

    // Get devices
    private fun getDevice(): String {
        var device = "4. Devices used: "

        val isSmartphone: Boolean = smartPhonecheckBox.isChecked
        val isLaptop: Boolean = laptopPcCheckBox.isChecked
        val isTablet: Boolean = tabletCheckBox.isChecked
        val isTv: Boolean = TvcheckBox.isChecked

        if (isSmartphone) {
            device += "\nSmartphone"
        }
        if (isLaptop) {
            device += "\n Laptop/Pc"
        }
        if (isTablet) {
            device += "\n Tablet"
        }
        if (isTv) {
            device += "\n Smart Tv"
        }

        return device
    }

    //This method displays the given quantity value on the screen.
    private fun display(number: Int): Int {
        quantity_text_view.text = "$number"
        return number
    }
   // Get gender
    private fun getGender(): String {
        val genderSelected: String = when (genderRadioGroup.checkedRadioButtonId) {
            R.id.maleRadioButton ->  "Male"
            R.id.femaleRadioButton -> "Female"
            else -> "Not provided"
        }
        return genderSelected
    }
    // Get education
    private fun getEducation(): String {
        val education: String = when (educationRadioGroup.checkedRadioButtonId) {
            R.id.middleSchoolRadioButton -> "Middle School"
            R.id.hightSchoolRadioButton -> "High School"
            R.id.collageRadioButton -> "Collage"
            R.id.moreThenRadioButton -> "More then Collage"
            else -> "Not provided"
        }
        return education
    }

    // create resultus to be displayed
    private fun createResults(name: String, gender: String, education: String, device: String, time: Int, platform: String): String {
        var result = "1. Name: $name"
        result += "\n\n2. Gender: $gender"
        result += "\n\n3. Education level: $education"
        result += "\n\n$device"
        result += "\n\n5. Time online: $time H"
        result += "\n\n$platform"
        return result
    }
}