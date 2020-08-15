// The "TicTacToe" class.
import java.awt.*;
import hsa.Console;

public class TicTacToe
{
    static Console c; // The output console
    private static char board[] [] = new char [3] [3];
    public static final char playerSymbol = 'x';
    public static final char comSymbol = 'o';

    public static void main (String[] args)
    {
	c = new Console (37, 150);
	boolean play = true;
	introPage ();
	int type = gameType ();
	while (play)
	{

	    for (int i = 0 ; i < board.length ; i++)
	    {

		for (int j = 0 ; j < board [0].length ; j++)
		{
		    board [i] [j] = '_';
		}
	    }

	    setBackground ();
	    drawBoard ();
	    int turn = firstTurn ();
	    boolean gameOver = false;
	    while (gameOver == false)
	    {
		if (turn == 1)
		{
		    int playerPos[] = playerMove ();
		    if (gameOverChecker (playerPos) == true)
		    {
			gameOver = true;
			displayResult ('x');
		    }
		    else if (tieChecker () == true)
		    {
			gameOver = true;
			displayResult ('t');
		    }

		}
		else
		{
		    int[] comPos;
		    if (type == 1)
			comPos = comRandomMove ();
		    else if (type == 2)
			comPos = makeOptimalMove ();
		    else
			comPos = makePerfectMove ();

		    if (gameOverChecker (comPos) == true)
		    {
			gameOver = true;
			displayResult ('0');
		    }
		    else if (tieChecker () == true)
		    {
			gameOver = true;
			displayResult ('t');
		    }
		}

		turn = changeTurn (turn);
	    }
	    play = playAgain ();
	}

	exitScreen ();
	 try
	    {
		Thread.sleep (1000);
	    }
	    catch (Exception e)
	    {
	    }
	System.exit(-1);
	// Place your program here. 'c' is the output console
    } // main method

    public static void setBackground ()
    {
	c.setColor (new Color (32, 178, 170));
	c.fillRect (0, 0, c.getWidth () - 1, c.getHeight () - 1);
    }

    public static void drawSymbol_0 (int x, int y)
    {
	c.setColor (new Color (32, 178, 170));
	c.fillRect (x + 10, y + 10, 180, 180);
	for (int i = 0 ; i <= 10 ; i++)
	{
	    c.setColor (new Color (175, 238, 238));
	    c.fillArc (x + 10, y + 15, 170, 170, 0, 36 * i);
	    c.setColor (new Color (32, 178, 170));
	    c.fillOval (x + 30, y + 35, 130, 130);
	    try
	    {
		Thread.sleep (40);
	    }
	    catch (Exception e)
	    {
	    }

	}
    }

