package com.example.android.infinitescroll.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Character(val results: List<CharacterData>)

data class Info(val count: Int, val pages: Int, val next: String, val prev: String?)

@Parcelize
data class CharacterData(val id: Int, val name: String, val gender: String, val image: String) : Parcelable
