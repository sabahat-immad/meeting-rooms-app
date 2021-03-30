package saba.qazi.meetingrooms.meetingroomdetail.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit
import saba.qazi.meetingrooms.meetingroomdetail.MrDetailAPI


@Module
@InstallIn(FragmentComponent::class)
class MrDetailModule {

    @Provides
    fun MrDetailsAPI(retrofit: Retrofit) = retrofit.create(MrDetailAPI::class.java)
}