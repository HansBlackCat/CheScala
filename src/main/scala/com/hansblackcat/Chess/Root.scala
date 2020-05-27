package com.hansblackcat.Chess
import scala.collection.mutable.{Map=>MMap}
import scala.math._

class Root {
    implicit class ArrayToExLocation (arr: Array[Int]) {
        def toExLoc() = {
            require(
                arr.length == 2 &&
                0 <= arr(0) && arr(0) <8 &&
                0 <= arr(1) && arr(1) <8
            )
            ExLocation((arr(0)+97).toChar +: (arr(1)+1).toString())
        }
    }
    implicit class ArrayLikeVector (arr: Array[Int]) {
        def +> (tup: (Int, Int)) = {
            Array(arr(0)+tup._1, arr(1)+tup._2)
        }
        def +> (arr2: Array[Int]) = {
            require(arr.length == 2)
            Array(arr(0)+arr2(0), arr(1)+arr2(1))
        }
        def -> (tup: (Int, Int)) = {
            Array(arr(0)-tup._1, arr(1)-tup._2)
        }
        def -> (arr2: Array[Int]) = {
            require(arr.length == 2)
            Array(arr(0)-arr2(0), arr(1)-arr2(1))
        }
        def toUnit() = {
            def safeUnit(a: Int) = if (a != 0) { a / abs(a) } else a
            Array(safeUnit(arr(0)), safeUnit(arr(1)))
        }
    }

    implicit class ExLocationMaker (row: Char) {
        /*
        def #> (colum: Int) = {
            ExLocation(row +: colum.toString)
        }
        */
        def #> (colum: Char) = {
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
    val infoNone: Info = InfoNone
    def infoBlackWhite(info: Info) = {
        info match {
            case InfoNone        => throw new Exception("call of None type")
            case InfoBlack(_, _) => false
            case InfoWhite(_, _) => true
        }
    }

    // Vector(a1, b1, c1, ..., a2, b2, ...
    val baseGridKeys = for (j <- 1 to 8; i <- 'a' to 'h') yield i +: j.toString()

    val baseGridValue: Vector[Info] = Vector(
        Rook.<<@>, Knight.<<@>, Bishop.<<@>, Queen.<<@>, King.<<@>, Bishop.<<@>, Knight.<<@>, Rook.<<@>,
        Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>, Pawn.<<@>,
    ) ++
        ((for (i <- 1 to 32) yield infoNone).toVector) ++
        Vector(
        Pawn.<@>>, Pawn.<@>>, Pawn.<@>>, Pawn.<@>>, Pawn.<@>>, Pawn.<@>>, Pawn.<@>>, Pawn.<@>>,
        Rook.<@>>, Knight.<@>>, Bishop.<@>>, Queen.<@>>, King.<@>>, Bishop.<@>>, Knight.<@>>, Rook.<@>>,
    )
    val baseMapHashTMP = baseGridKeys.zip(baseGridValue).toMap
    val baseMapHash = MMap(baseMapHashTMP.toSeq: _*)
    
    val _emptyGrid = (baseGridKeys zip (for (i <- 1 to 64) yield infoNone)).toMap
    val emptyGrid: MMap[String, Info] = MMap(_emptyGrid.toSeq: _*)

    val testGrid1 = {
        var tmpGrid = emptyGrid.clone()
        tmpGrid("f3") = Queen.<#>>; tmpGrid("e1") = King.<<@>; tmpGrid("d3") = Rook.<#>>; tmpGrid("e5") = Knight.<<#>
        tmpGrid("g4") = Bishop.<<#>; tmpGrid("a5") = Pawn.<@>>; tmpGrid("b5") = Pawn.<<#>; tmpGrid("h1") = Rook.<<@>
        tmpGrid("c2") = Pawn.<<@>; tmpGrid("d8") = King.<#>>; tmpGrid("d7") = Bishop.<<#>; tmpGrid("d6") = Rook.<<#>
        tmpGrid("g8") = Knight.<<#>
        tmpGrid
    }
}