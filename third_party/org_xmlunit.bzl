load("//:import_external.bzl", import_external = "safe_exodus_maven_import_external")

def dependencies():

  import_external(
      name = "org_xmlunit_xmlunit_core",
      artifact = "org.xmlunit:xmlunit-core:2.6.2",
      artifact_sha256 = "4f0e407dc9eb19582d74b9bcbeeef5117ccae42ebc4dd589db2da506a7c2e17d",
      deps = [
          "@javax_xml_bind_jaxb_api"
      ],
  )


  import_external(
      name = "org_xmlunit_xmlunit_legacy",
      artifact = "org.xmlunit:xmlunit-legacy:2.6.2",
      artifact_sha256 = "25359297aae5bae13167b1794ece3148caf4007f232afe4debf7540a758c5c14",
      deps = [
          "@org_xmlunit_xmlunit_core"
      ],
    # EXCLUDES junit:junit
  )


  import_external(
      name = "org_xmlunit_xmlunit_placeholders",
      artifact = "org.xmlunit:xmlunit-placeholders:2.6.2",
      artifact_sha256 = "b594a3c2c681fd0d2f407040b1af55153ebfbd146fb88fb7e930797de11e5733",
      deps = [
          "@org_xmlunit_xmlunit_core"
      ],
  )
