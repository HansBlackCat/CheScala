package com.hansblackcat.scalaFX

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.scene.control.MenuBar
import scalafx.scene.control.Menu
import scalafx.scene.control.MenuItem
import scalafx.scene.control.SeparatorMenuItem
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.TabPane
import scalafx.event.ActionEvent
import scalafx.scene.text.TextFlow
import scalafx.scene.control.TextField

object DrawingMain extends JFXApp {
    stage = new JFXApp.PrimaryStage {
        title = "CheScala"
        scene = new Scene(900, 600) {
            val menuBar = new MenuBar

            val startMenu = new Menu("\u2658")
            val start_start = new MenuItem("Start")
            val start_restart = new MenuItem("Restart")
            val start_back = new MenuItem("Rewind")
            val start_exit = new MenuItem("Exit")
            startMenu.items = List(start_start, new SeparatorMenuItem, start_restart, start_back, new SeparatorMenuItem, start_exit)

            val propMenu = new Menu("Temp")
            val prop_log = new MenuItem("Log")
            propMenu.items = List(prop_log)

            menuBar.menus = List(startMenu, propMenu)
        
            
            val rootPane = new BorderPane
            rootPane.top = menuBar
            root = rootPane

            start_exit.onAction = (actionEvent: ActionEvent) => {
                sys.exit(0)
            }
        }
    }
}