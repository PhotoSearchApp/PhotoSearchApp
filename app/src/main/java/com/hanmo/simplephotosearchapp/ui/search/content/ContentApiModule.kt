package com.hanmo.simplephotosearchapp.ui.search.content

import com.hanmo.simplephotosearchapp.network.PhotoSearchApi
import com.hanmo.simplephotosearchapp.repository.PhotoSearchRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ContentApiModule {

    @Provides
    fun providePhotoSearchApi(retrofit: Retrofit) : PhotoSearchApi {
        return retrofit.create(PhotoSearchApi::class.java)
    }

    @Provides
    fun providePhotoSearchRepository(photoSearchApi: PhotoSearchApi) : PhotoSearchRepository {
        return PhotoSearchRepository(photoSearchApi)
    }

}