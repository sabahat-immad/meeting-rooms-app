package saba.qazi.meetingrooms.meetingroomslist.di

import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import saba.qazi.meetingrooms.meetingroomslist.MeetingRoomsListAPI

val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

@Module
@InstallIn(FragmentComponent::class)
class MeetingRoomList {

    private val BASE_URL = "https://ssgmobile.github.io/api/room/"
    @Provides
    fun meetingRoomsListApi(retrofit: Retrofit) : MeetingRoomsListAPI = retrofit.create(MeetingRoomsListAPI::class.java)

    @Provides
    fun retrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}