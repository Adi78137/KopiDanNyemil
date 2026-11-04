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

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        val tilName = findViewById<TextInputLayout>(R.id.tilName)
        val tilUsername = findViewById<TextInputLayout>(R.id.tilUsername)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)

        findViewById<MaterialButton>(R.id.btnRegister).setOnClickListener {
            val name = etName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString()

            tilName.error = null
            tilUsername.error = null
            tilPassword.error = null

            if (name.isEmpty()) {
                tilName.error = "Nama lengkap wajib diisi"
                return@setOnClickListener
            }
            if (username.isEmpty()) {
                tilUsername.error = "Username wajib diisi"
                return@setOnClickListener
            }
            if (DummyData.users.any { it.username == username }) {
                tilUsername.error = "Username sudah dipakai"
                return@setOnClickListener
            }
            if (password.length < 6) {
                tilPassword.error = "Password minimal 6 karakter"
                return@setOnClickListener
            }

            val user = User(name, username, password)
            DummyData.users.add(user)
            DummyData.currentUser = user
            startActivity(Intent(this, HomeActivity::class.java))
            finishAffinity()
        }
    }
}