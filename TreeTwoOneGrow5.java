// Arihant Kaul
// 4/19/2021
// Game Project

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Image;

import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;
import java.io.FileNotFoundException;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;	
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TreeTwoOneGrow5
{
	public static void main (String [] args)
	{
		TreeTwoOneGrow5 ttog = new TreeTwoOneGrow5();
		ttog.run();
	}
	
	public void run()
	{
		JFrame ttogFrame = new JFrame ("Tree Two One Grow!");
		ttogFrame.setSize(800, 600);
		ttogFrame.setLocation(10, 0);
		ttogFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		TreeTwoOneGrowPanel ttogp = new TreeTwoOneGrowPanel();
		ttogFrame.getContentPane().add(ttogp);
		ttogFrame.setVisible(true);
	}
}

// Class with JButton and Menu for all cards
class TreeTwoOneGrowPanel extends JPanel
{
	private JButton exit; // JButton for leaving the game
	
	// adds the button and the panels to this panel
	public TreeTwoOneGrowPanel()
	{
		setLayout(new BorderLayout());
		
		MenuMakerPanel mmp = new MenuMakerPanel();
		add(mmp, BorderLayout.NORTH);
		
		ExitButtonPanel ebp = new ExitButtonPanel();
		ebp.setPreferredSize(new Dimension (50, 50));
		add(ebp, BorderLayout.SOUTH);
		
		SwitchCardsPanel scp = new SwitchCardsPanel();
		add(scp, BorderLayout.CENTER);
	}
	
	// Panel with exit button so that it is not full size
	class ExitButtonPanel extends JPanel implements ActionListener
	{
		public ExitButtonPanel()
		{
			setLayout(new FlowLayout(FlowLayout.CENTER));
			
			exit = new JButton("Exit");  
			add(exit);
			exit.addActionListener(this);
		}
		
		// closes window if button pressed
		public void actionPerformed (ActionEvent evt)
		{
			String command = evt.getActionCommand();
			if (command.equals("Exit"))
			{
				System.exit(0);
			}
		}	
	}	
	
	// Panel with "Menu"
	class MenuMakerPanel extends JPanel implements ActionListener
	{
		/* JFrame containing the instructions; pops out when instructions
		 * button is clicked
		 */
		private JFrame instructionsFrame;
		
		/* JFrame containing the high scores; pops out when high score
		 * button is clicked
		 */
		private JFrame scoresFrame;
		
		/* Puts all of the JButtons together in a way that looks like a 
		 * horizontal menu
		 */
		public MenuMakerPanel()
		{
			setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			
			/** Adds the instructions panel to the instructions frame*/
			InfoPanel ip = new InfoPanel();
			
			instructionsFrame = new JFrame("Information and Instructions");
			instructionsFrame.setDefaultCloseOperation(instructionsFrame.DISPOSE_ON_CLOSE);
			instructionsFrame.setSize(800, 600);
			instructionsFrame.setLocation(300, 300);
			instructionsFrame.getContentPane().add(ip);
			instructionsFrame.setVisible(false);
			
			/** Adds the high scores panel to the high scores frame*/
			ScoresPanel sp = new ScoresPanel();
			
			scoresFrame = new JFrame("Scores");
			scoresFrame.setDefaultCloseOperation(scoresFrame.DISPOSE_ON_CLOSE);
			scoresFrame.setSize(800, 600);
			scoresFrame.setLocation(300, 300);
			scoresFrame.getContentPane().add(sp);
			scoresFrame.setVisible(false);
			
			JButton instructions = new JButton("Instructions");
			instructions.addActionListener(this);
			add(instructions);
			
			JButton scores = new JButton("Recent Scores");
			scores.addActionListener(this);
			add(scores);
			
			JButton quit = new JButton("Quit");
			quit.addActionListener(this);
			add(quit);
		}
		
		/* actionPerformed for three JButtons*/
		public void actionPerformed (ActionEvent evt)
		{
			String command = evt.getActionCommand();
			
			/* closes if quit button is pressed */
			if (command.equals("Quit"))
			{
				System.exit(1);
			}
			/* makes the instructions frame visible if instructions button
			 * is pressed
			 */
			else if (command.equals("Instructions"))
			{
				instructionsFrame.setVisible(true);
			}
			/* makes the high scores frame visible if high score button is pressed*/
			else if (command.equals("High Scores"))
			{
				scoresFrame.setVisible(true);
			}
		}
	}
}

/* Class containing CardLayout with all of the other panels*/
class SwitchCardsPanel extends JPanel
{	
	private CardLayout ttogCards; // CardLayout for switching between panels
	
