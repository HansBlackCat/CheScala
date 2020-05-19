package com.hansblackcat
import scala.math._

class ChessBitControl {
    // K Q R B K P (N)
    // 0b 0000 0000 0000 0000
    // ^12   := safe node
    // ^9~11 := col loc
    // ^6~8  := row loc
    // ^5    := white | black
    // ^4    := promotioned | default
    // ^0~3  := piece (0~6 := N K Q R B K P)

    def stringToByte(x: String) = x.split("").reverse.zipWithIndex.foldRight(0)((ipt, acc) => (ipt._1.toInt)*(pow(ipt._2, 2).toInt) + acc)
    

    def color(x: Int): Int = {
        x >> 7
    }



}