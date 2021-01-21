load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_objenesis_objenesis",
      artifact = "org.objenesis:objenesis:2.6",
      artifact_sha256 = "5e168368fbc250af3c79aa5fef0c3467a2d64e5a7bd74005f25d8399aeb0708d",
  )
