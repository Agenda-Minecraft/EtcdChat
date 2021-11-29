import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.5.10"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "cat.kiwi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url="https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven(url="https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
    implementation("net.md-5:bungeecord-api:1.16-R0.5-SNAPSHOT")
    implementation("io.etcd:jetcd-core:0.6.1")
    implementation("com.google.code.gson:gson:2.8.9")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}