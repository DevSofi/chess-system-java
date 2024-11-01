package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
//Gerencia o estado do jogo de xadrez.
public class ChessMatch {

    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();

    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        //cria uma matriz bidimensional
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);

            }
        }
        return mat;
    }
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
    //executa uma movimentação entre duas posições.
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source);
        //valida se a posição de origem (onde a peça está atualmente) contém uma peça

        Piece capturedPiece = makeMove(source,target); // realizar a movimentação da peça
        //Remover a peça da posição de origem.
        //Colocá-la na posição de destino.
        //Retornar qualquer peça que tenha sido capturada (se houver uma peça no destino).

        return(ChessPiece)capturedPiece;
    }
    private Piece makeMove(Position source, Position target) {

        Piece p = board.removePiece(source);
        //O método começa removendo a peça da posição de origem (source).
        // O método removePiece() do Board retorna a peça que estava naquela posição e, ao mesmo tempo,
        // remove-a do tabuleiro.
        Piece capturedPiece = board.removePiece(target);
        //o método tenta remover qualquer peça que esteja na posição de destino (target).
        // Isso pode ser uma peça que foi capturada durante o movimento.
        // Se não houver peça nessa posição, capturedPiece será null.

        board.placePiece(p,target);
        // atualiza a posição da peça no tabuleiro.
        //O método placePiece() verifica se a posição de destino já contém outra peça.
        // Se houver uma peça na posição de destino, o método deve lançar uma exceção
        //p: É a peça de xadrez que estamos movendo.
        //target: É a nova posição onde a peça deve ser colocada. Este parâmetro é do tipo

        return capturedPiece;
        // o método retorna a peça que foi capturada (ou null se nenhuma peça foi capturada).
    }
    private void validateSourcePosition(Position position) {
        if (!board.thereIsPiece(position)) {

            throw new ChessException("there is no piece on source position");
        }
    }
    private void placeNewPiece(char column, int row, ChessPiece piece) {

        board.placePiece(piece,new ChessPosition( column, (char) row).toPosition());
    }

    private void initialSetup() {
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
