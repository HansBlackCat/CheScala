package com.hansblackcat.scalaFX
import com.hansblackcat.Chess._

trait Functions {


    def toUni(ipt: Info) = {
        ipt match {
            case InfoNone => ""
            case InfoWhite(kind, _) => 
                kind match {
                    case King => "\u2654"
                    case Queen => "\u2655"
                    case Rook => "\u2656"
                    case Bishop => "\u2657"
                    case Knight => "\u2658"
                    case Pawn => "\u2659"
                }
            case InfoBlack(kind, _) => 
                kind match {
                    case King => "\u265A"
                    case Queen => "\u265B"
                    case Rook => "\u265C"
                    case Bishop => "\u265D"
                    case Knight => "\u265E"
                    case Pawn => "\u265F"
                }
        }
    }

    def exLocationToArr(ipt: ExLocation) {
        ipt.location.split("").map(i =>
            if ('a' to 'z' contains i.toCharArray()(0)) {
                i.toCharArray()(0).toInt - 96
            } else {
                i.toInt
            }
        )
    }
}