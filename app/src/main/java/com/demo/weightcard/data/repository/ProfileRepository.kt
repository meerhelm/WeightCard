package com.demo.weightcard.data.repository

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.net.Uri
import com.demo.weightcard.data.entity.ProfileEntity
import com.demo.weightcard.model.Profile
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*


class ProfileRepository(
    private val realm: Realm,
    private val context: Context
) {
    suspend fun writeProfile(profile: Profile) {
        realm.executeTransactionAwait {
            val profileEntity = ProfileEntity(
                profile.id,
                profile.weight,
                profile.birthday,
                profile.face
            )
            it.insert(profileEntity)
        }
    }



    suspend fun getProfile(profileId: Long): Profile? = withContext(Dispatchers.Main) {
        realm.where(ProfileEntity::class.java)?.equalTo("id", profileId)
            ?.findFirst()?.let { mapEntityToProfile(it) }
    }

    suspend fun getProfiles(): List<Profile> = withContext(Dispatchers.Main) {
        realm.where(ProfileEntity::class.java)?.findAll()?.mapNotNull { mapEntityToProfile(it) }!!
            .toList()
    }

    private fun mapEntityToProfile(profileEntity: ProfileEntity): Profile {
        return with(profileEntity) {
            Profile(
                id,
                weight,
                birthday,
                units,
                face
            )
        }
    }

    private fun readBitmapFromFile(face: String?): Bitmap? {
        TODO("Not yet implemented")
    }

    suspend fun removeProfile(profile: Profile) {
        realm.executeTransactionAwait {
            realm.where(ProfileEntity::class.java).equalTo("id", profile.id).findFirst()
                ?.deleteFromRealm()
        }
    }

    suspend fun filterProfiles(searchString: String): List<Profile> =
        withContext(Dispatchers.Main) {
            realm.where(ProfileEntity::class.java)?.findAll()
                ?.filter { it.weight.contains(searchString, true) }
                ?.mapNotNull { mapEntityToProfile(it) }!!.toList()
        }

    suspend fun removeProfilesById(id: Long) {
        realm.executeTransactionAwait {
            realm.where(ProfileEntity::class.java).equalTo("id", id).findFirst()
                ?.deleteFromRealm()
        }
    }

}