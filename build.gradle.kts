plugins {
  `version-catalog`
  `maven-publish`
}

group = "dev.nk7"
version = "1.0.0-SNAPSHOT"

repositories {
  maven(uri(properties["repo.releases.url"].toString()))
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
    create<MavenPublication>("nk7-versions-catalog") {
      from(components["versionCatalog"])
    }
  }
}

catalog {
  versionCatalog {
    from(files("libs.versions.toml"))
  }
}
