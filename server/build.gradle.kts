plugins {
    kotlin("jvm") version "1.5.20"
}

group = "com.clluv.kt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktor_version: String by project
val logback_version: String by project

dependencies {
    // Ktor + Netty engine --> allow us to use server functionality without having to rely on an external app container
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    // WebSocket Ktor plugin --> the main communication mechanism for the chat
    implementation("io.ktor:ktor-websockets:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
}