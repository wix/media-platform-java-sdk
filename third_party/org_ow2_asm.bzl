load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_ow2_asm_asm",
      artifact = "org.ow2.asm:asm:7.0",
      artifact_sha256 = "b88ef66468b3c978ad0c97fd6e90979e56155b4ac69089ba7a44e9aa7ffe9acf",
  )
