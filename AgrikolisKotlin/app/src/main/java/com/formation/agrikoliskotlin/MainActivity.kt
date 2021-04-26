package com.formation.agrikoliskotlin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object{
        val EXTRA_LOGIN = "login"
        val EXTRA_PASSWORD = "password"
    }
    var loginError: TextView? = null
    var passwordError: TextView? = null
    var user: EditText? = null
    var mdp: EditText? = null
    var myButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            user = findViewById<View>(R.id.login) as EditText

            user!!.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (user!!.text.toString().trim { it <= ' ' } == "") {
                        user!!.error = "Veuillez entrer un identifiant"
                    } else if (s.toString().trim { it <= ' ' }.length < 5) {
                        user!!.error =
                            "Votre identifiant doit posséder au moins cinq caractères | " + (5 - user!!.length())
                    }
                }

                override fun afterTextChanged(s: Editable) {}
            })

            mdp = findViewById<View>(R.id.pass) as EditText
            loginError = findViewById<View>(R.id.loginError) as TextView
            passwordError = findViewById<View>(R.id.passwordError) as TextView
            myButton = findViewById<View>(R.id.next_button) as Button

            //TOAST
            myButton!!.setOnClickListener {
                Log.d("Essai", "OK")
                //                loginError.setText("");
//                passwordError.setText("");
                passwordError!!.visibility = View.INVISIBLE
                loginError!!.visibility = View.INVISIBLE
                //                setContentView(R.layout.activity_second);
//                if (user.getText() != null) {
                if (user!!.text.toString().trim { it <= ' ' } == "") {
//                if (user.getText().equals(null)) {
//                if (user.getText() == null) {
//                    connection.setError("Veuillez entrer votre identifiant");
//                    connection.setError();
                    loginError!!.visibility = View.VISIBLE
                    //                    connection.setEnabled(true);
                    loginError!!.text = "Veuillez entrer un identifiant"
                    //                    try {
//                        Thread.sleep(3000);
//                        connection.setVisibility(View.INVISIBLE);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
                //                else if(user.getText().toString().trim().length() <5) {
                if (user!!.text.toString().trim { it <= ' ' }.length < 5) {
                    Log.d("essai", "loop3")
                    //                    connection.setText("");
                    loginError!!.visibility = View.VISIBLE
                    loginError!!.text = "Votre identifiant doit posséder au moins cinq caractères"
                    //ctrl + clic sur fonction
//                    connection.setError("Votre identifiant doit posséder au moins trois caractères");
//                    connection.setFocusable(true);
                    loginError!!.error = "a"

//                }else{
                }
                if (user!!.text.toString().trim { it <= ' ' } != EXTRA_LOGIN) {
                    loginError!!.visibility = View.VISIBLE
                    loginError!!.text = "Identifiant incorrect | Réessayer"
                }
                if (mdp!!.text.toString().trim { it <= ' ' } != EXTRA_PASSWORD) {
                    passwordError!!.visibility = View.VISIBLE
                    passwordError!!.text = "Mot de passe incorrect | Réessayer"
                }
                if (user!!.text.toString()
                        .trim { it <= ' ' } == EXTRA_LOGIN && mdp!!.text.toString()
                        .trim { it <= ' ' } == EXTRA_PASSWORD
                ) {
                    val intent = Intent(this@MainActivity,ActivitySecond::class.java) //Lancer l'activité DisplayVue
                    intent.putExtra(EXTRA_LOGIN, user!!.text.toString())
                    intent.putExtra(EXTRA_PASSWORD, mdp!!.text.toString())
                    startActivity(intent) //Afficher la vue
                }
            }
        }
}