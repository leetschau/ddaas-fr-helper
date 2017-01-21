package com.dh

import com.fr.base.FRContext
import com.fr.data.AbstractTableData
import groovy.json.JsonSlurper

/**
 * Created by leo on 17-1-20.
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
        def res = new URL("http://192.168.1.104:8080/Ddaas/tag/all").getText("utf-8")
        FRContext.getLogger().info("==============HTTP RES================: \n" + res);
        def jsonSlurper = new JsonSlurper()
        def rawData = jsonSlurper.parseText(res).data
        this.colNum = rawData[0].size()
        this.columnNames = rawData[0].keySet() as String[]
        this.valueList = []
        rawData.each { row ->
            def aRec = []
            row.each { key, value -> aRec.add(value) }
            valueList.add(aRec)
        }
    }

    void release() throws Exception {
        super.release()
        this.valueList = null
    }
}