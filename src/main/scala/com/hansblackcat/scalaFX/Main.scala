package com.hansblackcat.scalaFX
import com.hansblackcat.Chess._

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.StackPane
import scalafx.scene.layout.BorderPane
import scalafx.scene.Scene
import scalafx.scene.control.MenuBar
import scalafx.scene.control.Menu
import scalafx.scene.control.MenuItem
import scalafx.scene.control.Label
import scalafx.scene.shape.Rectangle
import scalafx.scene.paint.Color
import scalafx.scene.control.Tab
import scalafx.scene.control.SplitPane
import scalafx.scene.control.TabPane
import scalafx.geometry.Orientation
import scalafx.event.ActionEvent
import scalafx.scene.text.Font
import scalafx.scene.control.Button
import scalafx.scene.shape.StrokeLineCap.Butt
import scalafx.scene.control.Hyperlink
import scalafx.scene.layout.TilePane
import scalafx.beans.property.StringProperty
import scalafx.scene.input.MouseEvent
import scalafx.scene.Node
import scalafx.scene.shape.StrokeLineCap.Square
import scalafx.event.EventHandler
import scalafx.event.EventType
import scalafx.scene.input.MouseButton

object Main extends JFXApp with Functions {
    stage = new JFXApp.PrimaryStage {
        title = "CheScala"
        scene = new Scene(800, 800) {
            val menuBar = new MenuBar
            val startMenu = new Menu("\u2658")
            val startMenu_new = new MenuItem("start")
            startMenu.items = List(startMenu_new)
            menuBar.menus = List(startMenu)

            startMenu_new.onAction = (actionEvent: ActionEvent) => {
                val newBoard = mainTabUI()
                tabPane += newBoard
            }


            val tabPane = new TabPane
            tabPane += mainTabUI()

            val rootPane = new BorderPane
            rootPane.top = menuBar
            rootPane.center = tabPane
            root = rootPane
        }
    }


    private[this] def mainTabUI(): Tab = {
        val leftSplit = new SplitPane
        leftSplit.orientation = Orientation.Vertical

        val rightSplit = new SplitPane
        rightSplit.orientation = Orientation.Vertical
        val rightSplit_Top = new BorderPane
        val rightSplit_Bottom = new BorderPane
        rightSplit_Top.center = chessMainBoard()
        rightSplit.items ++= List(rightSplit_Top, rightSplit_Bottom)

        val splitItems = new SplitPane
        splitItems.items ++= List(leftSplit, rightSplit)
        splitItems.dividerPositions= 0.3

        val tab = new Tab
        tab.text = "Tab"
        tab.content = splitItems
        tab
    }


    private[this] def chessMainBoard() = {
        val newBoard = new BoardAction
        newBoard.start()
        val pieceRange = newBoard.possibleShowAll()

        val gridPane = new GridPane { gridLinesVisible = true }
        for (row <- 1 to 8; column <- 1 to 8) {
            val tmpText = s"${(row+96).toChar}${9-column}"
            
            val button = new Button {
                maxHeight = Double.MaxValue
                maxWidth = Double.MaxValue
                style = """
                -fx-background-color: transparent;
                -fx-stroke: transparent;
                """
            }
            val rectangle = new Rectangle {
                width = 75
                height = 75
                fill = if ((row + column) % 2 == 0) Color.Bisque else Color.SaddleBrown
                stroke = Color.Black
            }
            val label = new Label {
                maxHeight = Double.MaxValue
                maxWidth = Double.MaxValue

                text = toUni(newBoard.currentShow(tmpText))
                style = """
                -fx-text-alignment: center;
                -fx-alignment: center;
                """
                font = Font(30)
            }
            button.onAction = (e: ActionEvent) => {
                
            }
            
            val grid = new GridPane {
                children = List(
                    rectangle,
                    label,
                    button
                )
            }

            gridPane.add(grid, row, column)
        } 
        
        for (node <- gridPane.getChildren()) {
            node.setOnMouseEntered((mouseEvent: MouseEvent) => {
                val col = GridPane.getColumnIndex(node)
                val row = GridPane.getRowIndex(node)
                println(s"$col$row")
               
                
                
            })
            node.setOnMouseExited((mouseEvent: MouseEvent) => {
                val col = GridPane.getColumnIndex(node)
                val row = GridPane.getRowIndex(node)
                
            })
        }

        /*
        gridPane.children(15).setStyle("""    
        -fx-background-color: linear-gradient(
        to bottom, #dbdcdd 0%, #a9a9ab 100%);
        -fx-padding: 6px;
        -fx-font-size: 1.2em;
        """)
        */
        
        gridPane
    }
}