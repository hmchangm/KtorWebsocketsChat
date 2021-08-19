plugins {
    kotlin("jvm") version "1.5.20"
}

group = "com.clluv.kt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktor_version: String by project

dependencies {
    // Client implementation of Ktor on top of coroutines ("Coroutine-based I/O").
    implementation("io.ktor:ktor-client-websockets:$ktor_version")
    // The counterpart to the ktor-websockets, to consume WebSockets from the client with the same API as the server
    implementation("io.ktor:ktor-client-cio:$ktor_version")
}