package com.hansblackcat.Chess

class Root {
    implicit class ExLocationMaker (row: Char) {
        def #> (colum: Int) = {
            ExLocation(row +: colum.toString)
        }
    }
    implicit class ExLocationMaker2 (row: Int) {
        def #> (colum: Int) = {
            val tmp = (row + 96).toChar
            ExLocation(tmp +: colum.toString)
        }
    }
    implicit class InfoColorMaker (kind: PGNPieceKind) {
        def <<@> () = {
            InfoWhite(kind, true)
        }
        def <<#> () = {
            InfoWhite(kind, false)
        }
        def <@>> () = {
            InfoBlack(kind, true)
        }
        def <#>> () = {
            InfoBlack(kind, false)
        }
    }
    val infoNone = InfoNone

    // Vector(a1, a2, a3, a4, a5, a6, a7, a8, b1, ...
    val baseGridKeys = for (i <- 'a' to 'h'; j <- 1 to 8) yield i +: j.toString()

    val baseGridValue: Vector[Info] = Vector(
        Rook.<<@>, Knight.<<@>, Bishop.<<@>, Queen.<<@>, King.<<@>, Bishop.<<@>, Knight.<<@>, Rook.<<@>,
        Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>,
    ) ++
        ((for (i <- 1 to 32) yield infoNone).toVector) ++
        Vector(
        Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>,
        Rook.<<@>, Knight.<<@>, Bishop.<<@>, Queen.<<@>, King.<<@>, Bishop.<<@>, Knight.<<@>, Rook.<<@>,
    )
    val baseMapHash = baseGridKeys.zip(baseGridValue).toMap


    
}