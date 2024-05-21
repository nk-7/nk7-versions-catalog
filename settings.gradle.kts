rootProject.name = "nk7-platform"
include("catalog", "bom")

dependencyResolutionManagement {
  versionCatalogs {
    create("libs") {
      from(files("catalog/libs.versions.toml"))
    }
  }
}
