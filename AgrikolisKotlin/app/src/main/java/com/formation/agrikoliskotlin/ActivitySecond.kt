package com.formation.agrikoliskotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivitySecond : AppCompatActivity(), View.OnClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //        setContentView(R.menu.activity_second);

        val login = findViewById<TextView>(R.id.userLogin)
        val password = findViewById<TextView>(R.id.userPassword)
//    var password: String? = null
//        val EXTRA_LOGIN = "login"
//        val EXTRA_PASSWORD = "password"
//    var buttonListe: Button? = null
//        var buttonWebView: Button? = null

        val intent = intent
        val loginTxt = intent.getStringExtra(MainActivity.EXTRA_LOGIN)
        val passwordTxt = intent.getStringExtra(MainActivity.EXTRA_PASSWORD)
//        var login =
//        login.setText("Bienvenu $loginTxt")
        login.text = "Bienvenu ${loginTxt}"
//        password = findViewById<TextView>(R.id.userPassword).toString()
//        password.setText(passwordTxt)
        password.text = passwordTxt
        var buttonListe = findViewById<Button>(R.id.btn_liste)
        buttonListe.setOnClickListener(this)
        var buttonWebView = findViewById<Button>(R.id.btn_web_view)
        buttonWebView.setOnClickListener(this)

        // ou lambda
//        buttonListe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivitySecond.this, ActivityThird.class);
//                startActivity(intent);
//            }
//        });
//
//        buttonWebView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivitySecond.this, ActivityFourth.class);
//                startActivity(intent);
//            }
//        });


//
//
//        public boolean onCreateOptionsMenu(Menu Menu menu;
//        menu) {
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.activity_second, menu);
//            return true;
//        }

//    returnButton = (Button) findViewById(R.id.return_button);
//
//        returnButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Log.d("Essai", "OK");
//            setContentView(R.layout.activity_main);
//        }
//    });
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_liste -> {
                val intent = Intent(this@ActivitySecond, NoteListActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_web_view -> {
                val intentwv = Intent(this@ActivitySecond, ActivityFourth::class.java)
                // Première essai pour le WebView -> incorrect
//                String url = "https://agrikolis.com/";
//                Intent intentwv = new Intent(Intent.ACTION_VIEW);
//                intentwv.setData(Uri.parse(url));
                startActivity(intentwv)
            }
        }
    }
}