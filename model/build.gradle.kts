group = "com.langbiantianya"
version = "0.0.1-SNAPSHOT"


dependencies {

    implementation(jimmer.sql)

    annotationProcessor(apt.jimmer)
    annotationProcessor(apt.lombok)

    compileOnly(apt.lombok)
}