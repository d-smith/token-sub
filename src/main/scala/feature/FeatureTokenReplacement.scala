package feature

import scala.io.{BufferedSource, Source}
import java.util.Properties
import java.io.{File, StringReader}



object FeatureTokenReplacement
{
  import props.PropertyMapFactory.PropertyMap


  def replaceTokenizedParamsFromMap(propMap: PropertyMap, featureFileName: String) : String = {
    getFileLines(featureFileName)

    val output = new StringBuilder
    var processingConfig = false
    var configLines = List[String]()
    getFileLines(featureFileName).foreach(line => {
      processingConfig match {
        case false => {
          if(line.contains("<config")) processingConfig = true
          addLineToOutput(output, line)
        }
        case true => {
          if(processingConfig && line.contains("</config>")) {
            processingConfig = false
            processConfigLines(output, configLines, propMap)
            configLines = List[String]()
            addLineToOutput(output, line)
          } else {
            configLines = configLines :+ line
          }
        }
      }
    })

    output.toString
  }

  def getFileLines(featureFileName: String) : Iterator[String] = {
    val featureFile = new File(featureFileName)
    val featureSource = Source.fromFile(featureFile)
    featureSource.getLines
  }

  private def addLineToOutput(output: StringBuilder, line: String) : Unit = {
    output.append(line)
    output.append('\n')
  }

  private def processConfigLines(output: StringBuilder, lines: List[String], propMap: PropertyMap) : Unit = {
    lines.foreach(line => {
      val configTokens = line.split('=')
      assert(configTokens.size == 2)
      val tokenName = normalizeToken(configTokens(1))
      val tokenValue = propMap.getOrElse(tokenName,configTokens(1))
      addLineToOutput(output, String.format("%s = %s", configTokens(0), tokenValue))
    })
  }

  private def normalizeToken(token: String) : String = {
    token.replace("###", "").trim
  }
}