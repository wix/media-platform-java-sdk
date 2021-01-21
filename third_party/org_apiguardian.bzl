load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_apiguardian_apiguardian_api",
      artifact = "org.apiguardian:apiguardian-api:1.1.0",
      artifact_sha256 = "a9aae9ff8ae3e17a2a18f79175e82b16267c246fbbd3ca9dfbbb290b08dcfdd4",
  )
