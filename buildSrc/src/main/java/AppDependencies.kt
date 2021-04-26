import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * reference: https://medium.com/android-dev-hacks/kotlin-dsl-gradle-scripts-in-android-made-easy-b8e2991e2ba
 */
object AppDependencies {
    // kotlin
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    // android essentials
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val material = "com.google.android.material:material:${Versions.material}"
    val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtx}"
    val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveDataKtx}"
    val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

    // android ui
    val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // test
    val junit = "junit:junit:${Versions.junit}"
    val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val robolectric = "androidx.test:core:${Versions.robolectric}"
    val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"


    // hilt
    val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    // retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpInterceptor}"

    // logging
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // paging
    val pagingRumtime = "androidx.paging:paging-runtime:${Versions.paging}"

    // alternatively - without Android dependencies for tests
    val pagingCommon = "androidx.paging:paging-common:${Versions.paging}"

    // glide
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    // loupe
    val loupe = "com.igreenwood.loupe:loupe:${Versions.loupe}"

    // photoView
    val photoView = "com.github.chrisbanes:PhotoView:${Versions.photoView}"

    val implementations = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
        add(material)
        add(hilt)
        add(constraintLayout)
        add(viewmodelKtx)
        add(liveDataKtx)
        add(fragmentKtx)
        add(retrofit)
        add(retrofitGson)
        add(okhttpInterceptor)
        add(timber)
        add(pagingRumtime)
        add(glide)
        add(loupe)
        add(photoView)
    }

    val testImplementations = arrayListOf<String>().apply {
        add(junit)
        add(pagingCommon)
        add(mockito)
        add(robolectric)
        add(mockitoKotlin)
    }

    val androidTestImplementations = arrayListOf<String>().apply {
        add(extJunit)
        add(espressoCore)
    }

    val kapts = arrayListOf<String>().apply {
        add(hiltCompiler)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}