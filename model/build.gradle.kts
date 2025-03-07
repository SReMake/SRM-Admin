group = "com.SReMake"
version = "0.0.1-SNAPSHOT"


dependencies {
    implementation(starter.spring.boot.starter.security)
//    implementation(jimmer.sql)
    implementation(jimmer.sql.kotlin)
//    annotationProcessor(aptAndKsp.jimmer)
//    annotationProcessor(aptAndKsp.lombok)
//    compileOnly(aptAndKsp.lombok)
    ksp(aptAndKsp.jimmer.ksp)
}