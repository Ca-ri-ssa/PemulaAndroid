package com.carissa.pemulaandroid

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal(
    val name_animal: String,
    val desc_animal: String,
    val img_animal: Int,
    val extra_info: String,
    val tag_animal: List<String>,
    val class_animal: String,
    val order_animal: String,
    val family_animal: String
) : Parcelable