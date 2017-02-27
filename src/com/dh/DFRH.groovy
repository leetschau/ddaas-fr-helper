package com.dh

//@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')

import com.fr.data.AbstractTableData
import groovyx.net.http.HTTPBuilder

/**
*  Created by leo on 17-1-20.
*/

class DFRH extends AbstractTableData {
    int colNum = 0
    List<String> columnNames = null
    ArrayList valueList = null

    int getColumnCount() {
        init()
        colNum
    }

    String getColumnName(int columnIndex) {
        init()
        columnNames[columnIndex]
    }

    int getRowCount() {
        init()
        valueList.size()
    }

    Object getValueAt(int rowIndex, int columnIndex) {
        init()
        if (columnIndex >= colNum) {
            return null
        }
        ((Object[]) valueList.get(rowIndex))[columnIndex]
    }

    void init() {
        if (valueList != null) {
            return
        }
        def http = new HTTPBuilder('http://192.168.1.213:8081')
        http.post(path: '/Ddaas/data/queryMetaObjData', query: [metaObjectName: '产品', forceFetch: 1]) { resp, json ->
            println resp.status
            this.columnNames = json.data.metaAttrNames
            this.colNum = this.columnNames.size()
            this.valueList = json.data.dataRows
        }
    }

    void release() throws Exception {
        super.release()
        this.valueList = null
    }
}