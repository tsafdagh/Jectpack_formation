package com.tsafack.jetpackformation.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogDao {
    @Insert
    suspend fun inserAll(vararg dogs:DogBreed):List<Long>

    @Query("SELECT * FROM dogbreed")
    suspend fun getAllDogs():List<DogBreed>

    @Query("SELECT * FROM dogbreed WHERE uuid = :dogId")
    suspend fun getDog(dogId:Int):DogBreed

    @Query("DELETE FROM dogbreed")
    suspend fun deleteAllDogs()


}