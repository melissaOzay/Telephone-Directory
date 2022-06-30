package com.example.atildeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var name = findViewById<TextView>(R.id.name)
        val surname = findViewById<TextView>(R.id.surname)
        val number = findViewById<TextView>(R.id.number)


        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Direktory Record"
            println("geri ye bastı")
        }

        findViewById<Button>(R.id.ButtonKayıt).setOnClickListener {
            val intent = Intent()

            intent.putExtra("kullanici_adi", name.text.toString())
            intent.putExtra("kullanici_soyadi", surname.text.toString())
            intent.putExtra("kullanici_telefon", number.text.toString())
            /*    var landmark =
                    Landmark(name.text.toString(), surname.text.toString(), number.text.toString())
                Data.landmarklis.add(landmark)*/
            setResult(501, intent);
            onBackPressed()


            // bu beni ilk sayfaya yolluyor.İkinci activity den birinciye geçerken intent kullanılmaz ama birinciden ikinciye geçerken intent kullanabilirsin.
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true


    }

}

