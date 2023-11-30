object Libs {
    // Android base
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout_version}"

    // Compose
    const val composeBom = "androidx.compose:compose-bom:${Versions.compose_bom_version}"

    const val androidXCompose = "androidx.compose.runtime:runtime"
    const val androidXComposeUi = "androidx.compose.ui:ui"
    const val androidXComposeFoundation = "androidx.compose.foundation:foundation"
    const val androidXComposeFoundationLayout = "androidx.compose.foundation:foundation-layout"
    const val androidXComposeMaterial = "androidx.compose.material:material"
    const val androidXComposeLiveData = "androidx.compose.runtime:runtime-livedata"
    const val androidXComposeUiTooling = "androidx.compose.ui:ui-tooling"

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

    // OkHttp
    const val okhttpBom = "com.squareup.okhttp3:okhttp-bom:${Versions.okhttp_bom_version}"
    const val okhttp = "com.squareup.okhttp3:okhttp"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor"

    //Room
    const val room = "androidx.room:room-runtime:${Versions.room_version}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"

    //Gson
    const val gson = "com.google.code.gson:gson:${Versions.gson_version}"

    //Paging 3
    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging_version}"

    //Coroutines
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"

    // Other Libs
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer_version}"
    const val coil = "io.coil-kt:coil:${Versions.coil_version}"


    // Unit tests
    const val junit = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit_version}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito_version}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.core_testing_version}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_test_version}"
    const val mockitoKotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin_version}"

    // Instrumentation tests
    const val extJunit = "androidx.test.ext:junit:${Versions.ext_junit_version}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"

}
