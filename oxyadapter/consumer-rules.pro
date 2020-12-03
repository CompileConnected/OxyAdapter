
-keepparameternames
-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod

-keepclasseswithmembernames, includedescriptorclasses class * {
    native <methods>;
}
-keep,allowoptimization class com.yohanes.oxyadapter.* {public *;}
-renamesourcefileattribute SourceFile
