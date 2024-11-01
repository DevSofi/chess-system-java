package chess;

import boardgame.Position;

public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition(char column,int row) {
        if (column<'a' || column>'h' || row<1 || row>8) {
            throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8");
        }
        this.column = column;
        this.row = row;


    }
    public char getColumn() {
        return column;
    }
    public int getRow() {
        return row;
    }
    protected Position toPosition() {
        return new Position(8-row,column - 'a' );
    } // representada por uma coluna de letra e uma linha numérica)
    // em uma Position (que usa coordenadas numéricas de linha e coluna)
    // 8 - row: Isso transforma a linha. Por exemplo:
    //Se row é 1 (que representa a linha mais próxima das peças brancas), o cálculo se torna 8 - 1 = 7 (que é a linha 7 na matriz interna).
    //column - 'a': Aqui, estamos convertendo a letra da coluna para um número:
    //Por exemplo, se column é 'a', o cálculo se torna 'a' - 'a' = 0.
    //Se column é 'b', o cálculo se torna 'b' - 'a' = 1, e assim por diante até 'h'.
    protected static ChessPosition fromPosition(Position position) {
        return new ChessPosition((char) ('a' - position.getColumn()), (char) (8 - position.getRow()));
    }
    // converter uma posição interna (Position) de volta para a representação de xadrez (ChessPosition).
    //Por exemplo, se position.getColumn() retornar 0, o cálculo se torna 'a' + 0, resultando em 'a'.
    // se position.getRow() retornar 0 (que corresponde à linha mais baixa no sistema interno), o cálculo se torna 8 - 0, resultando em 8.

    @Override
    public String toString() {
        return "" + column + row;
    }
}
