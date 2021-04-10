//Karthik Murugappan
//Geography Ace Game
//This game will teach students Geography around the world


import java.awt.*;                                    //import all files
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;


public class GeographyAce extends JFrame implements ItemListener{    //class header
	
	CardLayout cl;                        //call CardLayout variable
	
	public static void main (String [] args){        //main method header
		GeographyAce ga = new GeographyAce();        //create a new instance for GeographyAce
		
	}
	
	public void resetPane () {
		card2.resetBoard();
	}
	
	JPanel cards;            //declare JPanel "cards"
	Instructions card1;    //declare first card "Instructions"
	GameBoard card2;        //declare second card "GameBoard"
	
	public GeographyAce(){
		super("Geography Ace");        //name the frame "Geography Ace"
		setSize(900,700);            //set size of the frame
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);        //exit the frame by pressing the x button
		card1 = new Instructions(cards, this);    //create new instance for Instruction
		card2 = new GameBoard(cards);        //create new instance for GameBaord
		cards = new JPanel(new CardLayout());    //create new instance for CardLayout
		cards.add(card1, "Game Instructions");
		cards.add(card2, "Game Board");
		add(cards, BorderLayout.CENTER);
		setResizable(false);        //set Resizable to true
		setVisible(true);        //set Visible to true
	}
	
	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, (String) evt.getItem());
	}
	
	class Instructions extends JPanel implements ActionListener{        //create Instructions class
		JButton startButton;                        //declare JButton
		private JPanel cardconnectpanel;
		private GeographyAce mymaincard;
		public Instructions(JPanel cardlayoutpanel, GeographyAce mc){
			mymaincard = mc;
			cardconnectpanel = cardlayoutpanel;
			JLabel label = new JLabel("<html>"                //create JLabel
			+ "<center><font style ='font-size: 70px; color: orange;'> GEOGRAPHY ACE!</font></center>"		//create HTML code for the text on the title screen
			+ "<br>"
			+ "<br>"
			+ "<center><font style ='font-size: 30px; color: green'>Welcome to Geography Ace!</font></center>"
			+ "<br>"
			+ "<font style ='color: green;'><font/>You will learn about states, capitals, and landmarks"
			+ "<br>"
			+ "<br>"
			+ "- You will be racing against another car controlled"
			+ "<br>"
			+ "by the computer"
			+ "<br>"
			+ "- Try to get all the geography questions right"
			+ "<br>"
			+ "- If you get a question wrong, the other car will move"
			+ "<br>"
			+ "up 1 or 2 (random) spaces or"
			+ "<br>"
			+ "- For every correct answer, you will get ONE point"
			+ "<br>"
			+ "- First to NINE points wins"
			+ "<br>"
			+ "<br>"
			+ "Good Luck!"
			+ "</html>", JLabel.CENTER);
			label.setFont(new Font("DialogInput", Font.BOLD, 26));    //set font, style, and size
			startButton = new JButton("Start");            //create new "Start" button
			startButton.addActionListener(this);
			add(label);                            //add JLabel
			add(startButton);                        //add the "Start" button
		}
		
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			if (e.getSource() == startButton) {
				CardLayout cl = (CardLayout) (cards.getLayout());
				mymaincard.resetPane();
				cl.show(cards, "Game Board");
			}
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setBackground(Color.ORANGE);
			Image img = Toolkit.getDefaultToolkit().getImage("map.png");    //set background image
			g.drawImage(img, 0, 0, this);    //draw the image
		}
	}
	
	class GameBoard extends JPanel implements ActionListener            //create GameBoard class
	{
		JButton backButton;            //declare backButton
		JPanel panel1;            //declare JPanel
		public void resetBoard () {
			this.removeAll();
			myConstructor();
			this.revalidate();
			this.repaint();
			
		}
		public void myConstructor () {
			Track mycar = new Track("car.png");
			Track compcar = new Track("car1.png");
			this.setLayout(null);
			add(mycar);
			add(compcar);
			mycar.setSize(200,500);
			mycar.setLocation(0,150);
			compcar.setSize(100,500);
			compcar.setLocation(200,150);	

			
			QuestionAnswerCardLayoutPanel loool = new QuestionAnswerCardLayoutPanel(mycar, compcar);
			add(loool);
			loool.setSize(500,400);
			loool.setLocation(350,200);
			
			
			setBackground(Color.CYAN);        //set background color
			backButton = new JButton("Back");        //create "Back" button
			backButton.addActionListener(this);
			add(backButton);                //add the "Back" button
			setLayout(null);                    //set Layout as null
			backButton.setLocation(750,50);        //set Location of "Back" button
			backButton.setSize(100,20);            //set Size of the "Back" button
			
		}
		public GameBoard(JPanel lol)
		{
			panel1 = lol;
			myConstructor();
			
		}
			
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == backButton) {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.first(cards);
				
			}
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Image img = Toolkit.getDefaultToolkit().getImage("map.png");    //set background image
			g.drawImage(img, 0, 0, this);    //draw the image
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Serif", Font.BOLD, 75));
			g.drawString("Geography Ace!", 200, 100);
			
		}
		
	}
}

