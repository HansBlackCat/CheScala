package com.hansblackcat

class ChessPiece private (
    private[this] var _pieceKind: ChessPieceKind,
    private[this] var _location: (Int, Int),
    private[this] var _isWhite: Boolean,
    private[this] var _isPromotion: Boolean
) extends ChessBitControl with ChessPieceKind {

    def pieceKind = _pieceKind
    def pieceKind_= (_piece: ChessPieceKind) = _pieceKind = _piece
    def location = _location
    def location_= (_loc: (Int, Int)) = { _location = _loc }
    def isWhite = _isWhite
    def isWhite_= (isWhite: Boolean) = { _isWhite = isWhite }
    def isPromotion = _isPromotion
    def isPromotion_= (isPromotion: Boolean) = { _isPromotion = isPromotion }

    def this(kindIden: ChessPieceKind, locIden: (Int, Int), isWhiteIden: Boolean) = {
        this(kindIden, locIden, isWhiteIden, false)
    }

    def promotion(kindIden: ChessPieceKind) = {
        isPromotion = true
        pieceKind = kindIden
    } 
}
object ChessPiece {
    def apply(x: Char, loc: Int, white: Int) = {
        val charToKind = x match {
            case 'k' | 'K' => King
            case 'q' | 'Q' => Queen
            case 'r' | 'R' => Rook
            case 'b' | 'B' => Bishop
            case 'n' | 'N' => Knight
            case 'p' | 'P' => Pawn
            case _         => throw new Error("No m" +
              "atch")
        }
        val intToLoc = {
            require(10 < loc && loc < 89)
            (loc/10, loc%10)
        }
        val intToBool = {
            require(white == 0 || white == 1)
            white match {
                case 1 => true
                case 0 => false
            }
        }
        new ChessPiece(charToKind, intToLoc, intToBool)
    }
}