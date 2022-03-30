package chess.domain.move;

import chess.domain.position.File;
import chess.domain.position.Rank;
import java.util.Arrays;

public enum Direction {

    EAST(1, 0),
    WEST(-1, 0),
    NORTH(0, 1),
    SOUTH(0, -1),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),
    KNIGHT_EAST_LEFT(2, 1),
    KNIGHT_EAST_RIGHT(2, -1),
    KNIGHT_WEST_LEFT(-2, 1),
    KNIGHT_WEST_RIGHT(-2, -1),
    KNIGHT_NORTH_LEFT(-1, 2),
    KNIGHT_NORTH_RIGHT(1, 2),
    KNIGHT_SOUTH_LEFT(1, -2),
    KNIGHT_SOUTH_RIGHT(-1, -2),
    NONE(0, 0),
    ;

    private final int fileDistance;
    private final int rankDistance;

    Direction(final int fileDistance, final int rankDistance) {
        this.fileDistance = fileDistance;
        this.rankDistance = rankDistance;
    }

    public static Direction of(final int fileDistance, final int rankDistance) {
        return Arrays.stream(values())
                .filter(value -> isSameDirection(value, fileDistance, rankDistance))
                .findFirst()
                .orElse(NONE);
    }

    private static boolean isSameDirection(final Direction direction, final int fileDistance, final int rankDistance) {
        return Math.atan2(direction.fileDistance, direction.rankDistance) == Math.atan2(fileDistance, rankDistance);
    }

    public File moveFile(final File file) {
        return file.move(fileDistance);
    }

    public Rank moveRank(final Rank rank) {
        return rank.move(rankDistance);
    }
}
