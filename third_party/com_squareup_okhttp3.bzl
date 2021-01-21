load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "com_squareup_okhttp3_okhttp",
      artifact = "com.squareup.okhttp3:okhttp:4.7.2",
      artifact_sha256 = "6b64ca1d2069751242984537d5de2cbe627ae000430f5117c47d8cb3d272facd",
      deps = [
          "@com_squareup_okio_okio",
          "@org_jetbrains_kotlin_kotlin_stdlib"
      ],
  )
