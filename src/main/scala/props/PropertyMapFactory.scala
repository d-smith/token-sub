package props

import collection.JavaConversions._
import collection.mutable.HashMap
import java.util.Properties
import java.io.File
import java.io.FileInputStream


object PropertyMapFactory {

  type PropertyMap = HashMap[String, String]
  def loadMapFromProperties(props: Properties) : PropertyMap = {
    val propMap = new PropertyMap
    val names = props.propertyNames
    names.foreach(name => {
      val nameStr = name.asInstanceOf[String]
      val value = props.getProperty(nameStr).asInstanceOf[String]
      propMap += (nameStr -> value)
    })

    propMap
    		
  }
  
  
	def readPropertiesFromFile(filename: String) :Properties = {
		val propertyFile = new File(filename)
		val props = new Properties
		props.load(new FileInputStream(propertyFile))
		props
	}
  
	def propertyMapFromFile(filename: String) : PropertyMap = {
		val props = readPropertiesFromFile(filename)
		loadMapFromProperties(props)
	}
	
}


