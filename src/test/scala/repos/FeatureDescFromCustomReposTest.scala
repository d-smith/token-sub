package repos

import org.scalatest.FunSuite


class FeatureDescFromCustomReposTest extends FunSuite {

  test("we can append repositories to the defaults") {
    val featuresDescs = FeatureDescsFromCustomRepos.apply(Array("src/test/resources/org.apache.karaf.features.txt",
      "mvn:foo", "mvn:bar","mvn:baz"))
    val updated = featuresDescs.addReposToRepolist()
    assert(updated.indexOf("mvn:foo") != -1)
  }
}