    public static void drawSymbol_x (int x, int y)
    {
	c.setColor (new Color (32, 178, 170));
	c.fillRect (x + 10, y + 10, 180, 180);
	c.setColor (new Color (175, 238, 238));
	x = x + 5;
	c.fillOval (x + 9, y + 21, 23, 23);

	for (int i = 0 ; i <= 145 ; i++)
	{
	    c.fillRect (x + 13 + i, y + 24 + i, 15, 13);
	    c.fillOval (x + 13 + i, y + 25 + i, 23, 23);
	    try
	    {
		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}
	c.fillOval (x + 155, y + 18, 24, 24);
	for (int i = 0 ; i <= 145 ; i++)
	{
	    c.fillRect (x + 156 - i, y + 22 + i, 20, 15);
	    c.fillOval (x + 156 - i, y + 23 + i, 20, 23);
	    try
	    {
		Thread.sleep (2);
	    }
	    catch (Exception e)
	    {
	    }
	}

	c.fillOval (x + 5, y + 166, 24, 24);

    }

    public static void introPage ()
    {
	c.setColor (new Color (32, 178, 170)); // back ground
	c.fillRect (0, 0, c.getWidth (), c.getHeight ());
	c.setColor (new Color (0, 128, 128)); // title color
	Font myFont = new Font ("Comic Sans Ms", Font.BOLD, 200);
	c.setFont (myFont);
	for (int i = 1 ; i <= 3 ; i++)
	{
	    if (i == 1)
	    {
		c.drawString ("Tic", 100, 400);
	    }
	    else if (i == 2)
	    {
		c.drawString ("Tac", 400, 400);
	    }
	    else if (i == 3)
	    {
		c.drawString ("Toe", 750, 400);
	    }
	    try
	    {
		Thread.sleep (500);
	    }
	    catch (Exception e)
	    {
	    }
	}
	drawSymbol_x (70, 20);
	drawSymbol_0 (350, 20);
	drawSymbol_x (650, 20);
	drawSymbol_0 (950, 20);
	c.setColor (new Color (0, 128, 128));
	myFont = new Font ("Comic Sans Ms", Font.BOLD, 50);
	c.setFont (myFont);
	c.drawString ("Press any key to continue", 300, 500);
	char next = ' ';
	next = c.getChar ();
	c.clear ();
    }

    public static int gameType ()
    {
	setBackground ();
	Font myFont = new Font ("Comic Sans Ms", Font.BOLD, 70);
	c.setFont (myFont);
	c.setColour (new Color (0, 128, 128));
	c.drawString ("Please choose the game type:", 20, 60);
	c.drawString ("Press 1 for Easy", 20, 200);
	c.drawString ("Press 2 for Medium", 20, 300);
	c.drawString ("Press 3 for Hard", 20, 400);
	int type = (int) (c.getChar () - 48);
	while (type != 1 && type != 2 && type != 3)
	{
	    c.drawString ("Please choose the game type:", 20, 60);
	    c.drawString ("Press 1 for Easy", 20, 200);
	    c.drawString ("Press 2 for Medium", 20, 300);
	    c.drawString ("Press 3 for Hard", 20, 400);
	    type = (int) (c.getChar () - 48);
	}
	setBackground ();
	return type;

    }

    public static void drawBoard ()
    {
	c.setColour (new Color (0, 128, 128));
	for (int j = 0 ; j <= 600 ; j++)
	{
	    for (int i = 0 ; i <= 8 ; i++)
	    {
		c.drawLine (300, 0 + i, 300 + j, 0 + i); // h1
		c.drawLine (300 - i, 0, 300 - i, j + 8); // v1
	    }
	    try
	    {
		Thread.sleep (1);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int j = 0 ; j <= 608 ; j++)
	{
	    for (int i = 0 ; i <= 8 ; i++)
	    {

		c.drawLine (300, 600, 300 + j, 600 + i); // h4
		c.drawLine (900 + i, 0, 900 + i, 0 + j); // v4
	    }
	    try
	    {
		Thread.sleep (1);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int j = 0 ; j <= 600 ; j++)
	{
	    for (int i = 0 ; i <= 8 ; i++)
	    {
		c.drawLine (500 - i, 0, 500 - i, 5 + j); // v2
		c.drawLine (700 + i, 0, 700 + i, 5 + j); // v3
	    }
	    try
	    {
		Thread.sleep (1);
	    }
	    catch (Exception e)
	    {
	    }
	}
	for (int j = 0 ; j <= 600 ; j++)
	{
	    for (int i = 0 ; i <= 8 ; i++)
	    {
		c.drawLine (300, 200 + i, 300 + j, 200 + i); // h2
		c.drawLine (300, 400 + i, 300 + j, 400 + i); // h3
	    }
	    try
	    {
		Thread.sleep (1);
	    }
	    catch (Exception e)
	    {
	    }
	}
	Font myFont = new Font ("Helvetica", Font.BOLD, 180);
	c.setFont (myFont);
	c.drawString ("1", 340, 180);
	try
	{
	    Thread.sleep (200);
	}
	catch (Exception e)
	{
	}
	c.drawString ("2", 540, 180);
	try
	{
	    Thread.sleep (200);
	}
	catch (Exception e)
	{
	}
	c.drawString ("3", 740, 180);
	try
	{
	    Thread.sleep (200);
	}
	catch (Exception e)
	{
	}

	c.drawString ("4", 340, 380);
	try
	{
	    Thread.sleep (200);
	}
	catch (Exception e)
	{
	}
	c.drawString ("5", 540, 380);
	try
	{
	    Thread.sleep (200);
	}
	catch (Exception e)
	{
	}
	c.drawString ("6", 740, 380);
	try
	{
	    Thread.sleep (200);
	}
	catch (Exception e)
	{
	}

	c.drawString ("7", 340, 580);
	try
	{
	    Thread.sleep (200);
	}
	catch (Exception e)
	{
	}
	c.drawString ("8", 540, 580);
	try
	{
	    Thread.sleep (200);
	}
	catch (Exception e)
	{
	}
	c.drawString ("9", 740, 580);
	try
	{
	    Thread.sleep (200);
	}
	catch (Exception e)
	{
	}
    }


    public static int firstTurn ()
    {
	Font myFont = new Font ("Comic Sans Ms", Font.BOLD, 30);
	c.setFont (myFont);
	c.setColor (new Color (9, 84, 81));
	c.drawString ("Would you like to go first ? ", 390, 670);
	c.drawString ("Enter 'Y' for yes or 'N' for no", 375, 710);
	int turn = c.getChar ();

	while (turn != 'y' && turn != 'Y' && turn != 'n' && turn != 'N')
	{
	    clearInput ();
	    c.setColor (new Color (9, 84, 81));
	    c.drawString ("Enter 'Y' for yes or 'N' for no", 375, 710);
	    turn = c.getChar ();
	}

	if (turn == 'y' || turn == 'Y')
	{
	    return 1;
	}
	else
	{
	    return 2;
	}
    }


    public static void clearInput ()
    {
	c.setColor (new Color (32, 178, 170));
	c.fillRect (0, 620, c.getWidth () - 1, 400);
    }

    public static int[] playerMove ()
    {
	clearInput ();
	c.setColor (new Color (9, 84, 81));
	c.drawString ("Please enter the square number:", 375, 710);
	char squareNum = c.getChar ();
	int box = (int) (squareNum - 48);
	while (box <= 0 || box > 9)
	{
	    clearInput ();
	    c.setColor (new Color (9, 84, 81));
	    c.drawString ("Please enter the square number:", 375, 710);
	    squareNum = c.getChar ();
	    box = (int) (squareNum - 48);
	}
	int[] pos = convertToPos (box);
	while (board [pos [0]] [pos [1]] != '_')
	{
	    clearInput ();
	    c.setColor (new Color (9, 84, 81));
	    c.drawString ("Please enter the square number:", 375, 710);
	    squareNum = c.getChar ();
	    box = (int) (squareNum - 48);
	    while (box <= 0 || box > 9)
	    {
		clearInput ();
		c.setColor (new Color (9, 84, 81));
		c.drawString ("Please enter the square number:", 375, 710);
		squareNum = c.getChar ();
		box = (int) (squareNum - 48);

	    }
	    pos = convertToPos (box);
	}

	board [pos [0]] [pos [1]] = 'x';
	drawSymbol_x (pos [0] * 200 + 300, pos [1] * 200);
	return pos;
    }


    public static int[] convertToPos (int boxNum)
    {
	int[] pos = new int [2];
	if (boxNum == 1)
	{
	    pos [0] = 0;
	    pos [1] = 0;
	}
	else if (boxNum == 2)
	{
	    pos [0] = 1;
	    pos [1] = 0;
	}
	else if (boxNum == 3)
	{
	    pos [0] = 2;
	    pos [1] = 0;
	}
	else if (boxNum == 4)
	{
	    pos [0] = 0;
	    pos [1] = 1;
	}
	else if (boxNum == 5)
	{
	    pos [0] = 1;
	    pos [1] = 1;
	}
	else if (boxNum == 6)
	{
	    pos [0] = 2;
	    pos [1] = 1;
	}
	else if (boxNum == 7)
	{
	    pos [0] = 0;
	    pos [1] = 2;
	}
	else if (boxNum == 8)
	{
	    pos [0] = 1;
	    pos [1] = 2;
	}
	else if (boxNum == 9)
	{
	    pos [0] = 2;
	    pos [1] = 2;
	}

	return pos;
    }

    public static int changeTurn (int t)
    {
	if (t == 1)
	{
	    return 2;
	}
	else
	{
	    return 1;
	}
    }

    // easy mode
    public static int[] comRandomMove ()
    {

	int rowM = (int) (Math.random () * 3);
	int colM = (int) (Math.random () * 3);

	while (board [rowM] [colM] != '_')
	{
	    rowM = (int) (Math.random () * 3);
	    colM = (int) (Math.random () * 3);
	}
	board [rowM] [colM] = 0;
	int[] pos = {rowM, colM};
	drawSymbol_0 (pos [0] * 200 + 300, pos [1] * 200);
	return pos;
    }

    // medium mode
    public static int[] makeOptimalMove ()
    {

	int[] pos = new int [2];
	int[] comWin = checkComWin ();
	int[] playerWin = checkPlayerWin ();

	if (comWin [0] == 1)
	{

	    pos [0] = comWin [1];
	    pos [1] = comWin [2];
	}
	else if (playerWin [0] == 1 && comWin [0] == 0)
	{
	    pos [0] = playerWin [1];
	    pos [1] = playerWin [2];

	}
	else if (playerWin [0] == 0 && comWin [0] == 0)
	{
	    pos [0] = (int) (Math.random () * 3);
	    pos [1] = (int) (Math.random () * 3);
	    while (board [pos [0]] [pos [1]] != '_')
	    {
		pos [0] = (int) (Math.random () * 3);
		pos [1] = (int) (Math.random () * 3);
	    }
	}

	board [pos [0]] [pos [1]] = '0';
	drawSymbol_0 (pos [0] * 200 + 300, pos [1] * 200);

	return pos;

    }

    // hard mode
    public static int[] makePerfectMove ()
    {

	int[] pos = new int [2];
	int[] comWin = checkComWin ();
	int[] playerWin = checkPlayerWin ();

	if (comWin [0] == 1)
	{

	    pos [0] = comWin [1];
	    pos [1] = comWin [2];
	}
	else if (playerWin [0] == 1 && comWin [0] == 0)
	{
	    pos [0] = playerWin [1];
	    pos [1] = playerWin [2];

	}
	else if (playerWin [0] == 0 && comWin [0] == 0)
	{

	    pos [0] = (int) (Math.random () * 3);
	    pos [1] = (int) (Math.random () * 3);

	    while (board [pos [0]] [pos [1]] != '_')
	    {
		pos [0] = (int) (Math.random () * 3);
		pos [1] = (int) (Math.random () * 3);
	    }


	    if (board [0] [0] == 'x' && board [2] [2] == '_')
	    {
		pos [0] = 2;
		pos [1] = 2;
	    }
	    else if (board [0] [2] == 'x' && board [2] [0] == '_')
	    {
		pos [0] = 2;
		pos [1] = 0;
	    }
	    else if (board [2] [2] == 'x' && board [0] [0] == '_')
	    {
		pos [0] = 0;
		pos [1] = 0;
	    }
	    else if (board [2] [0] == 'x' && board [0] [2] == '_')
	    {
		pos [0] = 0;
		pos [1] = 2;
	    }
	    else if (board [1] [1] == '_')
	    {
		pos [0] = 1;
		pos [1] = 1;
	    }
	    else if (board [0] [0] == '_')
	    {
		pos [0] = 0;
		pos [1] = 0;
	    }
	    else if (board [0] [2] == '_')
	    {
		pos [0] = 0;
		pos [1] = 2;
	    }
	    else if (board [2] [0] == '_')
	    {
		pos [0] = 2;
		pos [1] = 0;
	    }
	    else if (board [2] [2] == '_')
	    {
		pos [0] = 2;
		pos [1] = 2;
	    }

	}

	board [pos [0]] [pos [1]] = '0';
	drawSymbol_0 (pos [0] * 200 + 300, pos [1] * 200);

	return pos;
    }

    public static int[] checkComWin ()
    {

	// index 0 - 0 if player can not win, 1 if player can win
	// index 1,2 - value of possible win position

	char symbol = '0';
	int[] winInfo = new int [3];
	winInfo [0] = 0;
	// check for possible row win
	for (int row = 0 ; row < board.length ; row++)
	{
	    if (board [row] [0] == symbol && board [row] [1] == symbol && board [row] [2] == '_')
	    {
		winInfo [0] = 1;
		winInfo [1] = row;
		winInfo [2] = 2;
		break;
	    }
	    if (board [row] [0] == symbol && board [row] [2] == symbol && board [row] [1] == '_')
	    {
		winInfo [0] = 1;
		winInfo [1] = row;
		winInfo [2] = 1;
		break;
	    }
	    if (board [row] [1] == symbol && board [row] [2] == symbol && board [row] [0] == '_')
	    {
		winInfo [0] = 1;
		winInfo [1] = row;
		winInfo [2] = 0;
		break;
	    }
	}

	// check for possible column win
	for (int col = 0 ; col < board.length ; col++)
	{

	    if (board [0] [col] == symbol && board [1] [col] == symbol && board [2] [col] == '_')
	    {
		winInfo [0] = 1;
		winInfo [1] = 2;
		winInfo [2] = col;
		break;

	    }
	    if (board [0] [col] == symbol && board [2] [col] == symbol && board [1] [col] == '_')
	    {
		winInfo [0] = 1;
		winInfo [1] = 1;
		winInfo [2] = col;
		break;

	    }
	    if (board [1] [col] == symbol && board [2] [col] == symbol && board [0] [col] == '_')
	    {
		winInfo [0] = 1;
		winInfo [1] = 0;
		winInfo [2] = col;
		break;
	    }
	}

	// check diagonal left (\)
	if (board [0] [0] == symbol && board [1] [1] == symbol && board [2] [2] == '_')
	{
	    winInfo [0] = 1;
	    winInfo [1] = 2;
	    winInfo [2] = 2;

	}
	if (board [0] [0] == symbol && board [2] [2] == symbol && board [1] [1] == '_')
	{

	    winInfo [0] = 1;
	    winInfo [1] = 1;
	    winInfo [2] = 1;
	}
	if (board [1] [1] == symbol && board [2] [2] == symbol && board [0] [0] == '_')
	{

	    winInfo [0] = 1;
	    winInfo [1] = 0;
	    winInfo [2] = 0;
	}

	// check diagonal right(/)
	if (board [0] [2] == symbol && board [1] [1] == symbol && board [2] [0] == '_')
	{
	    winInfo [0] = 1;
	    winInfo [1] = 2;
	    winInfo [2] = 0;

	}
	if (board [2] [0] == symbol && board [0] [2] == symbol && board [1] [1] == '_')
	{
	    winInfo [0] = 1;
	    winInfo [1] = 1;
	    winInfo [2] = 1;

	}
	if (board [1] [1] == symbol && board [2] [0] == symbol && board [0] [2] == '_')
	{
	    winInfo [0] = 1;
	    winInfo [1] = 0;
	    winInfo [2] = 2;
	}

	return winInfo;

    }

    public static int[] checkPlayerWin ()
    {

	// index 0 - 0 if player can not win, 1 if player can win
	// index 1,2 - value of possible win position

	char symbol = 'x';
	int[] winInfo = new int [3];
	winInfo [0] = 0;

	// check for possible row win
	for (int row = 0 ; row < board.length ; row++)
	{
	    if (board [row] [0] == symbol && board [row] [1] == symbol && board [row] [2] == '_')
	    {
		winInfo [0] = 1;
		winInfo [1] = row;
		winInfo [2] = 2;
		break;
	    }
	    if (board [row] [0] == symbol && board [row] [2] == symbol && board [row] [1] == '_')
	    {
		winInfo [0] = 1;
		winInfo [1] = row;
		winInfo [2] = 1;
		break;
	    }
	    if (board [row] [1] == symbol && board [row] [2] == symbol && board [row] [0] == '_')
	    {
		winInfo [0] = 1;
		winInfo [1] = row;
		winInfo [2] = 0;
		break;
	    }
	}

	// check for possible column win
	for (int col = 0 ; col < board.length ; col++)
	{

	    if (board [0] [col] == symbol && board [1] [col] == symbol && board [2] [col] == '_')
	    {
		winInfo [0] = 1;
		winInfo [1] = 2;
		winInfo [2] = col;
		break;

	    }
	    if (board [0] [col] == symbol && board [2] [col] == symbol && board [1] [col] == '_')
	    {
		winInfo [0] = 1;
		winInfo [1] = 1;
		winInfo [2] = col;
		break;

	    }
	    if (board [1] [col] == symbol && board [2] [col] == symbol && board [0] [col] == '_')
	    {
		winInfo [0] = 1;
		winInfo [1] = 0;
		winInfo [2] = col;
		break;
	    }
	}

	// check diagonal left (\)
	if (board [0] [0] == symbol && board [1] [1] == symbol && board [2] [2] == '_')
	{
	    winInfo [0] = 1;
	    winInfo [1] = 2;
	    winInfo [2] = 2;

	}
	if (board [0] [0] == symbol && board [2] [2] == symbol && board [1] [1] == '_')
	{

	    winInfo [0] = 1;
	    winInfo [1] = 1;
	    winInfo [2] = 1;
	}
	if (board [1] [1] == symbol && board [2] [2] == symbol && board [0] [0] == '_')
	{

	    winInfo [0] = 1;
	    winInfo [1] = 0;
	    winInfo [2] = 0;
	}

	// check diagonal right(/)
	if (board [0] [2] == symbol && board [1] [1] == symbol && board [2] [0] == '_')
	{
	    winInfo [0] = 1;
	    winInfo [1] = 2;
	    winInfo [2] = 0;

	}
	if (board [2] [0] == symbol && board [0] [2] == symbol && board [1] [1] == '_')
	{
	    winInfo [0] = 1;
	    winInfo [1] = 1;
	    winInfo [2] = 1;

	}
	if (board [1] [1] == symbol && board [2] [0] == symbol && board [0] [2] == '_')
	{
	    winInfo [0] = 1;
	    winInfo [1] = 0;
	    winInfo [2] = 2;
	}

	return winInfo;

    }

    public static boolean gameOverChecker (int[] pos)
    {

	int rMove = pos [0];
	int cMove = pos [1];
	boolean gameOver = false;

	// Check horizontal
	if (board [0] [cMove] == board [1] [cMove] && board [0] [cMove] == board [2] [cMove])
	{
	    gameOver = true;
	}

	// check vertical
	if (board [rMove] [0] == board [rMove] [1] && board [rMove] [0] == board [rMove] [2])
	{
	    gameOver = true;
	}

	// Check diagonal

	if (board [0] [0] == board [1] [1] && board [0] [0] == board [2] [2] && board [1] [1] != '_')
	{
	    gameOver = true;
	}
	if (board [0] [2] == board [1] [1] && board [0] [2] == board [2] [0] && board [1] [1] != '_')
	{
	    gameOver = true;
	}

	return gameOver;
    }

    public static boolean tieChecker ()
    {
	boolean tie = true;
	for (int i = 0 ; i < board.length ; i++)
	{
	    for (int j = 0 ; j < board [0].length ; j++)
	    {
		if (board [i] [j] == '_')
		{
		    tie = false;
		    break;
		}
	    }
	}
	return tie;
    }


    public static void clearBoard ()
    {
	c.setColor (new Color (32, 178, 170));
	c.fillRect (290, 0, 630, 620);
    }

    public static void displayResult (char symbol)
    {
	try
	{
	    Thread.sleep (1000);
	}
	catch (Exception e)
	{

	}
	clearInput ();
	clearBoard ();
	Font myFont = new Font ("Comic Sans Ms", Font.BOLD, 100);
	c.setFont (myFont);
	c.setColor (new Color (0, 128, 128));
	if (symbol == 'x')
	{
	    c.drawString ("X Wins", 430, 320);
	}
	else if (symbol == '0')
	{
	    c.drawString ("O Wins", 430, 320);
	}
	else
	{
	    c.drawString ("Tie Game", 390, 320);
	}
	myFont = new Font ("Comic Sans Ms", Font.BOLD, 50);
	c.setFont (myFont);
	c.drawString ("Press any key to continue", 295, 500);
	char next = ' ';
	next = c.getChar ();
    }

    public static boolean playAgain ()
    {
	clearInput ();
	clearBoard ();
	Font myFont = new Font ("Comic Sans Ms", Font.BOLD, 60);
	c.setFont (myFont);
	c.setColor (new Color (0, 128, 128));
	c.drawString ("Would you like to play Again ? ", 170, 300);
	c.drawString ("Enter 'Y' for yes or 'N' for no", 150, 530);
	int turn = c.getChar ();

	while (turn != 'y' && turn != 'Y' && turn != 'n' && turn != 'N')
	{
	    c.drawString ("Enter 'Y' for yes or 'N' for no", 150, 530);
	    turn = c.getChar ();
	}

	if (turn == 'y' || turn == 'Y')
	    return true;
	else
	    return false;
    }

    public static void exitScreen ()
    {
	setBackground ();
	c.setColor (new Color (0, 128, 128));
	Font myFont = new Font ("Comic Sans Ms", Font.BOLD, 100);
	c.setFont (myFont);
	for (int i = 1 ; i <= 3 ; i++)
	{
	    if (i == 1)
	    {
		c.drawString ("Thanks", 100, 400);
	    }
	    else if (i == 2)
	    {
		c.drawString ("For", 500, 400);
	    }
	    else if (i == 3)
	    {
		c.drawString ("Playing!", 700, 400);
	    }
	    try
	    {
		Thread.sleep (100);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }
} // TicTacToe class
