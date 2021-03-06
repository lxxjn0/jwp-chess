package wooteco.chess.util;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import wooteco.chess.domain.chessBoard.ChessBoard;
import wooteco.chess.domain.position.ChessFile;
import wooteco.chess.domain.position.ChessRank;
import wooteco.chess.domain.position.Position;

public class ChessBoardRenderer {

	private static final String EMPTY_SPACE = "";
	private static final String DELIMITER = " ";
	private static final String RANK_APPEND_FORMAT = "%s	%s";
	private static final String EMPTY_POSITION = ".";

	public static List<String> render(final ChessBoard chessBoard) {
		Objects.requireNonNull(chessBoard, "체스 보드가 null입니다.");
		final List<String> renderedChessBoard = renderEachChessRankFrom(chessBoard);

		Collections.reverse(renderedChessBoard);
		appendChessFileName(renderedChessBoard);
		return renderedChessBoard;
	}

	private static List<String> renderEachChessRankFrom(final ChessBoard chessBoard) {
		return Arrays.stream(ChessRank.values())
			.map(chessRank -> renderChessRankFrom(chessBoard, chessRank))
			.collect(toList());
	}

	private static String renderChessRankFrom(final ChessBoard chessBoard, final ChessRank chessRank) {
		return Arrays.stream(ChessFile.values())
			.map(chessFile -> renderChessPieceFrom(chessBoard, chessRank, chessFile))
			.collect(collectingAndThen(joining(DELIMITER),
				renderedRank -> String.format(RANK_APPEND_FORMAT, renderedRank, chessRank)));
	}

	private static String renderChessPieceFrom(final ChessBoard chessBoard, final ChessRank chessRank,
		final ChessFile chessFile) {
		final Position renderingPosition = Position.of(chessFile, chessRank);

		if (chessBoard.isChessPieceOn(renderingPosition)) {
			return chessBoard.getChessPieceNameOn(renderingPosition);
		}
		return EMPTY_POSITION;
	}

	private static void appendChessFileName(final List<String> renderedChessBoard) {
		renderedChessBoard.add(EMPTY_SPACE);
		renderedChessBoard.add(Arrays.stream(ChessFile.values())
			.map(ChessFile::toString)
			.collect(joining(DELIMITER)));
	}

}
