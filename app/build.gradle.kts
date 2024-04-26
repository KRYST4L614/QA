plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}


android {
    namespace = "com.example.randomuser"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.randomuser"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://randomuser.me/\"")
            buildConfigField("String", "DATABASE_NAME", "\"RandomUsers.db\"")
            buildConfigField("Integer", "PAGER_INITIAL_KEY", "1")
            buildConfigField("Integer", "PAGER_PREFETCH_DISTANCE", "5")
            buildConfigField("Integer", "PAGER_PAGE_SIZE", "15")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://randomuser.me/\"")
            buildConfigField("String", "DATABASE_NAME", "\"RandomUsers.db\"")
            buildConfigField("Integer", "PAGER_INITIAL_KEY", "1")
            buildConfigField("Integer", "PAGER_PREFETCH_DISTANCE", "5")
            buildConfigField("Integer", "PAGER_PAGE_SIZE", "15")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    viewBinding {
        enable = true
    }

    buildFeatures.buildConfig = true
}



dependencies {
    val roomVersion = "2.6.1"
    val moshiVersion = "1.14.0"
    val pagingVersion = "3.2.1"
    implementation("androidx.paging:paging-common-ktx:$pagingVersion")
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")
    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.dagger:dagger:2.51")
    ksp("com.google.dagger:dagger-compiler:2.51")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    //noinspection GradleDependency
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    //noinspection GradleDependency
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    androidTestImplementation("com.kaspersky.android-components:kaspresso:1.5.2")
}