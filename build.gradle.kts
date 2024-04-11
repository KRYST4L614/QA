plugins {
    id("java")
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val junitVersion = "5.9.1"
    val selenideVersion = "7.2.2"
    val slf4jVersion = "2.0.12"
    implementation(platform("org.junit:junit-bom:$junitVersion"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation("com.codeborne:selenide:$selenideVersion")
    runtimeOnly("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(19)
}