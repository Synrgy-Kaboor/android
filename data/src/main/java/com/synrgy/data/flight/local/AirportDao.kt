package com.synrgy.data.flight.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahidabd.library.data.CoroutineLocalDb
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 2/3/2024.
 * Github github.com/wahidabd.
 */


@Dao
interface AirportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveList(airports: List<AirportEntity>)

    @Query("SELECT * FROM flight")
    fun getList(): Flow<List<AirportEntity>>

}