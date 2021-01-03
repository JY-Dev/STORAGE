package com.example.storage.data

interface Repository<T> {
    suspend fun insert(data : T)
    suspend fun update(data : T)
    suspend fun delete(data : T)
    suspend fun getData() : MutableList<T>
    suspend fun getDataFromKey(key : Any) : T?
}