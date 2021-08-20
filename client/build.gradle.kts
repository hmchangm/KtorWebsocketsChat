plugins {
    kotlin("jvm") version "1.5.20"
}

group = "com.clluv.kt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val kotlinVersion: String by project
val ktorVersion: String by project
val arrowVersion: String by project

dependencies {
    // Client implementation of Ktor on top of coroutines ("Coroutine-based I/O").
    implementation("io.ktor:ktor-client-websockets:$ktorVersion")
    // The counterpart to the ktor-websockets, to consume WebSockets from the client with the same API as the server
    implementation("io.ktor:ktor-client-cio:$ktorVersion")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    implementation("io.arrow-kt:arrow-core:$arrowVersion")
}