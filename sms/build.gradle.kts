group = "com.sreMake.common"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":common"))
    implementation(starter.spring.boot.starter.web)
    implementation(utils.hutool.all)
    annotationProcessor(aptAndKsp.lombok)
    compileOnly(aptAndKsp.lombok)
    implementation("com.aliyun:dysmsapi20180501:1.0.10")
    // https://mvnrepository.com/artifact/com.tencentcloudapi/tencentcloud-sdk-java-sms
    implementation("com.tencentcloudapi:tencentcloud-sdk-java-sms:3.1.1179")
}
