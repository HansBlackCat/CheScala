package com.hansblackcat
import scala.collection.mutable.ListBuffer

class Board extends PieceAction {
    private[this] var currentBoard = ListBuffer[ChessPiece]()
    private[this] var historyBoard = ListBuffer[ListBuffer[ChessPiece]]()

    def initiateBoard() = {
        val initPiece = {
            val whitePhone = for (i <- 1 until 9) yield ChessPiece('p', 20+i, 1)
            val blackPhone = for (i <- 1 until 9) yield ChessPiece('p', 70+i, 0)
            val whiteSeq = Seq(
                ChessPiece('r',11,1),ChessPiece('n',12,1),ChessPiece('b',13,1),ChessPiece('q',14,1),
                ChessPiece('k',15,1),ChessPiece('b',16,1),ChessPiece('n',17,1),ChessPiece('r',18,1)
            )
            val blackSeq = Seq(
                ChessPiece('r',81,1),ChessPiece('n',82,1),ChessPiece('b',83,1),ChessPiece('q',84,1),
                ChessPiece('k',85,1),ChessPiece('b',86,1),ChessPiece('n',87,1),ChessPiece('r',88,1)
            ) 
            whiteSeq++whitePhone++blackSeq++blackPhone
        }
        for (i <- initPiece) currentBoard += i
    }   

    def delete(loc: (Int, Int)) = {
        require(0 < loc._1 && loc._1 < 9 && 0 < loc._2 && loc._2 < 9)
        for (i <- currentBoard) {
            if (i.location == loc) {}
        }
    }    

    // TODO
    // delete
    // turn
    // range


    def printConsole() = {
        println(currentBoard.length)
    }
}