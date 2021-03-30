package chess.repository.room;

import chess.domain.dto.PieceDto;
import chess.domain.dto.RoomDto;
import chess.domain.game.Room;
import chess.repository.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JdbcRoomRepository implements RoomRepository {

    private final ConnectionUtil connectionUtil;

    public JdbcRoomRepository() {
        this.connectionUtil = new ConnectionUtil();
    }

    @Override
    public long insert(long userId, String name, Room room) throws SQLException {
        int userIdIdx = 1;
        int nameIdx = 2;
        int stateIdx = 3;
        int currentTeamIdx = 4;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "INSERT INTO rooms (userid, name, state, currentteam) VALUES (?, ?, ?, ?)";
            conn = connectionUtil.getConnection();
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(userIdIdx, userId);
            ps.setString(nameIdx, name);
            ps.setString(stateIdx, room.state().getValue());
            ps.setString(currentTeamIdx, room.currentTeam().getValue());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
            throw new IllegalArgumentException("[ERROR] insert - Piece정보를 DB에 저장하지 못했습니다.");
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void update(long roomId, String name, Room room) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String query = "UPDATE rooms SET name = ?, state = ?, currentteam = ? WHERE id = ?";
            conn = connectionUtil.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, room.state().getValue());
            ps.setString(3, room.currentTeam().getValue());
            ps.setLong(4, roomId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public RoomDto findRoomById(long roomId) throws SQLException {
        Connection conn = null;
        Statement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM rooms WHERE id = " + roomId;
            conn = connectionUtil.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(query);

            if (rs.next()) {
                long id = rs.getLong("id");
                long userid = rs.getLong("userid");
                String name = rs.getString("name");
                String state = rs.getString("state");
                String currentTeam = rs.getString("currentteam");
                Timestamp createdAt = rs.getTimestamp("created_at");
                return new RoomDto(id, userid, name, state, currentTeam, createdAt);
            }
            throw new IllegalArgumentException("[ERROR] findRoomById - DB로부터 Room정보를 가져오지 못했습니다.");
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    @Override
    public List<RoomDto> findRoomsByUserId(long userId) throws SQLException {
        Connection conn = null;
        Statement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM rooms WHERE userid = " + userId;
            conn = connectionUtil.getConnection();
            ps = conn.createStatement();
            rs = ps.executeQuery(query);

            List<RoomDto> result = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                long userid = rs.getLong("userid");
                String name = rs.getString("name");
                String state = rs.getString("state");
                String currentTeam = rs.getString("currentteam");
                Timestamp createdAt = rs.getTimestamp("created_at");
                result.add(new RoomDto(id, userid, name, state, currentTeam, createdAt));
            }
            return result;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}
