load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "com_github_jknack_handlebars",
      artifact = "com.github.jknack:handlebars:4.0.7",
      artifact_sha256 = "d9b155fe8c8ddb0f9b3e5b156b5909dcd4c89d93defb2f72d0961aa633ad838f",
      deps = [
          "@org_antlr_antlr4_runtime",
          "@org_apache_commons_commons_lang3",
          "@org_slf4j_slf4j_api"
      ],
    # EXCLUDES org.mozilla:rhino
  )


  import_external(
      name = "com_github_jknack_handlebars_helpers",
      artifact = "com.github.jknack:handlebars-helpers:4.0.7",
      artifact_sha256 = "1001660f1e1d384df7c143a37e4ce3ba436baf4692f3fe4fd95cdb7ee9ab2a4b",
      deps = [
          "@com_github_jknack_handlebars"
      ],
    # EXCLUDES org.mozilla:rhino
  )
