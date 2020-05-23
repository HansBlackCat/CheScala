package com.hansblackcat.program

trait GameActionList
case object BACKWARD extends GameActionList
case object RESTART extends GameActionList

trait PlayActionList
case object MOVE extends PlayActionList
