load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_checkerframework_checker_qual",
      artifact = "org.checkerframework:checker-qual:2.11.1",
      artifact_sha256 = "015224a4b1dc6de6da053273d4da7d39cfea20e63038169fc45ac0d1dc9c5938",
  )
