package io.npc.support.util.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

sealed class ListConverter<T> {
    private val gson = Gson()

    @TypeConverter
    fun toList(data: String): List<T> {
        val listType = object : TypeToken<List<T>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun toJson(data: List<T>): String {
        return gson.toJson(data)
    }
}

class StringListConverter : ListConverter<String>()
class IntListConverter : ListConverter<Int>()
class LongListConverter : ListConverter<Long>()
class FloatListConverter : ListConverter<Float>()
class DoubleListConverter : ListConverter<Double>()
