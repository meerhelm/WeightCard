package com.demo.weightcard

import android.content.Context
import com.demo.weightcard.data.repository.ProfileRepository
import com.demo.weightcard.data.repository.RegistrationRepository
import com.demo.weightcard.logic.interactor.RegistrationInteractor
import com.demo.weightcard.logic.registration_flow.RegistrationStepProvider
import com.demo.weightcard.logic.validation.EmptyFieldValidator
import com.demo.weightcard.ui.registartion.birthday.ScreenBirthdayViewModel
import com.demo.weightcard.ui.registartion.list.ScreenRegistrationsListViewModel
import com.demo.weightcard.ui.registartion.photo.ScreenPhotoViewModel
import com.demo.weightcard.ui.registartion.registration.RegistrationViewModel
import com.demo.weightcard.ui.registartion.weight.ScreenWeightViewModel
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { RegistrationViewModel(get()) }
    factory { ProfileRepository(get()) }
    single {
        Realm.init(get())
        Realm.getInstance(RealmConfiguration.Builder().build())
    }
    viewModel { ScreenRegistrationsListViewModel(get()) }
    viewModel { ScreenWeightViewModel(get()) }
    viewModel { ScreenBirthdayViewModel(get()) }
    viewModel { ScreenPhotoViewModel(get()) }

    factory { RegistrationRepository(get(), get()) }
    factory { RegistrationStepProvider() }
    factory { RegistrationInteractor(get()) }

    factory { get<Context>().getSharedPreferences("weight_card_prefs", Context.MODE_PRIVATE) }
    factory { EmptyFieldValidator<String>(get()) }
}