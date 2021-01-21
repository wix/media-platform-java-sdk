load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_jetbrains_annotations",
      artifact = "org.jetbrains:annotations:13.0",
      artifact_sha256 = "ace2a10dc8e2d5fd34925ecac03e4988b2c0f851650c94b8cef49ba1bd111478",
  )


  import_external(
      name = "org_jetbrains_annotations_java5",
      artifact = "org.jetbrains:annotations-java5:19.0.0",
      artifact_sha256 = "647e9caad03518038fe9f6d8ecf6103cbb2e5919a3e316a8126c6caebf44f5ef",
  )
