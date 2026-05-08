import java.util.*;

interface IObserver {
    void update(String message);
}

class ConsoleNotifier implements IObserver {
    @Override
    public void update(String message) {
        System.out.println("[Notification]: " + message);
    }
}

class Symbol {
    private char mark;

    public Symbol(char m) {
        this.mark = m;
    }

    public char getMark() {
        return mark;
    }
}

class Board {
    private Symbol[][] grid;
    private int size;
    private Symbol emptyCell;

    // make the board
    public Board(int s) {
        size = s;
        emptyCell = new Symbol('_');
        grid = new Symbol[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = emptyCell;
            }
        }
    }

    // check if cell is empty
    public boolean isCellEmpty(int r, int c) {
        if (r < 0 || r >= size || c < 0 || c >= size) {
            return false;
        }
        return grid[r][c] == emptyCell;
    }

    // place symbol on the board
    public boolean placeMark(int r, int c, Symbol s) {
        if (r < 0 || r >= size || c < 0 || c >= size) {
            return false;
        }
        if (!isCellEmpty(r, c)) {
            return false;
        }
        grid[r][c] = s;
        return true;
    }

    public Symbol getCell(int r, int c) {
        if (r < 0 || r >= size || c < 0 || c >= size) {
            return emptyCell;
        }
        return grid[r][c];
    }

    public int getSize() {
        return size;
    }

    public Symbol getEmptyCell() {
        return emptyCell;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j].getMark() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

// class player
class TictacToePlayer {
    private int playerId;
    private String name;
    private Symbol symbol;
    private int score;

    public TictacToePlayer(int playerId, String name, Symbol symbol) {
        this.playerId = playerId;
        this.name = name;
        this.symbol = symbol;
        this.score = 0;
    }

    // getters and setters
    public int getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
}

// game rules
interface TicTacToeRules {
    boolean isValidMove(Board board, int r, int c);
    boolean checkWinCondition(Board board, Symbol symbol);
    boolean checkDrawCondition(Board board);
}

// standard rules
class StandardTicTacToeRules implements TicTacToeRules {
    @Override
    public boolean isValidMove(Board board, int r, int c) {
        return board.isCellEmpty(r, c);
    }

    @Override
    public boolean checkWinCondition(Board board, Symbol symbol) {
        int size = board.getSize();
        
        // check rows
        for (int i = 0; i < size; i++) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j) != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        // check columns
        for (int j = 0; j < size; j++) {
            boolean win = true;
            for (int i = 0; i < size; i++) {
                if (board.getCell(i, j) != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        // check \ diagonal
        boolean win = true;
        for (int i = 0; i < size; i++) {
            if (board.getCell(i, i) != symbol) {
                win = false;
                break;
            }
        }
        if (win) {
            return true;
        }

        // check / diagonal
        win = true;
        for (int i = 0; i < size; i++) {
            if (board.getCell(i, size - 1 - i) != symbol) {
                win = false;
                break;
            }
        }
        if (win) {
            return true;
        }

        return false;
    }

    // if all cells filled the no winner
    @Override
    public boolean checkDrawCondition(Board board) {
        int size = board.getSize();
        
        boolean draw = true;
        for (int i = 0; i < size; i++) {
            for  (int j = 0; j < size; j++) {
                if (board.getCell(i, j) == board.getEmptyCell()) {
                    draw = false;
                    break;
                }
            }
        }
        return draw;
    }
}

class TicTacToeGame {
    private Board board;
    private Deque<TictacToePlayer> players;
    private TicTacToeRules rules;
    private List<IObserver> observers;
    private boolean gameOver;

    public TicTacToeGame(int size) {
        board = new Board(size);
        players = new ArrayDeque<>();
        rules = new StandardTicTacToeRules();
        observers = new ArrayList<>();
        gameOver = false;
    }

    public void addPlayer(TictacToePlayer p) {
        players.add(p);
    }

    public void addObserver(IObserver o) {
        observers.add(o);
    }

    public void notify(String message) {
        for (IObserver o : observers) {
            o.update(message);
        }
    }

    public void play() {
        if (players.size() < 2) {
            System.out.println("Not enough players to start the game.");
            return;
        }

        notify("Game started");

        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            board.display();

            TictacToePlayer currPlayer = players.peekFirst();
            System.out.println(currPlayer.getName() + "(" + currPlayer.getSymbol().getMark() + ") - Enter the row and column");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // check if moves are valid or not
            if (rules.isValidMove(board, row, col)) {
                board.placeMark(row, col, currPlayer.getSymbol());
                notify(currPlayer.getName() + "has played on " + row + " " + col);

                if (rules.checkWinCondition(board, currPlayer.getSymbol())) {
                    board.display();
                    System.out.println(currPlayer.getName() + " has won the game");

                    currPlayer.incrementScore();

                    notify(currPlayer.getName() + " has won the game");
                    
                    gameOver = true;
                }

                else if (rules.checkDrawCondition(board)) {
                    board.display();
                    System.out.println("The game is a draw");

                    notify("The game is a draw");

                    gameOver = true;
                }

                else {
                    players.removeFirst();
                    players.addLast(currPlayer);
                }
            } else {
                System.out.println("Invalid move");
                notify("Invalid move");
            }
        }
    }
}

enum GameType {
    STANDARD;
}

class TicTacToeFactory {
    public static TicTacToeGame createGame(GameType type, int size) {
        if (type == GameType.STANDARD) {
            return new TicTacToeGame(size);
        }
        return null;
    }
}

public class TicTacToeMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe Game");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the board");
        int size = scanner.nextInt();

        TicTacToeGame game = TicTacToeFactory.createGame(GameType.STANDARD, size);

        IObserver notifier = new ConsoleNotifier();
        game.addObserver(notifier);

        TictacToePlayer player1 = new TictacToePlayer(1, "Madhur", new Symbol('X'));
        TictacToePlayer player2 = new TictacToePlayer(2, "Moltu", new Symbol('O'));

        game.addPlayer(player1);
        game.addPlayer(player2);

        game.play();
        scanner.close();
    }
}