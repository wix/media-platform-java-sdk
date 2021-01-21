load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "io_jsonwebtoken_jjwt_api",
      artifact = "io.jsonwebtoken:jjwt-api:0.11.2",
      artifact_sha256 = "fa340e4c0b81f24c4c0f943c4454343efe9e055f648c600f2b3b637763cf6f28",
  )


  import_external(
      name = "io_jsonwebtoken_jjwt_impl",
      artifact = "io.jsonwebtoken:jjwt-impl:0.11.2",
      artifact_sha256 = "cf5896bdb086df7e7451ffde5f5691fb6ae7ec6bffa4e82071d3c5a426b11995",
      deps = [
          "@io_jsonwebtoken_jjwt_api"
      ],
  )


  import_external(
      name = "io_jsonwebtoken_jjwt_jackson",
      artifact = "io.jsonwebtoken:jjwt-jackson:0.11.2",
      artifact_sha256 = "6c200dcf0df3fa3c6ea31dab95a4154708d2c5d01dd1c4d7974fcfe651bee45d",
      deps = [
          "@com_fasterxml_jackson_core_jackson_databind",
          "@io_jsonwebtoken_jjwt_api"
      ],
  )
