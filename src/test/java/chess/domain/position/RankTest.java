package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @Test
    @DisplayName("Rank에 1~8이 있는지 확인한다.")
    void contain() {
        assertThat(Rank.values()).containsOnly(Rank.ONE, Rank.TWO, Rank.THREE, Rank.FOUR,
                Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT);
    }

    @Test
    @DisplayName("값을 이용해 Rank를 찾는다.")
    void findRank() {
        assertThat(Rank.from(7)).isEqualTo(Rank.SEVEN);
    }

    @Test
    @DisplayName("1~8 이외의 값이 들어오는 경우 예외를 발생시킨다.")
    void exception() {
        assertThatThrownBy(() -> Rank.from(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 Rank 값 입니다.");
    }

    @Test
    @DisplayName("Rank를 1 증가시킨다.")
    void plus() {
        Rank rank = Rank.from(5);
        assertThat(rank.move(1)).isEqualTo(Rank.SIX);
    }

    @Test
    @DisplayName("Rank를 1 감소시킨다.")
    void minus() {
        Rank rank = Rank.from(5);
        assertThat(rank.move(-1)).isEqualTo(Rank.FOUR);
    }

}
