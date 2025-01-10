# Product Inventory Management and Missing Number Problem Solutions

This repository contains solutions to two problems: **Product Inventory Management** and **Missing Number Problem**, implemented in **Kotlin**.

---

## **Problem 1: Product Inventory Management**

### **Description**
Solution is available in ***src/ProductInventory.kt***. 
Manage an inventory of products, each defined by its name, price, and stock quantity. The tasks include:
1. Calculating the total inventory value.
2. Finding the most expensive product.
3. Checking if a specific product exists in the inventory.
4. Sorting the products by price or quantity in ascending/descending order.

### **Input**
A predefined list of products:
```kotlin
val products = listOf(
    Product("Laptop", 999.99, 5),
    Product("Smartphone", 499.99, 10),
    Product("Tablet", 299.99, 0),
    Product("Smartwatch", 199.99, 3)
)
```

### **Output Examples**
- Total inventory value: `10,599.82`
- Most expensive product: `"Laptop"`
- Is 'Headphones' in stock: `false`
- Products sorted by price or quantity (ascending/descending).

---

## **Problem 2: Missing Number Problem**

### **Description**
Solution is available in ***src/ArrayManipulation.kt***. 
Find the missing number in an array of distinct integers ranging from `1` to `n + 1`. The array size is `n`.

### **Input Example**
An array of size `n = 6`:
```kotlin
val input = intArrayOf(3, 7, 1, 2, 6, 4)
```

### **Output Example**
The missing number: `5`

---

## **Technologies Used**
- **Kotlin**: Programming language.
- **IntelliJ IDEA**: Recommended IDE.

---
