load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_slf4j_slf4j_api",
      artifact = "org.slf4j:slf4j-api:1.7.12",
      artifact_sha256 = "0aee9a77a4940d72932b0d0d9557793f872e66a03f598e473f45e7efecdccf99",
  )
