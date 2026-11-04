package lat.pam.kopidannyemil

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var adapter: MenuAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = DummyData.currentUser?.name ?: "Tamu"
        view.findViewById<TextView>(R.id.tvGreeting).text = "Halo $name,"

        adapter = MenuAdapter(DummyData.menu) {
            (activity as? HomeActivity)?.updateBadge()
        }
        val rv = view.findViewById<RecyclerView>(R.id.rvMenu)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        view.findViewById<ChipGroup>(R.id.chipGroup)
            .setOnCheckedStateChangeListener { _, checkedIds ->
                val list = when (checkedIds.firstOrNull()) {
                    R.id.chipMinuman -> DummyData.menu.filter { it.category == "Minuman" }
                    R.id.chipMakanan -> DummyData.menu.filter { it.category == "Makanan" }
                    else -> DummyData.menu
                }
                adapter.submit(list)
            }
    }
}