
-keepparameternames
-renamesourcefileattribute SourceFile
-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod

-keepclasseswithmembernames, includedescriptorclasses class * {
    native <methods>;
}