import groovy.json.JsonSlurper

def urlString = "https://httpbin.org/post"
def queryString = '{"第一": 3, "第二": 45, "abc": 345345}'

def url = new URL(urlString)
def connection = url.openConnection()
connection.setRequestMethod("POST")
connection.doOutput = true
connection.setRequestProperty('Content-Type', 'application/json')

def writer = new OutputStreamWriter(connection.outputStream)
writer.write(queryString)
writer.flush()
writer.close()
connection.connect()

def jsonSlurper = new JsonSlurper()
def res = jsonSlurper.parseText(connection.content.text)
println "result keys: ${res.keySet()}"
//println "data: ${res.data}"
println "json: ${res.json}"
println "json/key: ${res.json['第一']}"
println "headers/Accept: ${res.headers.Accept}"
println "headers/Content-Type: ${res.headers['Content-Type']}"
