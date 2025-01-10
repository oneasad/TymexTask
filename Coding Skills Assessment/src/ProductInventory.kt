data class Product(val name: String, val price: Double, val quantity: Int)

fun main() {
    val products = listOf(
        Product("Laptop", 999.99, 5),
        Product("Smartphone", 499.99, 10),
        Product("Tablet", 299.99, 0),
        Product("Smartwatch", 199.99, 3)
    )

    val totalInventoryValue = products.sumOf { it.price * it.quantity }
    println("Total inventory value: $totalInventoryValue")

    val mostExpensiveProduct = products.maxByOrNull { it.price }?.name
    println("Most expensive product: $mostExpensiveProduct")

    val isHeadphonesInStock = products.any { it.name == "Headphones" }
    println("Is 'Headphones' in stock: $isHeadphonesInStock")

    val productsSortedByPriceAsc = products.sortedBy { it.price }
    val productsSortedByPriceDesc = products.sortedByDescending { it.price }
    println("Products sorted by price (ascending): $productsSortedByPriceAsc")
    println("Products sorted by price (descending): $productsSortedByPriceDesc")

    val productsSortedByQuantityAsc = products.sortedBy { it.quantity }
    val productsSortedByQuantityDesc = products.sortedByDescending { it.quantity }
    println("Products sorted by quantity (ascending): $productsSortedByQuantityAsc")
    println("Products sorted by quantity (descending): $productsSortedByQuantityDesc")
}
