package com.hansblackcat
import scala.math._
import scala.collection.mutable.ListBuffer

trait PieceAction {
    private[this] val _avail = Set() ++ {
        for (i<-1 until 9; j<-1 until 9) yield (i,j)
    }

    // shading
    // enPassant
    def range(elem: ChessPiece, boardStatus: ListBuffer[ChessPiece], history: ListBuffer[ListBuffer[ChessPiece]]) = {
        val eL = elem.location
        val ally = {
            if (elem.isWhite) boardStatus.filter(_.isWhite)
            else boardStatus.filterNot(_.isWhite)
        }
        val foe = {
            if (elem.isWhite) boardStatus.filterNot(_.isWhite)
            else boardStatus.filter(_.isWhite) 
        }

        elem.pieceKind match {
            case Pawn => 
                val init = if ((elem.isWhite && elem.location._2==2) | (!elem.isWhite && elem.location._2==7)) 1 else 0
                val toward = if (elem.isWhite) 1 else -1
                val max = Set(
                    eL, (eL._1, eL._2+toward), (eL._1, eL._2+toward+init*toward)
                )
                val edit = _avail & max diff Set(eL)

                // EnPassant

            case Rook => 
                val max = Set() ++ 
                    { for (i<-(-7) until 8) yield (eL._1 + i, eL._2) } ++
                    { for (i<-(-7) until 8) yield (eL._1, eL._2 + i) }
                val edit = _avail & max diff Set(eL)

                val allyFoeElisionCount = {
                    def safeUnit(a: Int) = if (a!=0) a/abs(a) else 0

                    var elisionPoint = ListBuffer[(Int, Int)]()
                    for { i<-((for (a<-boardStatus) yield a.location).toSet & edit); j<-1 until 8 } {
                        val unitPorgress = (safeUnit(i._1-eL._1), safeUnit(i._2-eL._2))
                        elisionPoint :+ (i._1 * j, i._2 * j)
                    }
                    
                    // Add Where enemies
                }
            case Bishop =>
                val max = Set() ++
                    { for (i<-(-7) until 8) yield (eL._1+i, eL._2+i) } ++
                    { for (i<-(-7) until 8) yield (eL._1+i, eL._2-i) }
                val edit = _avail & max diff Set(eL)
            case Knight => 
                val max = Set() ++
                    { for {i<-(-2) until 3; j<-(-2) until 3; if abs(i)+abs(j)==3} yield (eL._1+i, eL._2+j) }
                val edit = _avail & max diff Set(eL)
            case King => 
                val max = Set() ++
                    { for {i<-(-1) to 1; j<-(-1) to 1} yield (eL._1+i, eL._2+j) } 
                val edit = _avail & max diff Set(eL)
            case Queen => 
                val max = Set() ++
                    { for (i<-(-7) until 8) yield (eL._1 + i, eL._2) } ++
                    { for (i<-(-7) until 8) yield (eL._1, eL._2 + i) } ++
                    { for (i<-(-7) until 8) yield (eL._1+i, eL._2+i) } ++
                    { for (i<-(-7) until 8) yield (eL._1+i, eL._2-i) }
                val edit = _avail & max diff Set(eL)
                
        }

    }    
}