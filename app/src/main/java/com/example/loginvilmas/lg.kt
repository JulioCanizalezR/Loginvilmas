package com.example.loginvilmas

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.sql.PreparedStatement
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.jar.Attributes

private var connectSql = ConnectSql()

lateinit var Usuario2: EditText
lateinit var Contraseña2: EditText
lateinit var Boton2: Button
lateinit var registrar2: TextView
lateinit var loginempl2: TextView


class lg : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        supportActionBar?.hide()
        Boton2= findViewById(R.id.btnaceptar);
        Contraseña2 = findViewById(R.id.contraseña2);
        Usuario2 = findViewById(R.id.usuario2);
        registrar2 = findViewById(R.id.txtregistarse2);
        loginempl2 = findViewById(R.id.txtloginemple2);

        val intent: Intent = Intent(this, Register::class.java)
        val Intent2: Intent = Intent(this, Recuperacio::class.java)
        val intent1: Intent = Intent(this, Agrega::class.java)
        Contraseña2.setOnClickListener {
            startActivity(Intent2);
        }
        registrar2.setOnClickListener{
            startActivity(intent);
        }

        Boton2.setOnClickListener {
            val Contrasena = md5(Contraseña2.text.toString());
            try {
                val statement = connectSql.dbConn()?.createStatement()
                val resultSet =statement?.executeQuery("SELECT Nombre_usuario, contraseña from TbCredeciales Where Nombre_usuario = '${Usuario2.text.toString()}' and contraseña= '${Contrasena}'")

                while (resultSet?.next() == true)
                {
                    val a1 = resultSet.getString("Nombre_usuario")
                    val a2 = resultSet.getString("contraseña")

                    if (Usuario2.text.toString() == a1 && Contrasena == a2)
                    {
                        startActivity(intent1)
                        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            catch (ex: SQLException)
            {
                Toast.makeText(this, "Error al mostrar", Toast.LENGTH_SHORT).show()
            }

        }
    }
}