	public SwitchCardsPanel()
	{
		ttogCards = new CardLayout();
		setLayout(ttogCards);
			
		StartPanel sp = new StartPanel(ttogCards, this);
		add(sp, "Start Panel");

		InfoPanel ip = new InfoPanel(ttogCards, this);
		add(ip, "Info Panel");
				
		GamePanel1 gp1 = new GamePanel1(ttogCards, this);
		add(gp1, "Game Panel 1");
	}
}

// starting panel, nested to switch to other card
class StartPanel extends JPanel implements ActionListener
{
	private String pictName; // name of the picture that is added to panel
	private Image startMenuTree; // Tree image
	private JButton start, info; // start JButton leads to game panels, info JButton to info panel
	private SwitchCardsPanel scp; // allows for switching between cards
	private CardLayout cards; // allows for switching between cards
	
	/* Adds start button and info button to the Start Panel */
	public StartPanel(CardLayout cardsIn, SwitchCardsPanel scpIn)
	{
		setLayout(null);
		setBackground(Color.BLACK);

		pictName = "ForestPic.jpeg";
		
		startMenuTree = getTreePic();
		
		scp = scpIn;
		cards = cardsIn;
		
		start = new JButton("Start");
		add(start);
		start.setBounds(350, 275, 100, 50);
		start.addActionListener(this);
		start.setBackground(Color.BLUE);
		start.setForeground(Color.WHITE);
		
		info = new JButton("Info");
		add(info);
		info.setBounds(350, 350, 100, 50);
		info.addActionListener(this);
		info.setBackground(Color.BLUE);
		info.setForeground(Color.WHITE);
	}
	
	/* returns picture given name of file */
	public Image getTreePic() 
	{
		File imgFile = new File(pictName);
		Image picture = null;
		try
		{
			picture = ImageIO.read(imgFile);
		}
		catch (IOException e)
		{
			System.err.println("\n\n" + pictName + " can't be found.\n\n");
			e.printStackTrace();
		}
		
		return picture;
	}
	
	/* draws tree image at specified coordinates*/
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(startMenuTree, 30, 30, 720, 230, this); // Leaves 30 px margin on left and right side, and scales up picture horizontally
	}
	
	/* goes to specified panel */
	public void actionPerformed (ActionEvent evt)
	{
		String command = evt.getActionCommand();
		
		if (command.equals("Info"))
		{
			cards.show(scp, "Info Panel"); // goes to info panel
		}
		else if (command.equals("Start"))
		{
			cards.show(scp, "Game Panel 1");
		}	
	}
}

/* JPanel with information on how to play the game and the answers to all the questions*/
class InfoPanel extends JPanel implements ActionListener
{
	private String information; // Gives the information on the correct answers
	private JLabel infoPanelHeader; // Describes the panel
	private JTextArea infoAndInstructionsArea; // Display the message that teaches the user how to play
	private String howToPlay; // String that will contain the message for the JTextArea
	private CardLayout cards; // CardLayout for switching back to home page once user is done reading this
	private SwitchCardsPanel scp; // SwitchCardsPanel for switching back to home page
	
	/* Adds the JLabel and the Text Area; Constructor overridden so that
	 * it can accept CardLayout and SwitchCardsPanel
	 */
	public InfoPanel(CardLayout cardsIn, SwitchCardsPanel scpIn)
	{
		setLayout(new BorderLayout());
		
		cards = cardsIn;
		scp = scpIn;
		
		infoPanelHeader = new JLabel("Informational Panel", JLabel.CENTER);
		add(infoPanelHeader, BorderLayout.NORTH);
		
		addGameInfo();
		howToPlay = getPlayInfo();
		infoAndInstructionsArea = new JTextArea(howToPlay, 10, 5);
		infoAndInstructionsArea.setLineWrap(true);
		infoAndInstructionsArea.setWrapStyleWord(true);
		JScrollPane scroller = new JScrollPane(infoAndInstructionsArea);
		add(scroller, BorderLayout.CENTER);
		
		JButton goBack = new JButton("Back");
		goBack.addActionListener(this);
		add(goBack, BorderLayout.SOUTH);
	}
	
	/* Constructor that does not receive CardLayout and SwitchCardsPanel*/
	public InfoPanel()
	{
		setLayout(new BorderLayout());
		
		infoPanelHeader = new JLabel("Informational Panel", JLabel.CENTER);
		add(infoPanelHeader, BorderLayout.NORTH);
		
		addGameInfo();
		howToPlay = getPlayInfo();
		infoAndInstructionsArea = new JTextArea(howToPlay, 10, 5);
		infoAndInstructionsArea.setLineWrap(true);
		infoAndInstructionsArea.setWrapStyleWord(true);
		JScrollPane scroller = new JScrollPane(infoAndInstructionsArea);
		add(scroller, BorderLayout.CENTER);
	}

