package com.example.android.infinitescroll.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android.infinitescroll.api.RickAndMortyApi
import com.example.android.infinitescroll.models.CharacterData

class PagingSource(var status: String): PagingSource<Int, CharacterData>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
        return try {
            val currentPage = params.key ?: 1
            val response = RickAndMortyApi.retrofitService.getCharacter(currentPage, status).await()
            val responseData = mutableListOf<CharacterData>()
            responseData.addAll(response.results)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}