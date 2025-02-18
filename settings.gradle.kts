pluginManagement {
    repositories {
        google() // Add the Google repository
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS) // Fail if repositories are defined at the project level
    repositories {
        google()  // Google repository for dependencies
        mavenCentral()
    }
}

rootProject.name = "Mekelle University Student Representative Election"
include(":app")




 