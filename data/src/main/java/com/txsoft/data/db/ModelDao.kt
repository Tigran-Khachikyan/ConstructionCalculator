package com.txsoft.data.db

import androidx.room.*
import com.txsoft.data.entities.ModelEntity

@Dao
interface ModelDao {

    @Query("SELECT * FROM _MODELS")
    fun getAllModels(): List<ModelEntity>

    @Query("SELECT * FROM _MODELS WHERE _id =:id")
    fun get(id: Int): ModelEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: ModelEntity)

    @Query("DELETE FROM _MODELS WHERE _id =:id")
    suspend fun deleteById(id: Int)

    @Delete
    suspend fun delete(model: ModelEntity)

    @Delete
    suspend fun deleteList(model: List<ModelEntity>)

    @Query("DELETE FROM _MODELS")
    suspend fun clearAll()


}