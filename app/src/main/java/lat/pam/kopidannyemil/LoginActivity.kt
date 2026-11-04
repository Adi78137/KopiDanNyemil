package lat.pam.kopidannyemil

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.content.Intent

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        val tilUsername = findViewById<TextInputLayout>(R.id.tilUsername)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)

        findViewById<MaterialButton>(R.id.btnLogin).setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString()

            tilUsername.error = null
            tilPassword.error = null

            if (username.isEmpty()) {
                tilUsername.error = "Username wajib diisi"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                tilPassword.error = "Password wajib diisi"
                return@setOnClickListener
            }

            val user = DummyData.users.find {
                it.username == username && it.password == password
            }
            if (user == null) {
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            } else {
                DummyData.currentUser = user
                startActivity(Intent(this, HomeActivity::class.java))
                finishAffinity()
            }
        }
    }
}