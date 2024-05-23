package com.lbtt2801.yamevn.utils

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class Product(
//    @PrimaryKey val id: String,
    val id: Int,
    val name: String,
    val description: String,
    val price: Int
)

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT * FROM Product WHERE id = :id")
    suspend fun getProductById(id: String): Product

    @Delete
    suspend fun deleteProduct(product: Product)
}

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
