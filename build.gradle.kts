plugins {
    `java-platform`
    `maven-publish`
}

group = "dev.nk7"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}
publishing {
    publications {
        create<MavenPublication>("nk7-platform") {
            from(components["javaPlatform"])
        }
    }
}

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform(libs.kotlin))
    api(platform(libs.spring.boot))
    api(platform(libs.spring.cloud))
    api(platform(libs.spring.statemachine))
    api(platform(libs.ktor))
    api(platform(libs.exposed))
    api(platform(libs.junit))

    api(libs.hikari)
    api(libs.mysql)
    api(libs.sqlite)
    api(libs.liquibase)
    api(libs.kafka.clients)
    api(libs.kafka.stream)
    api(libs.postgresql)

    api(libs.assertj)
}