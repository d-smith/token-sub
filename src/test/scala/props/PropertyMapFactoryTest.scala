package props

import org.scalatest.FunSuite


class PropertyMapFactoryTest extends FunSuite
{
  import PropertyMapFactory.propertyMapFromFile
  test("property file with 3 props contains 3 keys and value") {
    val propMap = propertyMapFromFile("src/test/resources/test.properties")
    assert( 3 === propMap.size)
    assert("foo val" === propMap.getOrElse("foo", "fail"))
  }
}