rootProject.name = "nk7-platform"
dependencyResolutionManagement{
    versionCatalogs{
        create("libs"){
            from(files("libs.versions.toml"))
        }
    }
}

