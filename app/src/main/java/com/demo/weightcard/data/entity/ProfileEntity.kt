package com.demo.weightcard.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlin.random.Random

open class ProfileEntity(
    @PrimaryKey var id: Long,
    var weight: String = "",
    var birthday: String = "",
    var face: String = "",
) : RealmObject() {
    constructor() : this(id = Random.nextLong())
}