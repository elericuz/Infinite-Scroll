package com.example.android.infinitescroll.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Character(val info: Info, val results: List<Results>)

data class Info(val count: Int, val pages: Int, val next: String, val prev: String?)

@Parcelize
data class Results(val id: Int, val name: String, val gender: String, val image: String) : Parcelable
