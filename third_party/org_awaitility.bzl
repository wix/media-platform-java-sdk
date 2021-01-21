load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_awaitility_awaitility",
      artifact = "org.awaitility:awaitility:4.0.3",
      artifact_sha256 = "1bc242420f3492b5b8c68f148e4e4101f6ea825527ce793be22c0c6ffa3b5121",
    # EXCLUDES org.hamcrest:hamcrest
  )
