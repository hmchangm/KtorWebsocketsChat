plugins {
    kotlin("jvm") version "1.5.20"
}

group = "com.clluv.kt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktorVersion: String by project
val logbackVersion: String by project
val arrowVersion: String by project

dependencies {
    // Ktor + Netty engine --> allow us to use server functionality without having to rely on an external app container
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    // WebSocket Ktor plugin --> the main communication mechanism for the chat
    implementation("io.ktor:ktor-websockets:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.arrow-kt:arrow-core:$arrowVersion")
    implementation("com.squareup.okhttp3:okhttp:3.8.1")
    implementation("com.beust:klaxon:5.5")
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}