object Libs {
    // Android base
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout_version}"

    //Navigation
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"

    // ViewModel and LiveData
    const val lifeCycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val lifeCycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    const val lifeCycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
    const val lifecycleProcess =
        "androidx.lifecycle:lifecycle-process:${Versions.lifecycle_version}"

    // Dagger Hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val hiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofitConverterGson =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit_version}"

    //Room
    const val room = "androidx.room:room-runtime:${Versions.room_version}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"

    //Gson
    const val gson = "com.google.code.gson:gson:2.10.1"

    //Paging 3
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging_version}"

    //Coroutines
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"

    // Other Libs
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer_version}"

    object Test {
        const val junitTest = "junit:junit:${Versions.junit_version}"
        const val espressoCoreTest =
            "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
        const val extJunitTest = "androidx.test.ext:junit:${Versions.androidxJunit_version}"
    }
}
