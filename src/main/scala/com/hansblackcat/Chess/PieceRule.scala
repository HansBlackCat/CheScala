package com.hansblackcat.Chess
import scala.collection.mutable.{Map => MMap}
import scala.math._

trait PieceRule extends Root {

    private[this] def base1Range(location: String, kind: PGNPieceKind, isWhite: Boolean) = {
        require(baseGridKeys contains location)
        
        val locArr = ExLocation(location).toArrLoc
        val baseKeysArr = baseGridKeys.toArray
        
        val kindMatch = { 
            val tmp = kind match {
                case King => 
                    for (i <- -1 to 1; j <- -1 to 1 if i!=0 & j!=0) yield locArr +> (i,j)
                case Queen => 
                    val t1 = for (i <- -8 to 8 if i != 0) yield locArr +> (i,0)
                    val t2 = for (j <- -8 to 8 if j != 0) yield locArr +> (0,j) 
                    val t3 = for (i <- -8 to 8 if i != 0) yield locArr +> (i,i)
                    val t4 = for (i <- -8 to 8 if i != 0) yield locArr +> (i,-i)
                    t1 ++ t2 ++ t3 ++ t4
                case Rook => 
                    val t1 = for (i <- -8 to 8 if i != 0) yield locArr +> (i,0)
                    val t2 = for (j <- -8 to 8 if j != 0) yield locArr +> (0,j) 
                    t1 ++ t2
                case Bishop => 
                    val t3 = for (i <- -8 to 8 if i != 0) yield locArr +> (i,i)
                    val t4 = for (i <- -8 to 8 if i != 0) yield locArr +> (i,-i)
                    t3 ++ t4
                case Knight => 
                    val t = for (i <- -2 to 2; j <- -2 to 2 if abs(i) + abs(j) == 3) yield locArr +> (i,j)
                    t
                case Pawn =>
                    if (isWhite) for (i <- -1 to 1) yield locArr +> (i, 1)
                    else for (i <- -1 to 1) yield locArr +> (i, -1)
            }
            tmp.toArray

            //// ---------- intersection

            }

    }

    def mapInterpret(currentMap: MMap[String, Info]) = {

        var a: Location = NoneLocation
        for (i <- Seq("a2", "kk")) {
            try { a = ExLocation(i) }
            catch {
                case a: IllegalAccessException => {println("req err")}
                case _: Throwable => {println("Some err")}
            } finally {
                println(a.toString ++ "aaaa")
            }
        }
    }
}