	/* String containing all the information needed to play the game*/
	public String getPlayInfo()
	{
		String playInfo = "Welcome to TreeTwoOneGrow! This is a botany game, designed to "
			+ "teach you tips and tricks of growing trees. To play, you will have to answer"
			+ " five multiple choice questions about plants. After you answer these"
			+ " questions, you will get to grow a tree.\n\n If you answer three or more"
			+ " questions correctly, you will get to grow a tree of your choice in"
			+ " pleasant conditions. If you answer two or less questions correctly,"
			+ " you will be given a random tree to grow in harsher conditions. \n\n"
			+ "To start the game, click on the back button or close this window.\n\n"
			+ information;
			
		return playInfo;
	}
	
	public void addGameInfo()
	{
		Scanner readQuestions = null;
		String questionsTextFile = "BotanyQuestionsFile.txt";
		information = "";
		
		File botanyQuestionsFile = new File(questionsTextFile);
		try
		{
			readQuestions = new Scanner(botanyQuestionsFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.err.println("ERROR: Unable to find " + questionsTextFile);
		}
		
		for (int i = 0; i < 4; i++)
		{
			readQuestions.nextLine();
		}
		
		while (readQuestions.hasNext())
		{
			information += readQuestions.nextLine() + "\n";
		}
	}
	
	/* Goes back to home page*/
	public void actionPerformed(ActionEvent evt)
	{
		cards.first(scp);
	}
}
	
/* First game panel; has questions*/
class GamePanel1 extends JPanel
{
	private CardLayout cards; // For switching between cards
	private SwitchCardsPanel scp; // For going to the next card
	
	/* Constructor that uses an ImageIcon to add an Image and adds the Questions
	 * JPanel to the West
	 */
	public GamePanel1(CardLayout cardsIn, SwitchCardsPanel scpIn)
	{
		setLayout(new BorderLayout());
		
		cards = cardsIn;
		scp = scpIn;
		
		JLabel forestImage = new JLabel();
		forestImage.setIcon(new ImageIcon("ForestPic.jpeg"));
		ImageIcon forestImageIcon = (ImageIcon) forestImage.getIcon();
		Image forestTempImage = forestImageIcon.getImage();
		Image forestScaledImage = forestTempImage.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
		forestImage.setIcon(new ImageIcon(forestScaledImage));
		add(forestImage, BorderLayout.EAST);	
			
		QuestionsPanel qp = new QuestionsPanel();
		add(qp, BorderLayout.WEST);
	}
	
	/* JPanel containing questions and answers*/
	class QuestionsPanel extends JPanel implements ActionListener
	{
		private JLabel question; // JLabel with question to be answered
		
		private JCheckBox ans1, ans2, ans3, ans4; // Checkboxes which user clicks to show which answers they think are correct
		
		private JButton submit; // JButton for letting user submit their answers
		
		private int questionsAnswered; // the number of questions that have already been answered
		
		private int [] alreadyAnswered; /* int array containing the numbers of the questions
										that have already been answered */
										
		private int questionsCorrect; // the number of questions that have been answered correctly
		
		private boolean [] answersCorrect; /* an array containing the order of 
			correct answers for the current question: ex: {true, false, false, true} */
		
		/* Constructor that creates JLabel and CheckBoxes with question and answer*/
		public QuestionsPanel()
		{
	
			Color brown = new Color (118, 57, 49);
			
			setLayout(new GridLayout(6, 1));

			questionsAnswered = 0;
			questionsCorrect = 0;
			alreadyAnswered	= new int [5];
		
			question = new JLabel("question");
			add(question);
			question.setForeground(brown);
			
			ans1 = new JCheckBox("Answer 1");
			ans1.setSelected(false);
			add(ans1);
			ans1.setBackground(brown);
			ans1.setForeground(Color.GREEN);
			
			ans2 = new JCheckBox("Answer 2");
			ans2.setSelected(false);
			add(ans2);
			ans2.setBackground(brown);
			ans2.setForeground(Color.GREEN);

			ans3 = new JCheckBox("Answer 3");
			ans3.setSelected(false);
			add(ans3);
			ans3.setBackground(brown);
			ans3.setForeground(Color.GREEN);
			
			ans4 = new JCheckBox("Answer 4");
			ans4.setSelected(false);
			add(ans4);
			ans4.setBackground(brown);
			ans4.setForeground(Color.GREEN);

			submit = new JButton("Submit");
			add(submit);
			submit.addActionListener(this);
			submit.setBackground(Color.GREEN);
			submit.setForeground(brown);
			
			answersCorrect = new boolean [4];
			
			randomizeQuestion();
		}		
		
