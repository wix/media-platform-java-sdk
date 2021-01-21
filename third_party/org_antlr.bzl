load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_antlr_antlr4_runtime",
      artifact = "org.antlr:antlr4-runtime:4.7.1",
      artifact_sha256 = "43516d19beae35909e04d06af6c0c58c17bc94e0070c85e8dc9929ca640dc91d",
    # EXCLUDES org.abego.treelayout:org.abego.treelayout.core
  )
