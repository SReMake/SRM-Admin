group = "com.sreMake"
version = "0.0.1-SNAPSHOT"


dependencies {
    implementation(project(":common"))
    implementation(starter.spring.boot.starter.quartz)
    implementation(starter.spring.boot.starter.data.jdbc)
    implementation(starter.spring.boot.starter.web)

}