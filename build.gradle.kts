plugins {
    `java-platform`
    `maven-publish`
}

group = "dev.nk7"
version = "1.0.0-SNAPSHOT"

repositories {
    maven(uri(properties["repo.releases.url"]!!))
}
publishing {
    repositories {
        maven {
            url = uri(
                if (version.toString().endsWith("SNAPSHOT")) {
                    project.properties["repo.snapshots.url"].toString()
                } else {
                    project.properties["repo.releases.url"].toString()
                }
            )
            credentials {
                username = project.properties["repo.username"].toString()
                password = project.properties["repo.password"].toString()
            }
        }

    }
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
    api(platform(libs.testcontainers))

    api(libs.hikari)
    api(libs.mysql)
    api(libs.postgresql)
    api(libs.sqlite)
    api(libs.liquibase)
    api(libs.kafka.clients)
    api(libs.kafka.stream)
    api(libs.slf4j)
    api(libs.logback.classic)
    api(libs.assertj)
}