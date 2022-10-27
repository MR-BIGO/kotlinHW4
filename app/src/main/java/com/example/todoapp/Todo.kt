package com.example.todoapp

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Todo(val name: String?, val comment: String?) : Parcelable
