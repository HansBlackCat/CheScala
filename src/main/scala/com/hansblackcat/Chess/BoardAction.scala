package com.hansblackcat.Chess

class BoardAction extends Root with PGN {
    // TagPair
    // Comment

    private[this] var historyMoveText = Array()
    // private[this] var rightBfMoveText = Array()
    private[this] var currentMoveText = Array()

    def setMoveText(txt: PGNMoveText) = {
        txt match {
            case MoveRound(round) => 
            case White(who, where, what) => 
        }    
    }

    
}