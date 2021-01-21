load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_eclipse_jetty_jetty_continuation",
      artifact = "org.eclipse.jetty:jetty-continuation:9.2.28.v20190418",
      artifact_sha256 = "593dcc53c1f62f761bdb5f2982aa455d81d111f7e20a6ba9a0cac1003fb38b61",
  )


  import_external(
      name = "org_eclipse_jetty_jetty_http",
      artifact = "org.eclipse.jetty:jetty-http:9.2.28.v20190418",
      artifact_sha256 = "b5ccf94d576d5e488587ffc76d215efa4a91a25b88c732b632ab784adafe81d5",
      deps = [
          "@org_eclipse_jetty_jetty_util"
      ],
  )


  import_external(
      name = "org_eclipse_jetty_jetty_io",
      artifact = "org.eclipse.jetty:jetty-io:9.2.28.v20190418",
      artifact_sha256 = "4bc9dbdc342937d24dcc7ace723e16ce0409ef5d2f88dc47ba7b3d35cc630ffa",
      deps = [
          "@org_eclipse_jetty_jetty_util"
      ],
  )


  import_external(
      name = "org_eclipse_jetty_jetty_security",
      artifact = "org.eclipse.jetty:jetty-security:9.2.28.v20190418",
      artifact_sha256 = "e55f63c3c9920c4d8a0eb192e066fc26cf45843ead52cc25c2d21ebaa6bdcb7f",
      deps = [
          "@org_eclipse_jetty_jetty_server"
      ],
  )


  import_external(
      name = "org_eclipse_jetty_jetty_server",
      artifact = "org.eclipse.jetty:jetty-server:9.2.28.v20190418",
      artifact_sha256 = "fc48ec1a68bb42200de242ce4aa35a0deb2531ea6695169732cf5b9eaf8b3f96",
      deps = [
          "@javax_servlet_javax_servlet_api//:linkable",
          "@org_eclipse_jetty_jetty_http",
          "@org_eclipse_jetty_jetty_io"
      ],
  )


  import_external(
      name = "org_eclipse_jetty_jetty_servlet",
      artifact = "org.eclipse.jetty:jetty-servlet:9.2.28.v20190418",
      artifact_sha256 = "b58fa73981dd32d7a63cdc3b3b7eb89d9aba1f7c33e5424d575eab127f1e851c",
      deps = [
          "@org_eclipse_jetty_jetty_security"
      ],
  )


  import_external(
      name = "org_eclipse_jetty_jetty_servlets",
      artifact = "org.eclipse.jetty:jetty-servlets:9.2.28.v20190418",
      artifact_sha256 = "e76ebb433f47fb324704410d3ecb414446b03e213c21ec9039765a109c88479f",
      deps = [
          "@org_eclipse_jetty_jetty_continuation",
          "@org_eclipse_jetty_jetty_http",
          "@org_eclipse_jetty_jetty_io",
          "@org_eclipse_jetty_jetty_util"
      ],
  )


  import_external(
      name = "org_eclipse_jetty_jetty_util",
      artifact = "org.eclipse.jetty:jetty-util:9.2.28.v20190418",
      artifact_sha256 = "bdb2aee14bd9bc855c51cda78071064b1077770de2b228338f00f91f9cb1de44",
  )


  import_external(
      name = "org_eclipse_jetty_jetty_webapp",
      artifact = "org.eclipse.jetty:jetty-webapp:9.2.28.v20190418",
      artifact_sha256 = "a7126946497227a93d86f2dcff727ededadfb424fa8d1bc6b47d6b55b6c41544",
      deps = [
          "@org_eclipse_jetty_jetty_servlet",
          "@org_eclipse_jetty_jetty_xml"
      ],
  )


  import_external(
      name = "org_eclipse_jetty_jetty_xml",
      artifact = "org.eclipse.jetty:jetty-xml:9.2.28.v20190418",
      artifact_sha256 = "27e53eaec5e548d3016e48bb3a4fc02bf1c230c8918d57d62d599bd2b67c560c",
      deps = [
          "@org_eclipse_jetty_jetty_util"
      ],
  )
