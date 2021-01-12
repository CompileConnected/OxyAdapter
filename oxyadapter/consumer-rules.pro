
-keepparameternames
-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod

-keepclasseswithmembernames, includedescriptorclasses class * {
    native <methods>;
}
-keep,allowoptimization class com.yohanes.oxyadapter.* {public *;}
-keep,allowoptimization class com.yohanes.oxyadapter.core.* {public *;}
-keep,allowoptimization class com.yohanes.oxyadapter.databinding.* {public *;}
-renamesourcefileattribute SourceFile
