package com.harismexis.cosmos.workshared.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.cosmos.apod.viewmodel.APODVm
import com.harismexis.cosmos.home.viewmodel.HomeVm
import com.harismexis.cosmos.mediaplayer.MediaPlayerVm
import com.harismexis.cosmos.mrp.viewmodel.MRPVm
import com.harismexis.cosmos.niavl.viewmodel.NIAVLVm
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(APODVm::class)
    internal abstract fun provideAPODVm(viewModel: APODVm): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeVm::class)
    internal abstract fun provideHomeVm(viewModel: HomeVm): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MRPVm::class)
    internal abstract fun provideMRPVm(viewModel: MRPVm): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NIAVLVm::class)
    internal abstract fun provideNIAVLVm(viewModel: NIAVLVm): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MediaPlayerVm::class)
    internal abstract fun provideMediaPlayerVm(viewModel: MediaPlayerVm): ViewModel
}