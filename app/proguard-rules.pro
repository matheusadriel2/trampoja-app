-keepattributes Signature
-keepattributes *Annotation*

-keep class retrofit2.** { *; }
-dontwarn retrofit2.**
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-keep class okio.** { *; }
-dontwarn okio.**

-keep class com.squareup.moshi.** { *; }
-dontwarn com.squareup.moshi.**
-keep class kotlin.Metadata { *; }

-dontwarn kotlin.reflect.**
-keep class kotlin.reflect.** { *; }

-keep class br.com.fiap.trampoja.data.remote.model.** { *; }
-keep class br.com.fiap.trampoja.di.AdzunaJob { *; }
-keep class br.com.fiap.trampoja.di.AdzunaSearchResponse { *; }

-keepclasseswithmembers class * {
    @com.squareup.moshi.* <fields>;
    @com.squareup.moshi.* <methods>;
}
