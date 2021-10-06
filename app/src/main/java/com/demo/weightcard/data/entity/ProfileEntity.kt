package com.demo.weightcard.data.entity

import com.demo.weightcard.logic.Units
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlin.random.Random

open class ProfileEntity(
    @PrimaryKey var id: Long,
    var weight: String = "",
    var units: String = Units.KG,
    var birthday: Long = 0,
    var face: String = "",
) : RealmObject() {
    constructor() : this(id = Random.nextLong())
}