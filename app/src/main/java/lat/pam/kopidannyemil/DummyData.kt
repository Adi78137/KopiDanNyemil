package lat.pam.kopidannyemil

data class MenuItem(
    val name: String,
    val desc: String,
    val price: Int,
    val emoji: String,
    val category: String
)

data class User(
    var name: String,
    val username: String,
    val password: String
)

object DummyData {

    val menu = listOf(
        MenuItem("Kopi Susu Gula Aren", "Espresso, susu, gula aren", 22000, "☕", "Minuman"),
        MenuItem("Donat Cokelat", "Donat empuk, glaze cokelat", 12000, "🍩", "Makanan"),
        MenuItem("Sourdough Loaf", "Roti fermentasi alami", 35000, "🍞", "Makanan"),
        MenuItem("Cappuccino", "Espresso, foam susu lembut", 25000, "🥛", "Minuman"),
        MenuItem("Matcha Latte", "Matcha Jepang, susu segar", 27000, "🍵", "Minuman"),
        MenuItem("Croissant Butter", "Pastry renyah, butter premium", 20000, "🥐", "Makanan"),
        MenuItem("Choco Chip Cookies", "Cookies renyah, choco chip", 15000, "🍪", "Makanan")
    )

    val users = mutableListOf(
        User("Adi Karunia", "admin", "admin123")
    )

    var currentUser: User? = null

    val cart = mutableMapOf<MenuItem, Int>()

    fun qty(item: MenuItem): Int = cart[item] ?: 0

    fun addToCart(item: MenuItem) {
        cart[item] = qty(item) + 1
    }

    fun removeFromCart(item: MenuItem) {
        val q = qty(item)
        if (q <= 1) cart.remove(item) else cart[item] = q - 1
    }

    fun cartCount(): Int = cart.values.sum()

    fun cartTotal(): Int = cart.entries.sumOf { it.key.price * it.value }

    fun rupiah(value: Int): String {
        return "Rp " + "%,d".format(value).replace(',', '.')
    }
}