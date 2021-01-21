load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_junit_platform_junit_platform_commons",
      artifact = "org.junit.platform:junit-platform-commons:1.6.1",
      artifact_sha256 = "f849803127a4436e1e3ec232f158c7609c0848b0b7a8631fcb7fe7fb33636343",
      deps = [
          "@org_apiguardian_apiguardian_api"
      ],
  )


  import_external(
      name = "org_junit_platform_junit_platform_engine",
      artifact = "org.junit.platform:junit-platform-engine:1.6.1",
      artifact_sha256 = "a00d54d1ac3ddeffea1fea43f6790b689c7f1848a7ba21d7263ff8d82dc949f0",
      deps = [
          "@org_apiguardian_apiguardian_api",
          "@org_junit_platform_junit_platform_commons",
          "@org_opentest4j_opentest4j"
      ],
  )
