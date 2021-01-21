load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "com_github_tomakehurst_wiremock",
      artifact = "com.github.tomakehurst:wiremock:2.26.0",
      artifact_sha256 = "fad7734b371652d33d37920d8388f752a0f664f2ccd30c3d5ffc6e60b8b1da69",
      deps = [
          "@com_fasterxml_jackson_core_jackson_annotations",
          "@com_fasterxml_jackson_core_jackson_core",
          "@com_fasterxml_jackson_core_jackson_databind",
          "@com_flipkart_zjsonpatch_zjsonpatch",
          "@com_github_jknack_handlebars",
          "@com_github_jknack_handlebars_helpers",
          "@com_google_guava_guava",
          "@com_jayway_jsonpath_json_path",
          "@commons_fileupload_commons_fileupload",
          "@net_sf_jopt_simple_jopt_simple",
          "@org_apache_commons_commons_lang3",
          "@org_apache_httpcomponents_httpclient",
          "@org_eclipse_jetty_jetty_server",
          "@org_eclipse_jetty_jetty_servlet",
          "@org_eclipse_jetty_jetty_servlets",
          "@org_eclipse_jetty_jetty_webapp",
          "@org_ow2_asm_asm",
          "@org_slf4j_slf4j_api",
          "@org_xmlunit_xmlunit_core",
          "@org_xmlunit_xmlunit_legacy",
          "@org_xmlunit_xmlunit_placeholders"
      ],
  )
