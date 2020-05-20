# CheScala

[![](https://mermaid.ink/img/eyJjb2RlIjoiY2xhc3NEaWFncmFtXG4gICAgQ2hlc3NCaXRDb250cm9sIDx8LS0gQ2hlc3NQaWVjZVxuICAgIENoZXNzUGllY2VLaW5kIC4ufD4gQ2hlc3NQaWVjZSA6dHJhaXRcbiAgICBDaGVzc1BpZWNlIC4uIEJvYXJkXG4gICAgUGllY2VBY3Rpb24gLi58PiBCb2FyZCA6IHRyYWl0XG4gICAgQm9hcmQgPHwtLSBCb2FyZE1haW5cblxuICAgIGNsYXNzIENoZXNzQml0Q29udHJvbCB7XG4gICAgXG4gICAgfVxuICAgIGNsYXNzIENoZXNzUGllY2Uge1xuICAgICAgICAtX3BpZWNlS2luZFxuICAgICAgICAtX2xvY2F0aW9uXG4gICAgICAgIC1faXNXaGl0ZVxuICAgICAgICAtX2lzUHJvbW90aW9uXG4gICAgICAgICtwcm9tb3Rpb25UbygpXG4gICAgICAgICttb3ZlKClcbiAgICB9XG4gICAgY2xhc3MgQm9hcmQge1xuICAgICAgICAtY3VycmVudEJvYXJkXG4gICAgICAgIC1oaXN0b3J5Qm9hcmRcbiAgICAgICAgK2luaXRpYXRlQm9hcmQoKVxuXG4gICAgfVxuXG4gICAgY2xhc3MgQ2hlc3NQaWVjZUtpbmQge1xuICAgICAgICA8PEFEVD4-XG4gICAgICAgIEtpbmdcbiAgICAgICAgUXVlZW5cbiAgICAgICAgUm9va1xuICAgICAgICBLbmlnaHRcbiAgICAgICAgQmlzb3BcbiAgICAgICAgUGF3blxuICAgICAgICBVbmRlZmluZWRcbiAgICB9XG5cdFx0XHRcdFx0IiwibWVybWFpZCI6eyJ0aGVtZSI6ImRlZmF1bHQifX0)](https://mermaid-js.github.io/mermaid-live-editor/#/edit/eyJjb2RlIjoiY2xhc3NEaWFncmFtXG4gICAgQ2hlc3NCaXRDb250cm9sIDx8LS0gQ2hlc3NQaWVjZVxuICAgIENoZXNzUGllY2VLaW5kIC4ufD4gQ2hlc3NQaWVjZSA6dHJhaXRcbiAgICBDaGVzc1BpZWNlIC4uIEJvYXJkXG4gICAgUGllY2VBY3Rpb24gLi58PiBCb2FyZCA6IHRyYWl0XG4gICAgQm9hcmQgPHwtLSBCb2FyZE1haW5cblxuICAgIGNsYXNzIENoZXNzQml0Q29udHJvbCB7XG4gICAgXG4gICAgfVxuICAgIGNsYXNzIENoZXNzUGllY2Uge1xuICAgICAgICAtX3BpZWNlS2luZFxuICAgICAgICAtX2xvY2F0aW9uXG4gICAgICAgIC1faXNXaGl0ZVxuICAgICAgICAtX2lzUHJvbW90aW9uXG4gICAgICAgICtwcm9tb3Rpb25UbygpXG4gICAgICAgICttb3ZlKClcbiAgICB9XG4gICAgY2xhc3MgQm9hcmQge1xuICAgICAgICAtY3VycmVudEJvYXJkXG4gICAgICAgIC1oaXN0b3J5Qm9hcmRcbiAgICAgICAgK2luaXRpYXRlQm9hcmQoKVxuXG4gICAgfVxuXG4gICAgY2xhc3MgQ2hlc3NQaWVjZUtpbmQge1xuICAgICAgICA8PEFEVD4-XG4gICAgICAgIEtpbmdcbiAgICAgICAgUXVlZW5cbiAgICAgICAgUm9va1xuICAgICAgICBLbmlnaHRcbiAgICAgICAgQmlzb3BcbiAgICAgICAgUGF3blxuICAgICAgICBVbmRlZmluZWRcbiAgICB9XG5cdFx0XHRcdFx0IiwibWVybWFpZCI6eyJ0aGVtZSI6ImRlZmF1bHQifX0)

``` mermaid
classDiagram
    ChessBitControl <|-- ChessPiece
    ChessPieceKind ..|> ChessPiece :trait
    ChessPiece .. Board
    PieceAction ..|> Board : trait
    Board <|-- BoardMain

    class ChessBitControl {
    
    }
    class ChessPiece {
        -_pieceKind
        -_location
        -_isWhite
        -_isPromotion
        +promotionTo()
        +move()
    }
    class Board {
        -currentBoard
        -historyBoard
        +initiateBoard()

    }

    class ChessPieceKind {
        <<ADT>>
        King
        Queen
        Rook
        Knight
        Bisop
        Pawn
        Undefined
    }
```