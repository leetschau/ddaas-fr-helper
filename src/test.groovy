/**
 * Created by leo on 17-1-20.
 */

//@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')
import groovyx.net.http.HTTPBuilder

def http = new HTTPBuilder('http://192.168.1.213:8081')
def colNames = {}
def res = {}

http.post(path: '/Ddaas/data/queryMetaObjData', query: [metaObjectName: '产品', forceFetch: 1]) { resp, json ->
    println resp.status
    colNames = json.data.metaAttrNames
    res = json.data.dataRows
}
println colNames
println res
