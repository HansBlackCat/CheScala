package com.hansblackcat.Chess
import scala.collection.mutable.{Map=>MMap}
import scala.collection.mutable

class BoardAction extends Root with PGN {
    // TagPair
    // Comment
    // private[this] var commentBuffer: MMap[Int, Comment]

    private[this] var currentBoard: MMap[String, Info] = MMap.empty

    // (whiteAction, blackAction)
    private[this] var historyMoveText = Array[(String, String)]()
    private[this] var currentMoveText = Array[(String, String)]()

    // TODO make this to case class
    private[this] var moveTextBuffer = "" // also check white, black .isEmpty



    // TODO: add TagPair
    def start(i : String) = { 
        currentBoard = i match {
            case "" => baseMapHash
            case "base" => baseMapHash
            case "test1" => testGrid1
        }
        historyMoveText = Array[(String, String)]()
        currentMoveText = Array[(String, String)]()
        moveTextBuffer = ""
    }
    // TDDO `{}` index
    def commentHere(comment: String) = {}

    def act(ipt: String) = {
        // only Pawn-init-doubleMove make init true, else false
        currentBoard("c1") = currentBoard("a2")
        currentBoard("a2") = InfoNone
    }


    // 1. Bb5 cxb5 Bxf7+ O-O e8=Q# 
    def setMoveText(ipt: String) = {
        var txt = ipt
        var isTurn = false
        var whats = Array[PGNSpecial]()
        var whos: Option[PGNPieceKind] = None // Some[T](cont) or None
        var wheres: Option[ExLocation] = None 

        if ('1' to '9' contains txt.head) {
            isTurn = true
        } else {
            for (i <- 1 to 4) {
                txt match {
                    case cap if txt.contains('x') =>
                        val idx = txt.indexOf('x')
                        whats = Capture +: whats
                        txt = txt.substring(0,idx) ++ txt.substring(idx+1)
                    case cm if txt.contains('#') =>
                        whats = CheckMateingMove +: whats
                        txt = txt.substring(0, txt.length - 1)
                        // End - Game
                    case ck if txt.contains('+') =>
                        whats = CheckingMove +: whats
                        txt = txt.substring(0, txt.length - 1)
                    case pro if txt.contains('=') =>
                        val idx = txt.indexOf('=')
                        val promotionTo = txt(idx+1)
                        promotionTo match {
                            case 'Q' => whats = Promotion(Queen) +: whats
                            case 'R' => whats = Promotion(Rook) +: whats
                            case 'B' => whats = Promotion(Bishop) +: whats
                            case 'N' => whats = Promotion(Knight) +: whats
                        }
                        txt = txt.substring(0,idx)
                    case _ => {}
                }
            }

            if (txt.head == 'O') {
                txt match {
                    case "O-O-O" => whats = QSCastling +: whats
                    case "O-O" => whats = KSCastling +: whats
                    case _ => throw new Exception("Wrong Castling Pattern input") 
                }
            } else if (txt.head == txt.head.toUpper) {
                txt.head match {
                    case 'K' => whos = Some(King)
                    case 'Q' => whos = Some(Queen)
                    case 'R' => whos = Some(Rook)
                    case 'B' => whos = Some(Bishop)
                    case 'N' => whos = Some(Knight)
                }

                txt.tail match {
                    case a if a.length == 3 =>
                        whats = OriginFromRow(a.head) +: whats
                        wheres = Some(a.tail(0) #> a.tail(1))
                    case b if b.length == 2 =>
                        wheres = Some(b(0) #> b(1)) 
                }
            } else {
                whos = Some(Pawn)
                txt match {
                    case a if a.length == 3 =>
                        whats = OriginFromRow(a.head) +: whats
                        wheres = Some(a.tail(0) #> a.tail(1))
                    case b if b.length == 2 =>
                        wheres = Some(b(0) #> b(1))
                }
            }
        }

        CurrentMoveText(ipt, isTurn, whats, whos, wheres)
    }

    // OFR(C), Capture, QSC, KSC, Prom, C, CM
    /* When castling not allowed
    Your king has been moved earlier in the game.
    The rook that you would castle with has been moved earlier in the game.
    There are pieces standing between your king and rook.
    The king is in check.
    The king moves through a square that is attacked by a piece of the opponent.
    The king would be in check after castling.
    */

    def checkAvail() = {}
    def action() = {

    }

    // For debug printing
    def debugPrintBoard = {
        _debugPrintB(currentBoard)
    }
    def debugPrintRangeAll = {
        for (i <- currentBoard) {
            i._2 match {
                case InfoNone => {}
                case InfoWhite(kind, _) => 
                    println("<< DEBUG Print Range >>")
                    println(s"-- ${kind} in ${i._1} --")
                    _debugPrintR(PieceRule(currentBoard)(i._1))
                case InfoBlack(kind, _) => 
                    println("<< DEBUG Print Range >>")
                    println(s"-- ${kind} in ${i._1} --")
                    _debugPrintR(PieceRule(currentBoard)(i._1))
            }
        }
    }
    
}