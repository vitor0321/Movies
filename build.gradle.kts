plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinKsp) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.androidKotlinMultiplatformLibrary) apply false
    alias(libs.plugins.androidLint) apply false
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    mavenLocal()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
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