		/* Method that randomizes which question is asked; done so that
		 * questions are different each time */
		public void randomizeQuestion()
		{
			Scanner getQuestions = null;
			int questionLine = 0;
			int lineOn = 0;
			String questionStr, ans1Str, ans2Str, ans3Str, ans4Str;
			boolean correctAns1 = false, correctAns2 = false, correctAns3 = false, correctAns4 = false;
						
			// gets a random question
			int questionNumber = (int)(Math.random() * 36) + 1;
			
			// makes sure that question was not asked before
			while (questionNumber == alreadyAnswered[0] || questionNumber == alreadyAnswered[1]
				|| questionNumber == alreadyAnswered[2] || questionNumber == alreadyAnswered[3]
				|| questionNumber == alreadyAnswered[4])
			{
				questionNumber = (int)(Math.random() * 36) + 1;
			}			
			
			alreadyAnswered[questionsAnswered] = questionNumber;
			
			questionLine = (6*(questionNumber-1)) +1; // calculates which line the random question is on
			
			File inFile = new File("BotanyQuestionsFile.txt");
			try
			{
				getQuestions = new Scanner(inFile);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			
			getQuestions.nextLine();
			getQuestions.nextLine();
			getQuestions.nextLine();
			
			// gets the Scanner to the needed line
			while (lineOn < questionLine)
			{
				getQuestions.nextLine();
				lineOn++;
			}
			
			questionStr = getQuestions.nextLine();
			question.setText(questionStr);
			
			/* If it is marked with a (c), it is true; these segments see if it is
			and use that to say whether each option is correct, then sets
			text of JLabel to that */
			ans1Str = getQuestions.nextLine();
			if (ans1Str.indexOf("(c)") > -1)
			{
				correctAns1 = true;
				ans1Str = ans1Str.substring(0, ans1Str.indexOf("(c)"));
			}
			ans1.setText(ans1Str);
			ans1.setSelected(false);


			ans2Str = getQuestions.nextLine();
			if (ans2Str.indexOf("(c)") > -1)
			{
				correctAns2 = true;
				ans2Str = ans2Str.substring(0, ans2Str.indexOf("(c)"));
			}
			ans2.setText(ans2Str);
			ans2.setSelected(false);

			
			ans3Str = getQuestions.nextLine();
			if (ans3Str.indexOf("(c)") > -1)
			{
				correctAns3 = true;
				ans3Str = ans3Str.substring(0, ans3Str.indexOf("(c)"));
			}
			ans3.setText(ans3Str);
			ans3.setSelected(false);

			
			ans4Str = getQuestions.nextLine();
			if (ans4Str.indexOf("(c)") > -1)
			{
				correctAns4 = true;
				ans4Str = ans4Str.substring(0, ans4Str.indexOf("(c)"));
			}
			ans4.setText(ans4Str);
			ans4.setSelected(false);
			
			answersCorrect[0] = correctAns1;
			answersCorrect[1] = correctAns2;
			answersCorrect[2] = correctAns3;
			answersCorrect[3] = correctAns4;
		}
		
		public void actionPerformed (ActionEvent evt)
		{
			String command = evt.getActionCommand();
			boolean allAnswersCorrect = true; // checks whether or not all of the answers submitted are correct
			
			// can put as else if bc all have the same result
			if (ans1.isSelected() && !answersCorrect[0] || !ans1.isSelected() &&
				answersCorrect[0])
			{
				allAnswersCorrect = false;
			}
			else if (ans2.isSelected() && !answersCorrect[1] || !ans2.isSelected() &&
				answersCorrect[1])
			{
				allAnswersCorrect = false;
			}
			else if (ans3.isSelected() && !answersCorrect[2] || !ans3.isSelected() &&
				answersCorrect[2])
			{
				allAnswersCorrect = false;
			}
			else if (ans4.isSelected() && !answersCorrect[3] || !ans4.isSelected() &&
				answersCorrect[3])
			{
				allAnswersCorrect = false;
			}
			
			if (allAnswersCorrect == true)
			{
				questionsCorrect += 1;
			}
			
			if (questionsAnswered == 4 && command.equals("Submit"))
			{
				GamePanel2 gp2 = new GamePanel2(questionsCorrect, cards, scp);
				scp.add(gp2, "Game Panel 2");
				cards.show(scp, "Game Panel 2"); 
			}
			else if (command.equals("Submit"))
			{
				questionsAnswered += 1;
				randomizeQuestion();
			}
		}
	}
	
