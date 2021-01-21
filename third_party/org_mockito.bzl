load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_mockito_mockito_core",
      artifact = "org.mockito:mockito-core:3.3.3",
      artifact_sha256 = "4be648c50456fba4686ba825000d628c1d805a3b92272ba9ad5b697dfa43036b",
      deps = [
          "@net_bytebuddy_byte_buddy",
          "@net_bytebuddy_byte_buddy_agent",
          "@org_objenesis_objenesis"
      ],
    # EXCLUDES org.hamcrest:hamcrest-core
  )


  import_external(
      name = "org_mockito_mockito_junit_jupiter",
      artifact = "org.mockito:mockito-junit-jupiter:3.3.3",
      artifact_sha256 = "21f3978ae8d993b46ade8f422df46b60add30c700f701747915574814b228ce3",
      deps = [
          "@org_mockito_mockito_core"
      ],
      runtime_deps = [
          "@org_junit_jupiter_junit_jupiter_api"
      ],
  )
