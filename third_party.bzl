load("//:third_party/org_xmlunit.bzl", org_xmlunit_deps = "dependencies")

load("//:third_party/org_slf4j.bzl", org_slf4j_deps = "dependencies")

load("//:third_party/org_ow2_asm.bzl", org_ow2_asm_deps = "dependencies")

load("//:third_party/org_opentest4j.bzl", org_opentest4j_deps = "dependencies")

load("//:third_party/org_objenesis.bzl", org_objenesis_deps = "dependencies")

load("//:third_party/org_mockito.bzl", org_mockito_deps = "dependencies")

load("//:third_party/org_junit_platform.bzl", org_junit_platform_deps = "dependencies")

load("//:third_party/org_junit_jupiter.bzl", org_junit_jupiter_deps = "dependencies")

load("//:third_party/org_jetbrains_kotlin.bzl", org_jetbrains_kotlin_deps = "dependencies")

load("//:third_party/org_jetbrains.bzl", org_jetbrains_deps = "dependencies")

load("//:third_party/org_hamcrest.bzl", org_hamcrest_deps = "dependencies")

load("//:third_party/org_eclipse_jetty.bzl", org_eclipse_jetty_deps = "dependencies")

load("//:third_party/org_checkerframework.bzl", org_checkerframework_deps = "dependencies")

load("//:third_party/org_awaitility.bzl", org_awaitility_deps = "dependencies")

load("//:third_party/org_apiguardian.bzl", org_apiguardian_deps = "dependencies")

load("//:third_party/org_apache_httpcomponents.bzl", org_apache_httpcomponents_deps = "dependencies")

load("//:third_party/org_apache_commons.bzl", org_apache_commons_deps = "dependencies")

load("//:third_party/org_antlr.bzl", org_antlr_deps = "dependencies")

load("//:third_party/net_sf_jopt_simple.bzl", net_sf_jopt_simple_deps = "dependencies")

load("//:third_party/net_minidev.bzl", net_minidev_deps = "dependencies")

load("//:third_party/net_bytebuddy.bzl", net_bytebuddy_deps = "dependencies")

load("//:third_party/javax_xml_bind.bzl", javax_xml_bind_deps = "dependencies")

load("//:third_party/javax_servlet.bzl", javax_servlet_deps = "dependencies")

load("//:third_party/io_jsonwebtoken.bzl", io_jsonwebtoken_deps = "dependencies")

load("//:third_party/commons_logging.bzl", commons_logging_deps = "dependencies")

load("//:third_party/commons_io.bzl", commons_io_deps = "dependencies")

load("//:third_party/commons_fileupload.bzl", commons_fileupload_deps = "dependencies")

load("//:third_party/commons_codec.bzl", commons_codec_deps = "dependencies")

load("//:third_party/com_squareup_okio.bzl", com_squareup_okio_deps = "dependencies")

load("//:third_party/com_squareup_okhttp3.bzl", com_squareup_okhttp3_deps = "dependencies")

load("//:third_party/com_jayway_jsonpath.bzl", com_jayway_jsonpath_deps = "dependencies")

load("//:third_party/com_google_j2objc.bzl", com_google_j2objc_deps = "dependencies")

load("//:third_party/com_google_guava.bzl", com_google_guava_deps = "dependencies")

load("//:third_party/com_google_errorprone.bzl", com_google_errorprone_deps = "dependencies")

load("//:third_party/com_google_code_findbugs.bzl", com_google_code_findbugs_deps = "dependencies")

load("//:third_party/com_github_tomakehurst.bzl", com_github_tomakehurst_deps = "dependencies")

load("//:third_party/com_github_jknack.bzl", com_github_jknack_deps = "dependencies")

load("//:third_party/com_flipkart_zjsonpatch.bzl", com_flipkart_zjsonpatch_deps = "dependencies")

load("//:third_party/com_fasterxml_jackson_core.bzl", com_fasterxml_jackson_core_deps = "dependencies")


load("//:macros.bzl", "maven_archive", "maven_proto")

def third_party_dependencies():
      

  com_fasterxml_jackson_core_deps()


  com_flipkart_zjsonpatch_deps()


  com_github_jknack_deps()


  com_github_tomakehurst_deps()


  com_google_code_findbugs_deps()


  com_google_errorprone_deps()


  com_google_guava_deps()


  com_google_j2objc_deps()


  com_jayway_jsonpath_deps()


  com_squareup_okhttp3_deps()


  com_squareup_okio_deps()


  commons_codec_deps()


  commons_fileupload_deps()


  commons_io_deps()


  commons_logging_deps()


  io_jsonwebtoken_deps()


  javax_servlet_deps()


  javax_xml_bind_deps()


  net_bytebuddy_deps()


  net_minidev_deps()


  net_sf_jopt_simple_deps()


  org_antlr_deps()


  org_apache_commons_deps()


  org_apache_httpcomponents_deps()


  org_apiguardian_deps()


  org_awaitility_deps()


  org_checkerframework_deps()


  org_eclipse_jetty_deps()


  org_hamcrest_deps()


  org_jetbrains_deps()


  org_jetbrains_kotlin_deps()


  org_junit_jupiter_deps()


  org_junit_platform_deps()


  org_mockito_deps()


  org_objenesis_deps()


  org_opentest4j_deps()


  org_ow2_asm_deps()


  org_slf4j_deps()


  org_xmlunit_deps()
