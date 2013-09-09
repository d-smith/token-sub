package feature

import props.PropertyMapFactory._
import org.scalatest.FunSuite


class FeatureTokenReplacementTest extends FunSuite
{
  import feature.FeatureTokenReplacement.replaceTokenizedParamsFromMap
  test("config sections can have properties replaced") {
    val propMap = propertyMapFromFile("src/test/resources/sample.properties")
    val updated = replaceTokenizedParamsFromMap(propMap, "src/test/resources/features.xml")
    assert(updated.contains("brokerURL  = vm://amq") === true)
  }
}