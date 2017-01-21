import groovy.json.JsonSlurper

/**
 * Created by leo on 17-1-20.
 */
def res = new URL("http://127.0.0.1:8080/Ddaas/tag/all").getText()
def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText(res)
println "statusCode: ${object.statusCode}"
def rawData = object.data
def colNum = rawData[0].size()
def columnNames = rawData[0].keySet() as String[]
println '--------=========='
println "colNum: $colNum"
println "rowCount: ${rawData.size()}"
println columnNames
println rawData[0]
def valueList = []
rawData.each { row ->
    def aRec = []
    row.each { key, value ->
        aRec.add(value)
    }
    valueList.add(aRec)
}
println valueList