load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "com_flipkart_zjsonpatch_zjsonpatch",
      artifact = "com.flipkart.zjsonpatch:zjsonpatch:0.4.4",
      artifact_sha256 = "ab9daea7f2def33b12063b257dad9274fac45034d0e3da1e2e0be9d2a4b4a6c7",
    # EXCLUDES com.fasterxml.jackson.core:jackson-core
    # EXCLUDES com.fasterxml.jackson.core:jackson-databind
  )
