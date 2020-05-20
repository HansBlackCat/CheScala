package com.hansblackcat
import scala.collection.mutable.ListBuffer

class Board extends PieceAction {
    private[this] var _currentBoard = ListBuffer[ChessPiece]()
    private[this] var _historyBoard = ListBuffer[ListBuffer[ChessPiece]]()
    private[this] var _turn = 1

    def currentBoard = _currentBoard
    def turn = _turn

    def initiateBoard() = {
        val initPiece = {
            val whitePhone = for (i <- 1 until 9) yield ChessPiece('p', 20+i, 1)
            val blackPhone = for (i <- 1 until 9) yield ChessPiece('p', 70+i, 0)
            val whiteSeq = Seq(
                ChessPiece('r',11,1),ChessPiece('n',12,1),ChessPiece('b',13,1),ChessPiece('q',14,1),
                ChessPiece('k',15,1),ChessPiece('b',16,1),ChessPiece('n',17,1),ChessPiece('r',18,1)
            )
            val blackSeq = Seq(
                ChessPiece('r',81,0),ChessPiece('n',82,0),ChessPiece('b',83,0),ChessPiece('q',84,0),
                ChessPiece('k',85,0),ChessPiece('b',86,0),ChessPiece('n',87,0),ChessPiece('r',88,0)
            ) 
            whiteSeq++whitePhone++blackSeq++blackPhone
        }
        for (i <- initPiece) _currentBoard += i
    }   

    def delete(loc: (Int, Int)) = {
        require(0 < loc._1 && loc._1 < 9 && 0 < loc._2 && loc._2 < 9)
        var delete = false
        for (i <- _currentBoard) {
            if (i.location == loc) {
                delete = true
                _currentBoard -= i
            }
        }

        if (delete == false) {
            throw new Exception("No element to delete")
        }
    }    

    def turnChange() = { _turn = _turn ^ 1 }


    // TODO
    // delete
    // turn
    // range


    def printConsole() = {
        println(_currentBoard.length)
    }
}