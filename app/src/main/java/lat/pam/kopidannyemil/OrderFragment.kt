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

class OrderFragment : Fragment(R.layout.fragment_order) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvSummary = view.findViewById<TextView>(R.id.tvSummary)
        val tvTotal = view.findViewById<TextView>(R.id.tvTotal)
        val tilName = view.findViewById<TextInputLayout>(R.id.tilName)
        val tilAddress = view.findViewById<TextInputLayout>(R.id.tilAddress)
        val etName = view.findViewById<TextInputEditText>(R.id.etName)
        val etAddress = view.findViewById<TextInputEditText>(R.id.etAddress)

        etName.setText(DummyData.currentUser?.name ?: "")

        if (DummyData.cart.isEmpty()) {
            tvSummary.text = "Belum ada pesanan. Tambahkan menu dari tab Home."
            tvTotal.visibility = View.GONE
        } else {
            tvSummary.text = DummyData.cart.entries.joinToString("\n") {
                "${it.value}x  ${it.key.name}  -  ${DummyData.rupiah(it.key.price * it.value)}"
            }
            tvTotal.text = "Total: ${DummyData.rupiah(DummyData.cartTotal())}"
        }

        view.findViewById<MaterialButton>(R.id.btnOrder).setOnClickListener {
            tilName.error = null
            tilAddress.error = null

            if (DummyData.cart.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Pesanan masih kosong, pilih menu di Home dulu",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (etName.text.toString().trim().isEmpty()) {
                tilName.error = "Nama lengkap wajib diisi"
                return@setOnClickListener
            }
            if (etAddress.text.toString().trim().isEmpty()) {
                tilAddress.error = "Alamat wajib diisi"
                return@setOnClickListener
            }

            DummyData.cart.clear()
            startActivity(Intent(requireContext(), ConfirmationActivity::class.java))
        }
    }
}