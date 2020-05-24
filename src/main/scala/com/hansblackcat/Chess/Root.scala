package com.hansblackcat.Chess

class Root {
    implicit class ExLocationMaker (row: Char) {
        def #> (colum: Int) = {
            ExLocation(row +: colum.toString)
        }
    }

    val gridKeys = for (i <- 'a' to 'h'; j <- 1 to 8) yield i +: j.toString()
    val gridValue = 4
}