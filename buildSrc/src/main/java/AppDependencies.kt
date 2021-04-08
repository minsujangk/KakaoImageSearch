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

    // hilt
    val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    // retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"


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
    }

    val testImplementations = arrayListOf<String>().apply {
        add(junit)
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