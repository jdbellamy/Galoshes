package com.example

import com.domingosuarez.boot.autoconfigure.jade4j.JadeHelper

@JadeHelper("utils")
public class ViewHelper {

    def upcase(String name) {
        List.metaClass.collectWithIndex = { yield ->
            def collected = []
            delegate.eachWithIndex { listItem, index ->
                collected << yield(listItem, index)
            }
            return collected
        }
        (name as List).collectWithIndex { item, i ->
            if (i == 0) item.toUpperCase()
            else item
        }.join('')
    }

    def upcase2(String name) {
        def acc = []
        name.collect(acc, {
            if (acc.size() == 0) it.toUpperCase()
            else it
        }).join('')
    }

    def formatLabel(String label) {
        def head = label.substring(0,1).toUpperCase()
        def tail = label.substring(1)
        String.join('', head, tail)
    }
}