	// returns image
	public Image getImg(String imgName) 
	{
		File imgFile = new File(imgName);
		Image picture = null;
		try
		{
			picture = ImageIO.read(imgFile);
		}
		catch (IOException e)
		{
			System.err.println("\n\n" + imgName + " can't be found.\n\n");
			e.printStackTrace();
		}
		
		return picture;
	}
}

/* Second Game Panel that allows the user to choose which species of tree
 * they get to use
 */
class GamePanel2 extends JPanel
{
	private CardLayout cards; // CardLayout for changing card
	private SwitchCardsPanel scp; // TreeTwoOneGrowPanel for changing card
	private boolean proceed; // whether continue button has been pressed or not
	private String speciesName; // String that contains the name of the species
	private int questionsCorrect; // The number of questions that the user answered correctly
	
	/* Constructor overridden to receive the number of questions that the user answered 
	 * correctly, and the SwitchCardsPanel and the CardLayout to switch to the third game panel.
	 */
	public GamePanel2(int questionsCorrectIn, CardLayout cardsIn, SwitchCardsPanel scpIn)
	{
		setLayout(new BorderLayout());
		
		cards = cardsIn;
		scp = scpIn;
		questionsCorrect = questionsCorrectIn;
		
		proceed = false;
			
		SizePanel sp = new SizePanel();
		add(sp, BorderLayout.CENTER);
		sp.setPreferredSize(new Dimension(400, 200));
	}	
		
	/*JPanel containing continue button and the species list panel*/
	class SizePanel extends JPanel implements ActionListener
	{
		/* Constructor: adds JButton and SpeciesList JPanel*/
		public SizePanel()
		{
			setLayout(new BorderLayout());
				
			JButton continueButton = new JButton("Continue");
			add(continueButton, BorderLayout.SOUTH);
			continueButton.addActionListener(this);
				
			SpeciesList species = new SpeciesList();
			add(species, BorderLayout.CENTER);
		}
		
		/* If the continue button is pressed and a species has been selected,
		 * add the third GamePanel to the CardLayout (done now so that timers are
		 * started now and not at the beginning), then start the three timers there
		 *, then switch to that GamePanel*/
		public void actionPerformed(ActionEvent evt)
		{
			String buttonPressed = evt.getActionCommand();
			if (buttonPressed.equals("Continue") && speciesName != null)
			{
				GamePanel3 gp3 = new GamePanel3(questionsCorrect, speciesName, cards, scp);
				scp.add(gp3, "Game Panel 3");
				GamePanel3.DrawTreePanel.startTimer();
				GamePanel3.DrawTreePanel.TimeAndSpeciesPanel.startAliveTimer();
				GamePanel3.FoodPanel.startResourceTimer();
				cards.show(scp, "Game Panel 3");
			}
		}
	}
	
	/* JPanel contaning the two other JPanels with the things required for 
	 * selecting the species of tree */
	class SpeciesList extends JPanel
	{
		public SpeciesList()
		{
			setLayout(new BorderLayout());
			
			ChooseSpecies speciesSelector = new ChooseSpecies();
			add(speciesSelector, BorderLayout.SOUTH);
			
			SpeciesImages speciesPics = new SpeciesImages();
			add(speciesPics, BorderLayout.NORTH);
		}
	}
	
	/* JPanel contaning the Radio Buttons for selecting the species */
	class ChooseSpecies extends JPanel implements ActionListener
	{
		/* Constructor that creates the Radio Buttons needed for selecting the
		* species
		*/
		public ChooseSpecies()
		{
			setLayout(new GridLayout(1, 3));	
			
			ButtonGroup chooseSpeciesButtons = new ButtonGroup();
			
			JRadioButton cherryBlossom = new JRadioButton("Cherry Blossom");
			chooseSpeciesButtons.add(cherryBlossom);
			cherryBlossom.addActionListener(this);
			add(cherryBlossom);
			
			JRadioButton quakingAspen = new JRadioButton("Quaking Aspen");
			chooseSpeciesButtons.add(quakingAspen);
			quakingAspen.addActionListener(this);
			add(quakingAspen);
			
			JRadioButton redwood = new JRadioButton("Redwood");
			chooseSpeciesButtons.add(redwood);
			redwood.addActionListener(this);
			add(redwood);
		}
		
		/* If one of the radio buttons is pressed, the species name for that is recorded*/
		public void actionPerformed(ActionEvent evt)
		{
			String buttonPressed = evt.getActionCommand();
			
			speciesName = buttonPressed;
		}
	}
	
	/* JPanel containing images of trees*/
	class SpeciesImages extends JPanel
	{
		// Images of redwood tree, cherry blossom, and quaking aspen
		private Image redwoodImg, cherryBlossomImg, quakingAspenImg;

