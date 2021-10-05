package com.demo.weightcard.utils

import io.realm.RealmList

inline fun <reified T> List<T>.toRealmList(): RealmList<T> {
    return RealmList(*this.toTypedArray())
}