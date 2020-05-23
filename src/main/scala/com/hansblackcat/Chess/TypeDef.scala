package com.hansblackcat.Chess

trait Location
case class ExLocation(location: String) extends Location

trait PGNTagPairs
case class Event(contents: String) extends PGNTagPairs
case class Site(contents: String) extends PGNTagPairs
case class Date(contents: String) extends PGNTagPairs
case class Round(contents: String) extends PGNTagPairs
case class White(contents: String) extends PGNTagPairs
case class Black(contents: String) extends PGNTagPairs
case class Result(contents: String) extends PGNTagPairs

trait PGNPieceKind
case object Phone extends PGNPieceKind
case object King extends PGNPieceKind
case object Queen extends PGNPieceKind
case object Knight extends PGNPieceKind
case object Bishop extends PGNPieceKind
case object Rook extends PGNPieceKind

trait PGNSpecial
case object PGNNone extends PGNSpecial


trait PGNMoveText extends PGNPieceKind
case class MoveRound(round: Int) extends PGNMoveText
case class WhiteLoc(who: PGNPieceKind, where: Location)
case class BlackLoc(who: PGNPieceKind, where: Location)


trait PGN extends Location

