package com.hansblackcat.program
import scala.collection.mutable.ListBuffer

// Castling
// Enpassant
// 50 draw
// st

class Board extends PieceAction with GameActionList with PlayActionList {
    private[this] var _currentBoard = ListBuffer[ChessPiece]()
    private[this] var _rightBeforeBoard = ListBuffer[ChessPiece]()
    private[this] var _historyBoard = ListBuffer[ListBuffer[ChessPiece]]()
    private[this] var _turn = 1

    private var _currentBoardLocation = for (i <- _currentBoard) yield i.location

    def currentBoard = _currentBoard
    def rightBeforeBoard = _rightBeforeBoard
    def turn = _turn

    def isThere(x: (Int, Int)): (Boolean, ChessPiece, Boolean) = {
        lazy val isContain = _currentBoardLocation.contains(x)
        lazy val realPiece = _currentBoard.find(_.location == x).get
        lazy val isW       = realPiece.isWhite
        (isContain, realPiece, isW)
    }

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

    // MOVE, BACKWARD, RESTART
    def gameAction(aL: GameActionList) = {
        aL match {
            case RESTART => 
                _currentBoard = ListBuffer[ChessPiece]()
                _rightBeforeBoard = ListBuffer[ChessPiece]()
                _historyBoard = ListBuffer[ListBuffer[ChessPiece]]()
                _turn = 1
                initiateBoard()
            case BACKWARD => 
                _currentBoard = _rightBeforeBoard
                // _rightBeforeBoard = _currentBoard
                turnChange()
            case _ => throw new Exception("Illegal input") 
        }
    }

    def playAction(a: PlayActionList, where: (Int, Int), toward: (Int, Int)) = {
        val currentLoc = for (i <- _currentBoard) yield i.location
        a match {
            case MOVE =>
                if (
                    currentLoc.contains(where) &&
                    range(isThere(where)._2 , _currentBoard, _rightBeforeBoard).contains(toward) 
                ) {
                    if (isThere(toward)._1) () ////////////////////////////////////////
                }
            case _ => throw new Exception("illegal ipt")
        }
    }

    // TODO
    // En Passant
    // Check / Checkmate

    def printConsole() = {
        println(_currentBoard.length)
    }
}