		/* Constructor that uses ImageIcons to add in images to a GridLayout*/
		public SpeciesImages()
		{	
			setLayout(new GridLayout(1, 3));
			
			/* For all three images, first, a JLabel is created. Then, that 
			 * JLabel is set to having the ImageIcon of its respective species. 
			 * Afterwards, an ImageIcon is created by casting that JLabel as an ImageIcon.
			 * Fourth, a temporary image is made by getting the image from the ImageIcon.
			 * Fifth, that image is scaled to be 400 x 400. Finally, the original JLabel
			 * is set to having that new Image as its ImageIcon*/
			JLabel cherryBlossomImg = new JLabel();
			cherryBlossomImg.setIcon(new ImageIcon("CherryBlossom.jpeg"));
			ImageIcon cherryBlossomImgIcon = (ImageIcon) cherryBlossomImg.getIcon();
			Image cherryBlossomTempImage = cherryBlossomImgIcon.getImage();
			Image cherryBlossomScaledImg = cherryBlossomTempImage.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
			cherryBlossomImg.setIcon(new ImageIcon(cherryBlossomScaledImg));
			
			JLabel quakingAspenImg = new JLabel();
			quakingAspenImg.setIcon(new ImageIcon("QuakingAspen.jpg"));
			ImageIcon quakingAspenImgIcon = (ImageIcon) quakingAspenImg.getIcon();
			Image quakingAspenTempImage = quakingAspenImgIcon.getImage();
			Image quakingAspenScaledImg = quakingAspenTempImage.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
			quakingAspenImg.setIcon(new ImageIcon(quakingAspenScaledImg));
			
			JLabel redwoodImg = new JLabel();
			redwoodImg.setIcon(new ImageIcon("Redwood.jpg"));
			ImageIcon redwoodImgIcon = (ImageIcon) redwoodImg.getIcon();
			Image redwoodTempImg = redwoodImgIcon.getImage();
			Image redwoodScaledImg = redwoodTempImg.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
			redwoodImg.setIcon(new ImageIcon(redwoodScaledImg));
			
			add(cherryBlossomImg);
			add(quakingAspenImg);
			add(redwoodImg);
		}
	}
}

/* Third Game Panel containing the actual animation of the tree*/
class GamePanel3 extends JPanel
{
	private String speciesName; // GamePanel2 for getting selected tree
	
	private int addX, addY; // amount to be added to x and y dimensions
	
	private int amtOfSun, amtOfWater, amtOfFertilizer; // ints representing the amount of sun, water, and fertilizer that the plant has
	private CardLayout cards; // CardLayout for switching to the high scores card
	private SwitchCardsPanel scp; // SwitchCardsPanel for switching to the high scores card
	private int questionsCorrect; // the number of questions that the user correctly answered
	private static Timer addResource; // Timer for how often you can add a resource
	
	/* Constructor that adds the panels and initializes the sun, water, and fertilizer
	 */
	public GamePanel3(int questionsCorrectIn, String speciesNameIn, 
		CardLayout cardsIn, SwitchCardsPanel scpIn)
	{
		setLayout(new BorderLayout());
		
		setBackground(Color.WHITE);
		
		questionsCorrect = questionsCorrectIn;
		scp = scpIn;
		cards = cardsIn;
		speciesName = speciesNameIn;

		amtOfSun = 10;
		amtOfFertilizer = 10;
		amtOfWater = 10;

		//has all of the food and submit buttons
		FoodPanel fp = new FoodPanel();
		add(fp, BorderLayout.WEST);
		
		// animates the tree and has time
		DrawTreePanel animateTree = new DrawTreePanel();
		add(animateTree, BorderLayout.CENTER);
	}
	
	/* JPanel with resources to give to plant*/
	class FoodPanel extends JPanel implements ActionListener
	{
		private boolean timeForResource; // whether or not enough time has elapsed so that user can add another resource
		
		/* Constructor: Timer is initialized and JLabel and RadioButtons are added*/
		public FoodPanel()
		{
			setLayout(new GridLayout(5,1));
			
			timeForResource = false;
			
			AddResourceListener arl = new AddResourceListener();
			addResource = new Timer(500, arl);
			
			JLabel food = new JLabel("Food", JLabel.CENTER);
			add(food);
			food.setBackground(Color.BLUE);
			food.setForeground(Color.BLUE);
			
			ButtonGroup bg = new ButtonGroup();
						
			JRadioButton sunlight = new JRadioButton("Sunlight");
			add(sunlight);
			bg.add(sunlight);
			sunlight.addActionListener(this);
			sunlight.setBackground(Color.BLUE);
			sunlight.setForeground(Color.WHITE);
			
			JRadioButton water = new JRadioButton("Water");
			add(water);
			bg.add(water);
			water.addActionListener(this);
			water.setBackground(Color.BLUE);
			water.setForeground(Color.WHITE);
			
			JRadioButton fertilizer = new JRadioButton("Fertilizer");
			add(fertilizer);
			bg.add(fertilizer);
			fertilizer.addActionListener(this);
			fertilizer.setBackground(Color.BLUE);
			fertilizer.setForeground(Color.WHITE);
		}
		
