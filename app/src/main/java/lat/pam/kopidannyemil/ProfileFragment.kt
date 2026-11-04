package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvGreeting = view.findViewById<TextView>(R.id.tvGreeting)
        val tvAvatar = view.findViewById<TextView>(R.id.tvAvatar)
        val tilName = view.findViewById<TextInputLayout>(R.id.tilName)
        val etName = view.findViewById<TextInputEditText>(R.id.etName)

        fun refreshHeader() {
            val name = DummyData.currentUser?.name ?: "Tamu"
            tvGreeting.text = "Halo $name,"
            tvAvatar.text = name.take(1).uppercase()
        }

        etName.setText(DummyData.currentUser?.name ?: "")
        refreshHeader()

        view.findViewById<MaterialButton>(R.id.btnSave).setOnClickListener {
            val newName = etName.text.toString().trim()
            tilName.error = null
            if (newName.isEmpty()) {
                tilName.error = "Nama tidak boleh kosong"
                return@setOnClickListener
            }
            DummyData.currentUser?.name = newName
            refreshHeader()
            Toast.makeText(requireContext(), "Profile berhasil disimpan", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<MaterialButton>(R.id.btnLogout).setOnClickListener {
            DummyData.currentUser = null
            DummyData.cart.clear()
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finishAffinity()
        }
    }
}