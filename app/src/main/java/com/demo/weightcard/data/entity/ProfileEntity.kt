package com.demo.weightcard.data.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlin.random.Random

open class ProfileEntity(
    @PrimaryKey var id: Long,
    var weight: String? = null,
    var birthday: String? = null,
    var face: String? = null,
) : RealmObject() {
    constructor() : this(id = Random.nextLong())
}