		/* Method for starting the Timer externally; static so that it can be called w/o having to worry about which instance it is*/
		public static void startResourceTimer()
		{
			addResource.start();
		}
		
		/* If a resource was pressed, depending on how much of each resource 
		 * the plant already has, it will grow by a certain amount*/
		public void actionPerformed(ActionEvent evt)
		{
			String resourcePressed = evt.getActionCommand();
			if (resourcePressed.equals("Sunlight") && timeForResource)
			{
				if (amtOfSun > 0 && amtOfFertilizer > 0 && amtOfWater > 0)
				{
					addX += 10;
					addY += 10;
				}
				else
				{
					addX += 7;
					addY += 7;
				}
				amtOfSun += 5;
			}
			else if (resourcePressed.equals("Water") && timeForResource)
			{
				if (amtOfSun > 0 && amtOfFertilizer > 0 && amtOfWater > 0)
				{
					addX += 10;
					addY += 10;
				}
				else
				{
					addX += 7;
					addY += 7;
				}
				amtOfWater += 5;
			}
			else if (resourcePressed.equals("Fertilizer") && timeForResource)
			{
				if (amtOfSun > 0 && amtOfFertilizer > 0 && amtOfWater > 0)
				{
					addX += 10;
					addY += 10;
				}
				else
				{
					addX += 7;
					addY += 7;
				}
				amtOfFertilizer += 5;
			}
			
			timeForResource = false;
		}
		
		/* Added because it allows for Timer to have its own actionPerformed()*/
		class AddResourceListener implements ActionListener
		{
			public void actionPerformed (ActionEvent evt)
			{
				timeForResource = true;
			}
		}
	}
	
	/* JPanel where tree is drawn and time and species are listed. trying to figure out
	why animation won't appear */
	class DrawTreePanel extends JPanel implements ActionListener
	{		
		private static Timer drawTree; // Timer for tree animation
		private boolean growing; // amount to be added to y dimension
		private Image growingTree; // Image of the tree
		private String growingTreeName; // the file name of the image of the tree
		private int seconds; // number of seconds elapsed
		private static Timer timeAlive; // Timer for displaying how long has been spent on the game
		
		/* Constructor: Makes the image of the growing tree a variable, sets up timer, 
		 * adds Time and Species Panel
		 */
		public DrawTreePanel()
		{
			setLayout(new BorderLayout());
			
			addX = 400;
			addY = 400;
			
			growingTreeName = speciesName + "Growing.png";
			growingTree = getTreePic(growingTreeName);
			
			growing = false;
			drawTree = new Timer(150, this);
			
			TimeAndSpeciesPanel tasp = new TimeAndSpeciesPanel();
			add(tasp, BorderLayout.NORTH);
		}
		
		/* Method for starting Timer externally; static so that it can accessed
		 * without worrying about instance*/
		public static void startTimer()
		{
			drawTree.start();	
		}
		
		/* Returns the Image version of the file that was sent*/
		public Image getTreePic(String pictName)
		{
			File imgFile = new File(pictName);
			Image picture = null;
			try
			{
				picture = ImageIO.read(imgFile);
			}
			catch (IOException e)
			{
				System.err.println("\n\n" + pictName + " can't be found.\n\n");
				e.printStackTrace();
			}
			
			return picture;
		}
	
		/* Draws the tree using addX and addY*/
		public void paintComponent(Graphics g)
		{			
			super.paintComponent(g);
			//Tree
			int height = 10 + addY; 
			int width = 10 + addX;
			
			// done so that it grows from the middle in terms of x-axis, bottom
			// in terms of height
			g.drawImage(growingTree, 350 - (width/2), 500 - height, width, height, this);
		}
		
		/* Regulates the size of the tree by subtracting one from addX and
		 * addY; if they are above 400 then set them as 400
		 */
		public void actionPerformed (ActionEvent evt)
		{
			amtOfSun -= 1;
			amtOfFertilizer -= 1;
			amtOfWater -= 1;
			
			addX -= 1;
			addY -= 1;
			
			if (addX > 400)
			{
				addX = 400;
				addY = 400;
			}
			
			if (addX < 0)
			{
				checkAndWriteScores(seconds);
				ScoresPanel hsp = new ScoresPanel();
				scp.add(hsp, "High Scores Panel");
				drawTree.stop();
				timeAlive.stop();
				cards.show(scp, "High Scores Panel");
			}
			repaint();
		}	
		
