package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.exception.InvalidTurnException;
import chess.domain.game.Score;
import chess.domain.game.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class Playing implements State {
	private Board board;
	private Turn turn;

	public Playing(Board board, Turn turn) {
		this.board = board;
		this.turn = turn;
	}

	@Override
	public State start() {
		throw new UnsupportedOperationException();
	}

	@Override
	public State end() {
		return new Finished();
	}

	@Override
	public State move(Position source, Position target) {
		Piece sourcePiece = board.findPiece(source);
		Piece targetPiece = board.findPiece(target);
		validateTurn(sourcePiece);
		sourcePiece.move(targetPiece);
		if (board.isKingDead()) {
			return new Finished();
		}
		nextTurn();
		return this;
	}

	private void validateTurn(Piece piece) {
		if (piece.isDifferentColor(turn.getColor())) {
			throw new InvalidTurnException();
		}
	}

	private void nextTurn() {
		turn = turn.next();
	}

	@Override
	public Board board() {
		return board;
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public Score score(Color color) {
		return Score.calculate(board.findPiecesByColor(color));
	}
}
