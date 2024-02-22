import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("maven-publish")
}

group = "org.teleight"
version = "1.0"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    //Tests
    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    //Checker
    implementation("org.checkerframework:checker:3.39.0")
    implementation("org.checkerframework:checker-qual:3.39.0")

    //Lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    //Json
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")

    //Annotations
    implementation("org.jetbrains:annotations:24.0.1")
}

tasks.test {
    useJUnitPlatform()
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    from(sourceSets.getByName("main").allSource)

    archiveFileName.set("TeleightBots-javadoc.jar")
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)

    archiveFileName.set("TeleightBots-sources.jar")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "TeleightBots"

            artifact(javadocJar)
            artifact(sourcesJar)

            project.shadow.component(this)
        }
    }
}

tasks.withType<ShadowJar> {

    archiveClassifier.set("")
    archiveVersion.set("")

    archiveFileName.set("TeleightBots.jar")
}
