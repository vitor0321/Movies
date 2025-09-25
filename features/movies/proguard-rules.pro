# Add project specific ProGuard rules here.

# Ktor
-keep class io.ktor.** { *; }
-keep class kotlinx.coroutines.** { *; }
-keepclassmembers class io.ktor.** { *; }
-dontwarn kotlinx.atomicfu.**
-dontwarn io.netty.**
-dontwarn com.typesafe.**

# Kotlin Serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.walcker.movies.**$$serializer { *; }
-keepclassmembers class com.walcker.movies.** {
    *** Companion;
}
-keepclasseswithmembers class com.walcker.movies.** {
    kotlinx.serialization.KSerializer serializer(...);
}
# Ignorar classes de gerenciamento Java que não existem no Android
-dontwarn java.lang.management.**
-dontwarn javax.management.**
-dontwarn io.ktor.util.debug.IntellijIdeaDebugDetector