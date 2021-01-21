load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_hamcrest_hamcrest",
      artifact = "org.hamcrest:hamcrest:2.2",
      artifact_sha256 = "5e62846a89f05cd78cd9c1a553f340d002458380c320455dd1f8fc5497a8a1c1",
  )