		/* Retain history of five scores in a file */
		public void checkAndWriteScores(int score)
		{
			score = (score*questionsCorrect)/5;
			boolean firstRun;
			final int MAX_RESULTS = 5;
			final String scoresFile = "HighScoresFile.txt";
			Scanner readScores = null;
			File highScoresFile = new File(scoresFile);
			try
			{
				firstRun = highScoresFile.createNewFile();
				readScores = new Scanner(highScoresFile);
			}
			catch (IOException e)
			{
				System.err.println("Error opening file: " + scoresFile);
				e.printStackTrace();
			}
			
			System.out.println("Questions correct: " + questionsCorrect);

			String [] sortScores = new String [MAX_RESULTS];
			int numOfScores = 0;

			sortScores[0] = String.valueOf(score);
			for (int i = 1; i < sortScores.length; i++) //Reserve space for current
			{
				if (readScores.hasNext())
				{
					sortScores[i] = readScores.nextLine();
					numOfScores++;
				}
			}

			PrintWriter writeHighScores = null;
			try
			{
				writeHighScores = new PrintWriter(highScoresFile);
				for (int i = 0; i < numOfScores+1; i++)
					writeHighScores.println(sortScores[i]);
			}
			catch (IOException e)
			{
				System.out.println("Error writing file: " + scoresFile);
				e.printStackTrace();
			}

			writeHighScores.close();
		}

		
		/* JPanel for displaying the time and species */
		class TimeAndSpeciesPanel extends JPanel implements ActionListener
		{
			private String timeElapsed; // formatted String for JLabel
			private JLabel time; // JLabel displaying elapsed time
			private JLabel species; // JLabel displaying species of tree
			
			/* Constructor where Timer is set up and time and species JLabels
			 * are initialized.*/
			public TimeAndSpeciesPanel()
			{
				setLayout(new GridLayout(1, 2));
				
				String speciesLabelStr = "Species: " + speciesName;
				
				species = new JLabel(speciesLabelStr, JLabel.CENTER);
				add(species);
				
				time = new JLabel("Time: 0:00", JLabel.CENTER);
				add(time);
				
				seconds = 0;
				timeAlive = new Timer(1000, this);
			}
			
			/* Starts the timer if run; static in order to not have to worry about
			 * instance when doing this from another class.*/
			public static void startAliveTimer()
			{
				timeAlive.start();	
			}
			
			/* Increases number of seconds, then makes timeElapsed String into
			minutes:seconds, then formats it if need be, then updates JLabel */
			public void actionPerformed (ActionEvent evt)
			{
				seconds++;
				timeElapsed = seconds/60 + ":" + seconds%60;
				if (seconds%60 < 10)
				{
					timeElapsed = seconds/60 + ":0" + seconds%60;
				}
				time.setText("Time: " + timeElapsed);
			}
		}
	}	
}

/* Contains the scores for the last 5 attempts */
class ScoresPanel extends JPanel
{
	private JTextArea scoresArea; // Contains the most recent scores
	/*Constructor adds header jlabel and then the textfield.*/	
	public ScoresPanel()
	{
		setLayout(new BorderLayout());
		
		JLabel header = new JLabel("Scores Panel", JLabel.CENTER);
		add(header, BorderLayout.NORTH);
		
		String scoresStr = getScores();
		scoresArea = new JTextArea(scoresStr, 10, 5);
		scoresArea.setLineWrap(true);
		scoresArea.setWrapStyleWord(true);
		JScrollPane scroller = new JScrollPane(scoresArea);
		add(scroller, BorderLayout.CENTER);
		
	}
	
	/* Method gets the 5 most recent scores*/
	public String getScores()
	{
		Scanner getScores = null;
		String fileName = "HighScoresFile.txt";
		File scoresFile = new File(fileName);
		
		try
		{
			getScores = new Scanner(scoresFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.err.print("Unable to read from " + fileName);
		}
	
		for (int i = 0; i < 4; i++)
		{
			if (getScores.hasNext())
			{
				getScores.nextLine();
			}
		}
		
		String [] highScores = new String [5];
		int numOfScores = 0;
		
		for (int i = 0; i < highScores.length; i++)
		{
			if (getScores.hasNext())
			{
				highScores[i] = getScores.nextLine();
				numOfScores++;
			}
		}
		
		String temp = "";
		
		String scores = "";
		for (int i = 0; i < numOfScores; i++)
		{
			scores += i + 1 + ". " + highScores[i] + "\n\n";
		}
		
		return scores;
	}
}