class Track extends JPanel
{
	
	private int ycoordinate;
	private String carurl;
	
	public Track (String carpic){
		carurl = carpic;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(new Font("Serif", Font.BOLD, 26));
		g.drawString("0", 140, 30);
		g.drawString("1", 140, 80);
		g.drawString("2", 140, 140);
		g.drawString("3", 140, 190);
		g.drawString("4", 140, 240);
		g.drawString("5", 140, 290);
		g.drawString("6", 140, 340);
		g.drawString("7", 140, 390);
		g.drawString("8", 140, 440);
		g.drawString("9", 140, 480);
		setBackground(Color.GREEN);
		Image img = Toolkit.getDefaultToolkit().getImage("road.png");
		Image carimg = Toolkit.getDefaultToolkit().getImage(carurl);
		g.drawImage(img, 0, 0, 100, 500, this);
		g.drawImage(carimg, 0, ycoordinate, 100, 50, this);
		
	}
	
	public int returnY() {
		return ycoordinate;
	}
	
	public void moveUp(){
		ycoordinate+=50;
		repaint();
	}
	
	public void moveDown(){
		ycoordinate-=50;
		repaint();
	}
	
}

class QuestionAnswerCardLayoutPanel extends JPanel {
	CardLayout qalayout;
	Track m, c;
	
	public String arr = "";
	
	public void carUp() {
		m.moveUp();
	}
	
	public int getyforcomp(){
		return c.returnY();
	}
	
	public int getyforcar(){
		return m.returnY();
	}
	
	public void compUp() {
		c.moveUp();
	}
	
