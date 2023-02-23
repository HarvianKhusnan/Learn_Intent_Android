package com.example.learn_intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvResult: TextView
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null) {
            val selectedValue =
                result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0)
            tvResult.text = "Hasil : $selectedValue"
        }

        /*
        Untuk membuat activity yang dapat mengembalikan nilai menggunakan intent, kita perlu membuat objek
        ActivityResultLauncher seperti diatas dan untuk mendapatkan hasil dari intent, kita perlu menggunakan
        kode registerForActivityResult dan parameter ActivityResulrContract
         */

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithObject:Button = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        val btnMoveWithData:Button = findViewById(R.id.btn_move_data)
        btnMoveWithData.setOnClickListener(this)

        val btnDialPhone: Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        val btnMoveForResultActivity:Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResultActivity.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_data -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodingAcademy Boy")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5)
                startActivity(moveWithDataIntent)

                /*
                Untuk memindahkan data dengan intent, kita perlu menngunakan kode putExtra(), putExtra
                digunakan untuk mengirim data ketika melakukan intent dikarenakan putExtra Memiliki beberapa
                value, yang berfungsi untuk menanmpung dan mengirim data.
                 */


            }

            R.id.btn_move_activity_object -> {
                val person = Person(
                    "DicodingAcademy",
                    5,
                    "Academy@dicoding.com",
                    "Bandung"
                )

                val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }

            R.id.btn_dial_number -> {
                val phoneNumber = "081283018984"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_for_result -> {
                val moveResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(moveResultIntent)

                /*
                Perbedaan intent yang akan mengembalikan hasil dengan intent biasa adalah Intent biasa menggunakan
                kode startActivity() yang dimana akan melakukan perpindahan saja, berbeda dengan intent yang menggunakan
                launch() dimana intent tersebut akan mengirim hasil atau umpan balik ketika intent dijalankan, misalnya
                pada tampilan android, ketika kita memilih angka 100 maka angka tersebut akan dikirim ke main activity.
                 */
            }
        }
    }
}