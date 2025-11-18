package id.antasari.p6minda_230104040222.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {

    // Dipakai untuk operasi biasa (bukan real-time)
    @Query("SELECT * FROM diary_entries ORDER BY timestamp DESC")
    suspend fun getAll(): List<DiaryEntry>

    // Dipakai untuk Flow real-time
    @Query("SELECT * FROM diary_entries ORDER BY timestamp DESC")
    fun observeAll(): Flow<List<DiaryEntry>>

    @Query("SELECT * FROM diary_entries WHERE id = :entryId LIMIT 1")
    suspend fun getById(entryId: Int): DiaryEntry?

    @Insert
    suspend fun insert(entry: DiaryEntry): Long

    @Update
    suspend fun update(entry: DiaryEntry)

    @Delete
    suspend fun delete(entry: DiaryEntry)
}
