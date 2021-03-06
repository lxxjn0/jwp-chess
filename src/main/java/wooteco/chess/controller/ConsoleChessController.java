package wooteco.chess.controller;

import static wooteco.chess.util.StringUtil.*;
import static wooteco.chess.view.ConsoleInputView.*;
import static wooteco.chess.view.ConsoleOutputView.*;

import java.util.Objects;

import wooteco.chess.domain.chessGame.ChessCommand;
import wooteco.chess.domain.chessGame.ChessGame;
import wooteco.chess.util.ChessBoardRenderer;

public class ConsoleChessController {

	private final ChessGame chessGame;

	public ConsoleChessController(ChessGame chessGame) {
		Objects.requireNonNull(chessGame, "체스 게임이 null입니다.");
		this.chessGame = chessGame;
	}

	public void run() {
		do {
			printChessBoard(ChessBoardRenderer.render(chessGame.getChessBoard()));

			ChessCommand chessCommand = receiveChessCommand();
			playChessGameBy(chessCommand);
		} while (!isEndState());
	}

	private ChessCommand receiveChessCommand() {
		ChessCommand chessCommand = ChessCommand.of(splitChessCommand(inputChessCommand()));

		if (chessCommand.isStartChessCommand()) {
			throw new IllegalArgumentException("start 명령어은 최초 시작 때만 사용 가능합니다.");
		}
		return chessCommand;
	}

	private void playChessGameBy(ChessCommand chessCommand) {
		if (chessCommand.isMoveChessCommand()) {
			chessGame.move(chessCommand);
		}
		if (chessCommand.isStatusChessCommand()) {
			double score = chessGame.status(chessCommand);
			printStatus(chessCommand.getStatusPieceColor(), score);
		}
		if (chessCommand.isEndChessCommand()) {
			chessGame.end();
		}
	}

	private boolean isEndState() {
		if (!chessGame.isEndState()) {
			return false;
		}
		return checkKingCaught();
	}

	private boolean checkKingCaught() {
		if (chessGame.isKingCaught()) {
			printKingCaught(chessGame.getCurrentPieceColor());
		}
		return true;
	}

}
