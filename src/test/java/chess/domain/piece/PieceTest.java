package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    @DisplayName("백기물일 경우 소문자로 표시한다.")
    void white() {
        Piece piece = new KingPiece(Color.WHITE);
        assertThat(piece.getName()).isEqualTo("whitek");
    }

    @Test
    @DisplayName("흑기물일 경우 대문자로 표시한다.")
    void black() {
        Piece piece = new KingPiece(Color.BLACK);
        assertThat(piece.getName()).isEqualTo("blackK");
    }
}
