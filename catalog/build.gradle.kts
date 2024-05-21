plugins {
  `version-catalog`
  `maven-publish`
}


catalog {
  versionCatalog {
    from(files("libs.versions.toml"))
  }
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
    create<MavenPublication>("versions-catalog") {
      from(components["versionCatalog"])
    }
  }
}

