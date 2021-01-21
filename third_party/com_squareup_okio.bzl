load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "com_squareup_okio_okio",
      artifact = "com.squareup.okio:okio:2.6.0",
      artifact_sha256 = "4d84ef686277b58eb05691ac19cd3befa3429a27274982ee65ea0f07044bcc00",
      deps = [
          "@org_jetbrains_kotlin_kotlin_stdlib",
          "@org_jetbrains_kotlin_kotlin_stdlib_common"
      ],
  )
