plugins {
  `maven-publish`
  `java-platform`
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
    create<MavenPublication>("platform") {
      from(components["javaPlatform"])
    }
  }
}


javaPlatform {
  allowDependencies()
}

dependencies {
  api(platform(libs.bom.kotlin))
  api(platform(libs.bom.spring.boot))
  api(platform(libs.bom.spring.cloud))
  api(platform(libs.bom.exposed))
  api(platform(libs.bom.testcontainers))
  api(platform(libs.bom.junit))
  api(platform(libs.bom.opentelemetry))

  constraints {
    api(libs.slf4j)
    api(libs.logback.classic)

    api(libs.postgresql)
    api(libs.mysql)
    api(libs.sqlite)
    api(libs.liquibase)

    api(libs.kafka.clients)
    api(libs.kafka.stream)
    api(libs.bundles.commons.rng)
  }
}
