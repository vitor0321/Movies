plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinKsp) apply false
    alias(libs.plugins.detekt) apply false
}

configurations.all {
    resolutionStrategy {
        force("io.ktor:ktor-client-core:3.0.0")
        force("io.ktor:ktor-client-okhttp:3.0.0")
        force("io.ktor:ktor-client-cio:3.0.0")
    }
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    afterEvaluate {
        configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
            config.setFrom(files("${rootProject.projectDir}/detekt.yml"))
            buildUponDefaultConfig = true
            autoCorrect = true
            source = files("src/")
            parallel = true
        }
    }
}