	public QuestionAnswerCardLayoutPanel(Track mycar, Track compcar) {
		m = mycar;
		c = compcar;
		qalayout = new CardLayout();
		setLayout(qalayout);
		add(new SecondClass("Which is the most populated \ncity in China?", "Hong Kong", "Seoul", "Beijing", "Shanghai", this),"1");
		add(new SecondClass("What Central American \ncountry's name means \n'many fish'?", "Belize", "Costa Rica", "Honduras", "Panama", this),"2");
		add(new SecondClass("What is the capital of Florida?", "Orlando", "Miami", "Atlanta", "Tallahassee", this),"3");
		add(new SecondClass("There is approximately one \nregular time zone for every \n15 degrees of longitude. \nHow many different time \nzones are there in the \nworld?", "12", "16", "20", "24", this),"4");
		add(new SecondClass("What is the capital of \nNew York?", "New York City", "Austin", "Philadelphia", "Albany", this),"5");
		add(new SecondClass("What is the capital of \nTexas?", "Boise", "San Antonio", "Dallas", "Austin", this),"6");
		add(new SecondClass("What is the capital of \nIdaho?", "Philadelphia", "Montana", "Orlando", "Boise", this));
		add(new SecondClass("What is the capital of \nthe United States?", "Cincinnati", "Chicago", "Philadelphia", "Washinton D.C.", this),"7");
		add(new SecondClass("What is the capital of \nSouth Dakota?", "Topeka", "Honolulu", "Sacramento", "Pierre", this),"8");
		add(new SecondClass("What is the capital of \nArkansas?", "Olympia", "Columbus", "Denver", "Little Rock", this),"9");
		add(new SecondClass("What is the capital of \nWyoming?", "Nashville", "Richmond", "Madison", "Cheyenne", this),"10");
		add(new SecondClass("What is the capital of \nCalifornia?", "San Francisco", "Los Angeles", "Seattle", "Sacramento", this),"11");
		add(new SecondClass("What is the capital of \nNorth Carolina?", "Santa Fe", "Boston", "Miami", "Raleigh", this),"12");
		add(new SecondClass("What is the capital of \nSouth Carolina?", "Concord", "Salem", "Charleston", "Columbia", this),"13");
		add(new SecondClass("What is the capital of \nNew Hampshire?", "Augusta", "Helena", "Lincoln", "Concord", this),"14");
		add(new SecondClass("What is the capital of \nConnecticut?", "Lansing", "Providence", "Trenton", "Hartford", this),"15");
		add(new SecondClass("What is the capital of \nMaine?", "Springfield", "Atlanta", "Washington D.C.", "Augusta", this),"16");
		add(new SecondClass("What is the capital of \nSouth Carolina?", "Concord", "Salem", "Charleston", "Columbia", this),"17");
		add(new SecondClass("What is the capital of \nOhio?", "Cleveland", "Dover", "Bismark", "Columbus", this),"18");
		add(new SecondClass("What is the capital of \nPennsylvania?", "Pittsburg", "Philadelphia", "Newark", "Harrisburg", this),"19");
		add(new SecondClass("Which continent is Albania \nlocated in?", "Asia", "North America", "Africa", "Europe", this),"20");
		add(new SecondClass("What is the smallest \ncountry by surface area \nin the world?", "Liberia", "Benin", "Monaco", "Vatican City", this),"21");
		add(new SecondClass("The hottest temperature \nrecorded on earth is \nEl Azizia Libya at...", "145 F", "128 F", "116 F", "136 F", this),"22");
		add(new SecondClass("The coldest temperature \nrecorded on earth is \nVostok Antarctica at...", "-82 F", "-116 F", "-94 F", "-134 F", this),"23");
		add(new SecondClass("What is the tallest \nmountain in the United \nStates?", "Castle Peak", "Mount Shasta", "Mount Whitney", "Mount McKinley", this),"24");
		add(new SecondClass("What is the tallest \nmountain in the world?", "Makalu", "Lhotse", "K2", "Mount Everest", this),"25");
		add(new SecondClass("Where are 70% - 80% \nof all freshwater on \nthe planet stored in?", "Reservoirs", "Rivers", "Oceans", "Glaciers", this),"26");
		add(new SecondClass("What is the capital of \nAustria?", "San Diego", "Sofia", "Minsk", "Vienna", this),"27");
		add(new SecondClass("What is the capital of \nEngland?", "Berlin", "Rome", "Paris", "London", this),"28");
		add(new SecondClass("What is the capital of \nGermany?", "Oslo", "Monaco", "Dublin", "Berlin", this),"29");
		add(new SecondClass("What is the capital of \nLuxembourg?", "Edinburgh", "Warsaw", "Podgorica", "Luxembourg", this),"30");
		add(new SecondClass("What is the capital of \nItaly?", "Copenhagen", "Tallin", "Athens", "Rome", this),"31");
		add(new SecondClass("What is the capital of \nEgypt?", "Port Louis", "Moroni", "Rabat", "Cairo", this),"32");
		add(new SecondClass("What is the captail of \nChad?", "Accra", "Gaborone", "Malabo", "N'Djamena", this),"33");
		add(new SecondClass("What is the capital of \nNigeria?", "Dakar", "Jamestown", "Niamey", "Abuja", this),"34");
		add(new SecondClass("What is the capital of \nUganda?", "Mamoudzou", "Antananarivo", "Kigali", "Kampala", this),"35");
		add(new SecondClass("How many capital cities \ndoes South Africa have?", "1", "2", "4", "3", this),"36");
		add(new SecondClass("What is the only city \nlocated on two continents?", "Moscow", "Athens", "Rome", "Istanbul", this),"37");
		add(new SecondClass("Which country means 'land \nof rabbits'?", "Nepal", "Germany", "Russia", "Spain", this),"38");
		add(new SecondClass("This waterfall is the \nhighest waterfall in the \nworld which drops from \n3,212 feet...", "Yosemite Falls", "Tugela Fall", "Niagara Falls", "Angel Falls", this),"39");
		add(new SecondClass("Where was the deepest \nhole ever made?", "New York", "Florida", "California", "Texas", this),"40");
		add(new SecondClass("This lake is the deepest \nlake in the world at \n5,712 feet...", "Lake Tanganyika", "Crater Lake", "Lake Tahoe", "Lake Baikal", this),"41");
		add(new SecondClass("This river is 4,160 miles \nlong, which is the longest \nriver in the world...", "Congo River", "Amazon River", "Mississippi River", "Nile River", this),"42");
		add(new SecondClass("Where is the Yangtze river \nlocated?", "South Korea", "Thailand", "Japan", "China", this),"43");
		add(new SecondClass("How many official language \ndoes South Africa have?", "3", "5", "7", "11", this),"44");
		add(new SecondClass("Which is the largest Muslim \ncountry in the world?", "Bangladesh", "India", "Pakistan", "Indonesia", this),"45");
		add(new SecondClass("Brazil got its name from...", "a seed", "a moviestar", "a fruit", "a nut", this),"46");
		add(new SecondClass("Where is the first paved \nroad located in?", "New York City", "Chicago", "San Francisco", "Detroit", this),"47");
		add(new SecondClass("Which city in the United \nStates is known as the \nBig Apple?", "Phoenix", "Chicago", "Los Angeles", "New York City", this),"48");
		add(new SecondClass("Which country contains \nmore than 25% of the \nworld's forests?", "United States", "Chile", "Brazil", "Siberia", this),"49");
		add(new SecondClass("Which continent has no \ndeserts?", "South America", "Africa", "Asia", "Europe", this),"50");
		add(new SecondClass("Which is the only US State \nthat grows coffee?", "Texas", "Washington", "California", "Hawaii", this));
		add(new SecondClass("It is forbidden for aircraft \nto fly over the...", "US Capitol Building", "Pentagon", "Empire State Building", "Taj Mahal", this),"51");
		add(new SecondClass("Which is not one of the \nGreat Lakes?", "Lake Superior", "Lake Michigan", "Lake Huron", "Crater Lake", this),"52");
		add(new SecondClass("What is the international \ntelephone dialing code for \nAntarctica?", "244", "787", "341", "672", this),"53");
		add(new SecondClass("Which is the city with \nthe highest murder rate?", "Atlanta", "Baltimore", "Oakland", "Detroit", this),"54");
		add(new SecondClass("How many active volcanoes \nare there in Japan?", "12", "9", "21", "17", this),"55");
		add(new SecondClass("Where is Mount Everest \nlocated?", "Iran", "China", "India", "Nepal", this),"56");
		add(new SecondClass("Where is the Great Wall \nof China located?", "Japan", "Albania", "India", "China", this),"57");
		add(new SecondClass("Where is the Taj Mahal \nlocated?", "Iraq", "Pakistan", "Bangladesh", "India", this),"58");
		add(new SecondClass("Where is the Big Ben \nlocated?", "Italy", "Scotland", "France", "England", this),"59");
		add(new SecondClass("The Burj Khalifa is the \ntallest building in the world. \nWhere is it located?", "India", "China", "England", "Dubai", this),"60");
		add(new SecondClass("Where is the Statue of \nLiberty located?", "Baltimore", "Boston", "Chicago", "New York City", this),"61");
		add(new SecondClass("Where is Stonehedge \nlocated?", "Ireland", "Denmark", "Norway", "England", this),"62");
		add(new SecondClass("Where is the Golden Gate \nBridge located?", "Houston", "New York City", "Chicago", "San Francisco", this),"63");
		add(new SecondClass("Where is Yellow Stone \nNational Park located?", "Kansas", "Idaho", "Montana", "Wyoming", this),"64");
		add(new SecondClass("What is the official \nlanguage of the United \nStates?", "French", "Spanish", "English", "No Official Language", this),"65");
		add(new SecondClass("What is the official \nlanguage of India?", "English", "Telugu", "Tamil", "Hindi", this),"66");
		add(new SecondClass("The Mall of America is \nthe biggest mall in the \nUnited States. Which state \nis it located in?", "New York", "Pennsylvania", "Texas", "Minnesota", this),"67");
		add(new SecondClass("Where is the busiest airport \nin the United States?", "Chicago", "New York City", "Los Angeles", "Atlanta", this),"68");
		add(new SecondClass("Where is Times Square \nlocated?", "Dallas", "Philadelphia", "Orlando", "New York City", this),"69");
		add(new SecondClass("Which is the only state to \nenter the Eastern \nHemisphere?", "Virginia", "California", "Hawaii", "Alaska", this),"70");
		add(new SecondClass("There are no rivers in...", "Finland", "Iraq", "Egypt", "Saudi Arabia", this),"71");
		add(new SecondClass("This is the only sea which \nhas no coast...", "Red Sea", "North Sea", "Baltic Sea", "Sargasso Sea", this),"72");
		add(new SecondClass("What is the longest river \nin North America?", "Mississippi River", "Rio Grande River", "Yukon River", "Missouri River", this),"73");
		add(new SecondClass("What is the longest river \nin Europe?", "Tobol River", "Kama River", "Ural River", "Volga River", this),"74");
		add(new SecondClass("Which country has the \nlargest population in Africa?", "Kenya", "South Africa", "Egypt", "Nigeria", this),"75");
		add(new SecondClass("What is the largest country \nby surface area in the \nworld?", "Japan", "India", "China", "Russia", this),"76");
		add(new SecondClass("How many countries border \nwith Egypt?", "1", "2", "3", "4", this),"77");
		add(new SecondClass("What is the capital of \nSouth Korea?", "Hong Kong", "Beijing", "Tokyo", "Seoul", this),"78");
		add(new SecondClass("In which continent is the \nWeddell Sea located in?", "Europe", "South America", "Asia", "Antarctica", this),"79");
		add(new SecondClass("What country has the most \nof the gas and petrol?", "Mexico", "Germany", "Iraq", "Saudi Arabia", this),"80");
		add(new SecondClass("In which country is \nMount Kenya?", "Egypt", "South Africa", "Nigeria", "Kenya", this),"81");
		add(new SecondClass("How many island make up \nthe state of Hawaii?", "5", "9", "7", "8", this),"82");
		add(new SecondClass("Where is the city of Toronto \nlocated?", "France", "Mexico", "United States", "Canada", this),"83");
		add(new SecondClass("Where is the island of Malta \nlocated?", "Baltic Sea", "Black Sea", "Red Sea", "Mediterranean Sea", this),"84");
		add(new SecondClass("What country is famous for \nthe maple tree?", "Italy", "England", "United States", "Canada", this),"85");
		add(new SecondClass("Where are hamburgers \noriginally from?", "Poland", "France", "United States", "Germany", this),"86");
		add(new SecondClass("What is the capital of \nZimbabwe?", "Yaounde", "Victoria", "Juba", "Harare", this),"87");
		add(new SecondClass("What is the China's official \nlanguage?", "Cantonese", "Korean", "Chinese", "Mandarin", this),"88");
		add(new SecondClass("What is Italy's shape \nsimilar to?", "a pizza", "a ring", "a box", "a boot", this),"89");
		add(new SecondClass("In which South American \ncountry is the Atacama \ndesert located in?", "Peru", "Uruguay", "Brazil", "Chile", this),"90");
		add(new SecondClass("Where is the Mammoth \nCave, the world's longest \ncave?", "Canada", "South Africa", "Mexico", "United States", this),"91");
		add(new SecondClass("Which country was \npreviously called \nAbyssinia?", "Madagascar", "Albania", "Bosnia", "Ethiopia", this),"92");
		add(new SecondClass("Which is the least populated \nstate in the United States?", "Idaho", "South Dakota", "Rhode Island", "Wyoming", this),"93");
		add(new SecondClass("What is the highest \nmountain in Africa?", "Mount Meru", "Mount Kenya", "Mount Elgon", "Mount Kilimanjaro", this),"94");
		add(new SecondClass("Which is the longest \nexposed mountain range?", "The Himalayas", "The Appalachian Mountains", "The Rocky Mountains", "The Andes", this),"95");
		add(new SecondClass("Where is the Leaning Tower \nof Pisa located?", "France", "Germany", "Russia", "Italy", this),"96");
		add(new SecondClass("Which is the most populated \ncity in the world?", "Beijing", "New York City", "Moscow", "Tokyo", this),"97");
		add(new SecondClass("In the Sahara Desert, there \nis a town named Tidikelt, \nAlgeria, which did not \nreceive a drop of rain for...", "20 years", "2 years", "5 years", "10 years", this),"98");
		add(new SecondClass("What is the capital of \nWashington?", "Tacoma", "Salem", "Seattle", "Olympia", this),"99");
		add(new SecondClass("What is the capital of \nOregon?", "San Diego", "Seattle", "Portland", "Salem", this),"100");
		//add(new SecondClass("?", "", "", "", "", this),"");
		
		add(new EndClass1(), "won");
		add(new EndClass2(), "lost");
		
		((CardLayout)this.getLayout()).show(this, "" + (int) (1 + Math.random()*100));
		
	}
}

