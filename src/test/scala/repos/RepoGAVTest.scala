package repos

import org.scalatest.FunSuite


class RepoGAVTest extends FunSuite {
  import RepoGAV.xmlpath2GAV

  test("an xml artifact path is translated to a GAV") {
    val path = "./sample-repo/xtracsolutions/sample-feature/1.0-SNAPSHOT/sample-feature-1.0-SNAPSHOT-features.xml"
    val gav = xmlpath2GAV(path)
    assert ( "mvn:xtracsolutions/sample-feature/1.0-SNAPSHOT/xml/features" === gav)
  }
}
