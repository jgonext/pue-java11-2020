package maze.jgo;
import static maze.jgo.Constants.ANSI_BLACK;
import static maze.jgo.Constants.ANSI_BLUE;
import static maze.jgo.Constants.ANSI_CYAN;
import static maze.jgo.Constants.ANSI_GREEN;
import static maze.jgo.Constants.ANSI_RESET;
import static maze.jgo.Constants.ANSI_WHITE;
import static maze.jgo.Constants.ANSI_YELLOW;
import static maze.jgo.Constants.BLOCK_GOAL;
import static maze.jgo.Constants.BLOCK_SOLUTION;
import static maze.jgo.Constants.BLOCK_START;
import static maze.jgo.Constants.BLOCK_WALL;
import static maze.jgo.Constants.BLOCK_WAY;

import maze.MazePrinter;
import maze.domain.Maze;

/**
 * 
 * Visualizador de laberintos.
 * 
 * Imprime por pantalla el laberinto, y si aplica, posiciones inicial/final y camino encontrado.
 * 
 * @author Jose
 *
 */
public class JGOMazePrinterImpl implements MazePrinter {
	
	interface PrintBlock {
		void doIt(String ansi, char block);
	}
	
	private PrintBlock printOneBlock = (a,b) -> {
		System.out.print( a );
		System.out.print( b );
		System.out.print( b );
		System.out.print( b );
		System.out.print( ANSI_RESET );
	};

	@Override
	public void print(Maze maze) {
		printHeadTitle(maze.getWidth());
		for (int x=0; x<maze.getHeight(); x++) {
			printHeadLine(x);
			for (int y=0; y<maze.getWidth(); y++) {
				if (maze.isWall(x, y)) printOneBlock.doIt(ANSI_BLACK, BLOCK_WALL);
				else if (maze.isStart(x, y)) printOneBlock.doIt(ANSI_GREEN, BLOCK_START);
				else if (maze.isGoal(x, y)) printOneBlock.doIt(ANSI_BLUE, BLOCK_GOAL);
				else if (maze.isSolution(x, y)) printOneBlock.doIt(ANSI_YELLOW, BLOCK_SOLUTION);
				else printOneBlock.doIt(ANSI_WHITE, BLOCK_WAY);
			}
			System.out.println();
		}
	}
	
	private void printHeadTitle(int w) {
		System.out.print( ANSI_CYAN );
		System.out.print(String.format("%1$3s", ' '));
		for (int y=0; y<w; y++) {
			System.out.print(String.format("%1$3s", y));
		}
		System.out.println( ANSI_RESET );
	}
	
	private void printHeadLine(int x) {
		System.out.print( ANSI_CYAN );
		System.out.print(String.format("%1$3s", x)); 
		System.out.print( ANSI_RESET );
	}
	
	public String toString(Maze maze) {
		StringBuilder sb = new StringBuilder();
		for (int x=0; x<maze.getHeight(); x++) {
			for (int y=0; y<maze.getWidth(); y++) {
				if (maze.isWall(x, y)) sb.append(1);
				else if (maze.isStart(x, y)) sb.append('A');
				else if (maze.isGoal(x, y)) sb.append('B');
				else sb.append(0);
			}
			sb.append('\n');
		}
		return sb.toString();
	}

}