class SecondClass extends JPanel implements ActionListener {
	
	private JButton nextbutton;
	private JTextArea questionspace;
	private JTextArea success;
	private ThirdClass answerpanel;
	private QuestionAnswerCardLayoutPanel clp;
	public boolean iscorrect;
	private String correctanswer;
	
	public SecondClass(String q, String a1, String a2, String a3, String ca, QuestionAnswerCardLayoutPanel cardlayoutpanel) {
		clp = cardlayoutpanel;
		setLayout(new GridLayout(2,2));
		
		nextbutton = new JButton("Next");
		questionspace = new JTextArea(q);
		add(questionspace);
		nextbutton.addActionListener(this);
		success = new JTextArea("");
		correctanswer = ca;
		
		Font font = new Font("Verdana", Font.BOLD, 16);
		questionspace.setFont(font);
		questionspace.setForeground(Color.BLUE);
		
		Font font2 = new Font("Verdana", Font.BOLD, 19);
		success.setFont(font2);
		success.setForeground(Color.RED);
		
		answerpanel = new ThirdClass(a1, a2, a3, ca, this);
		add(answerpanel);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() != nextbutton) {
			this.add(nextbutton);
			this.add(success);
			
			if (((JRadioButton)e.getSource()).getText()  == correctanswer) {
				success.setText("Success");
				clp.carUp();
			}
			
			else {
				success.setText("Wrong, This answer is \n" + correctanswer + ".");
				for (int numOfUps = 0;  numOfUps < (int) (1 + Math.random()*2); numOfUps++) {
					clp.compUp();
				}
				
			}
			System.out.print(clp.getyforcar());
			if(clp.getyforcar() > 400)
			((CardLayout)clp.getLayout()).show(clp, "won");
			
			
			if(clp.getyforcomp() > 400)
			((CardLayout)clp.getLayout()).show(clp, "lost");

			
		}

