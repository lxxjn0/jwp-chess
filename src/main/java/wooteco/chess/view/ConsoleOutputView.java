package wooteco.chess.view;

import java.util.List;

import wooteco.chess.domain.chessPiece.pieceType.PieceColor;

public class ConsoleOutputView {

	static final String PROMPT = "> ";

	private static void printNewLine() {
		System.out.println();
	}

	public static void printChessStart() {
		System.out.println(PROMPT + "체스 게임을 시작합니다.");
		System.out.println(PROMPT + "게임 시작 : start");
		System.out.println(PROMPT + "게임 이동 : move source위치 target위치 - 예. move b2 b3");
		System.out.println(PROMPT + "게임 점수 : status 체스 색상 - 예. status white, status black");
		System.out.println(PROMPT + "게임 종료 : end");
	}

	public static void printChessBoard(List<String> renderedChessBoard) {
		printNewLine();
		renderedChessBoard.forEach(System.out::println);
	}

	public static void printStatus(PieceColor statusPieceColor, double score) {
		System.out.println(String.format("%s 점수 : %.1f", statusPieceColor.getColor(), score));
	}

	public static void printKingCaught(PieceColor catchingPieceColor) {
		System.out.println(String.format("%s가 킹을 잡았습니다.", catchingPieceColor.getColor()));
	}

	public static void printChessEnd() {
		System.out.println(PROMPT + "게임 종료!");
	}

}
