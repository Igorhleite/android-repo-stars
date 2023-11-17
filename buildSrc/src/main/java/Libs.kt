object Libs {
    // Android base
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val material = "com.google.android.material:material:${Versions.material_version}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout_version}"

    object Test {
        const val junitTest = "junit:junit:${Versions.junit_version}"
        const val espressoCoreTest = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
        const val extJunitTest = "androidx.test.ext:junit:${Versions.androidxJunit_version}"
    }
}