		else if (e.getSource() == nextbutton) {
				((CardLayout)clp.getLayout()).show(clp, "" + findNewRandom());
		}
	}
	
	public int findNewRandom() {
		int m = (int) (1 + Math.random()*100);
		if (clp.arr.indexOf("*" + m + "*") == -1) {
			clp.arr = "*" + m + "*"; // since arr is a public class
			return m;
		}
		else {
			return findNewRandom();
					
		}
	}
}

class ThirdClass extends JPanel implements ActionListener {
	
	JRadioButton answer1;
	JRadioButton answer2;
	JRadioButton answer3;
	JRadioButton correctanswer;
	
	public JRadioButton createOne(String z, ButtonGroup m, SecondClass y, boolean iscorrect) {
		answer1 = new JRadioButton(z);
		answer1.addActionListener(y);
		m.add(answer1);
		add(answer1);
		return answer1;
	}
	
	public ThirdClass (String answer1, String answer2, String answer3, String correctanswer, SecondClass lolol) {
		ButtonGroup answerGroup = new ButtonGroup();
		String m = "";
		String [] n = {answer1, answer2, answer3, correctanswer};
		int count = 0;
		while (count != 4) {
			int abc = (int) (Math.random() * 4);
			if (m.indexOf(";*" + n[abc] + "*;") == -1)  {
				createOne(n[abc], answerGroup, lolol, false);
				m+=";*" + n[abc] + "*;";
				count++;
			}
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}

class EndClass1 extends JPanel {
	
	public EndClass1() {
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(new Font("Serif", Font.BOLD, 50));
		g.drawString("YOU WON!", 100, 200);
				
	}
}

class EndClass2 extends JPanel {
	public EndClass2() {
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(new Font("Serif", Font.BOLD, 50));
		g.drawString("YOU LOST!", 100, 200);
				
	}
	

	
}