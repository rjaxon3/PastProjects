
/**
002
* @(#)BlackJack.java
003
* BlackJack application
004
* Author: Brian SonniE
005
*/

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;

 

public class BlackJack extends JFrame{

    int cash = 0;

    int currentBet = 0;

    ArrayList<Card> deck;

    CardsJaxon[] usersCards = new CardsJaxon[10];

    CardsJaxon[] dealersCards = new CardsJaxon[10];

    private JButton buttonHit, buttonStay, buttonDeal, buttonCashOut;

    private JPanel userPane, dealerPane, userCardPane;

    private JLabel labelMoney, labelBet;

    BufferedImage img;

    final int cardWidth = 79;

    final int cardHeight = 123;

    boolean gameOn = true; 
 

public BlackJack()
 {

    setTitle("BlackJack"); 
    setSize(450,500);  

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    setLayout(new BorderLayout());
    setVisible(true);  
    setBackground(Color.green);    
    buildTable();  
    gameInit();    
    repaint();

    getContentPane().setBackground(Color.GREEN);

 } 

public void paint(Graphics g)
{    

    super.paint(g);    
    g.drawString("Dealers Hand", 20, 60);  

    g.drawString("Your Hand", 20, 230);        

    g.drawString("Sum: "+getUserSum(), 90, 230);       
    int x1 = 10;   
    int y1 = 250;  
    int x2 = x1+cardWidth; 
    int y2 = y1+cardHeight;        
    int dx1 = 10;  
    int dy1 = 80;  
    int dx2 = dx1+cardWidth;   
    int dy2 = dy1+cardHeight;      

    // Draw Dealers Cards      

    for(int i = 1; i<=getNumDealerCards(); i++) 

    {  

    g.drawImage(img, dx1+(cardWidth*(i-1)), dy1, dx2+(cardWidth*(i-1)), dy2, dealersCards[i-1].getX(),

        dealersCards[i-1].getY(), dealersCards[i-1].getX()+cardWidth ,dealersCards[i-1].getY()+cardHeight,null);

    }      

    // Draw User Cards

    for(int i = 1; i<=getNumUserCards(); i++)

    {  
        g.drawImage(img, x1+(cardWidth*(i-1)), y1, x2+(cardWidth*(i-1)), y2, usersCards[i-1].getX(),

            usersCards[i-1].getY(), usersCards[i-1].getX()+cardWidth ,usersCards[i-1].getY()+cardHeight,null);

    }

    } 

    public int getNumUserCards()

    {  

        int numCards = 0;  

        while(usersCards[numCards]!=null)

        numCards++;

        return numCards;

    }

     

    public int getNumDealerCards() {

    int numCards = 0;

    while(dealersCards[numCards]!=null)

        numCards++;    

    return numCards;

    } public int getUserSum()

    {  

    int numCards = 0;  
    int sum = 0;   

    int cardVal = 0;

    while(usersCards[numCards]!=null){

        cardVal = usersCards[numCards].getValue();
        if(cardVal == 1 && sum<=10)
                cardVal = 11;
        else if(cardVal == 11)
        cardVal = 10;
        else if(cardVal == 12)
        cardVal = 10;
        else if(cardVal == 13)
        cardVal = 10;

        sum+=cardVal;
        numCards++;
    }  

    return sum;

 }

 public int getDealerSum() {   
    int numCards = 0;  
    int sum = 0;   
    int cardVal = 0;   
    while(dealersCards[numCards]!=null){       
    cardVal = dealersCards[numCards].getValue();       
    if(cardVal == 1 && sum<=10)             
         cardVal = 11;

        else if(cardVal == 11)
         cardVal = 10;

        else if(cardVal == 12)
         cardVal = 10;

        else if(cardVal == 13)
                cardVal = 10;

        sum+=cardVal;
        numCards++;
    }  

    return sum;

 }

 // Recursion Implemented Check \

  public int setBet(int bet)

  {

    try{   

    bet = Integer.parseInt(JOptionPane.showInputDialog("Enter Your Bet: "));

    }catch(NumberFormatException ex)

    {   bet=0; }
    if(bet <= 0 || bet > cash)
      bet=setBet(0);

        else
         labelBet.setText(Integer.toString(bet));

        return bet;
 }
 public void buildTable() {

    //***Dealer Pane***\
    dealerPane = new JPanel();
    dealerPane.setBackground(Color.gray);
    buttonDeal = new JButton("Deal");
    buttonCashOut = new JButton("Walk Away");
    dealerPane.add(buttonCashOut);
    dealerPane.add(buttonDeal);
    buttonDeal.addActionListener(new Dealer());
    buttonCashOut.addActionListener(new Walk());

    //***UserCardPane***\
    userCardPane = new JPanel();

    //***UserPane***\

    userPane = new JPanel(new GridLayout(3,2));
    Border pancakes = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Your Money");
    userPane.setBorder(pancakes);
    userPane.setBackground(Color.gray);
    buttonHit = new JButton("Hit");
    buttonStay = new JButton("Stay");
    labelBet = new JLabel("0");
    labelMoney = new JLabel("0");
    userPane.add(new JLabel("Your Total Monies: "));
    userPane.add(labelMoney);
    userPane.add(new JLabel("Your Bet: "));
    userPane.add(labelBet);
    userPane.add(buttonHit);
    userPane.add(buttonStay);
    buttonHit.addActionListener(new HitMe());
    buttonStay.addActionListener(new Stay());
    add(dealerPane, BorderLayout.NORTH);
    add(userPane, BorderLayout.SOUTH);

     // Set Starting Money

    try {

    img = ImageIO.read(new File("cards.png"));

    } catch (IOException e) {}
    cash = getStartingAmmt();
    labelMoney.setText(Integer.toString(cash));

    } 

    /**
175
    * Code for New Round
176
    */

    public void gameInit() {
    currentBet = setBet(0);
    usersCards = new Card[10];
    dealersCards = new Card[10];
    deck = new ArrayList<Card>(52);
    for(int i = 0; i<52; i++)
        deck.add(i, new Card((i%4),(i%13)+1));     

    // Give User and Dealer 2 Cards Each       
    usersCards[0] = pullRandomCard();
    usersCards[1] = pullRandomCard();
    dealersCards[0] = pullRandomCard();
    dealersCards[1] = pullRandomCard();
    repaint();
    }

     
    public int getStartingAmmt() {
        int amnt = 0;

    while(amnt<=0)
    {

        try{
        amnt = Integer.parseInt(JOptionPane.showInputDialog("Enter Starting Ammount of Money:"));

        }catch(NumberFormatException nfe){      amnt=0;     }

    }

    return amnt;

    }

 

    public Card pullRandomCard()

    {

        Random rand = new Random();

                return deck.remove(rand.nextInt(deck.size()));

    }

  /** Deal
212
    * Recreate The deck 
213
    * Give dealer and user 2 cards
214
    */

    class Dealer implements ActionListener{

        public void actionPerformed(ActionEvent ae)

        {

            gameOn = true;

            gameInit();

        }

    } 

    /** Hit Me
225
    * Draw a random card from the deck
226
    * Check the new sum of cards
227
    * if 21 user wins
228
    * if > 21 user losses
229
    * if < 21 user can choose to hit/stay
230
    */ 

    class HitMe implements ActionListener{

        public void actionPerformed(ActionEvent ae)

        {

            if(gameOn){

            usersCards[getNumUserCards()] = pullRandomCard();

            repaint();

            if(getUserSum()>21)

            {

                    // You Lose

                JOptionPane.showMessageDialog(null, "You Lost...");

                gameOn = false;

                cash-=currentBet;

                labelMoney.setText(Integer.toString(cash));

            }

            if(cash<=0)

            {

                JOptionPane.showMessageDialog(null,"You broke...");

                System.exit(0);

            }

            }

        }  

    }

   /**  
255
    * Check for winner
256
    */

    class Stay implements ActionListener{

        public void actionPerformed(ActionEvent ae)

        {

            if(gameOn){

            gameOn = false;

            if(getUserSum()>21)

            {

                // You Lose

                JOptionPane.showMessageDialog(null, "You Lost...");

                cash-=currentBet;

                labelMoney.setText(Integer.toString(cash));

            }

            // Ai

            else {
            
                while(getDealerSum()<17)

                {

                    dealersCards[getNumDealerCards()] = pullRandomCard();

                }

                if(getDealerSum()>21)

                {

                    // You Win

                    JOptionPane.showMessageDialog(null, "You Win!!");

                    cash+=currentBet;

                    labelMoney.setText(Integer.toString(cash));

                }

                else{

                    if(21-getUserSum() < 21-getDealerSum())

                    {// You win

                        JOptionPane.showMessageDialog(null, "You Win!!");

                        cash+=currentBet;

                        labelMoney.setText(Integer.toString(cash));

                    }

                    else if(getUserSum() == getDealerSum())

                    {

                        JOptionPane.showMessageDialog(null, "Tie...");

                    }

                    else // You Lose

                    {

                        JOptionPane.showMessageDialog(null, "You Lost...");

                        cash-=currentBet;

                        labelMoney.setText(Integer.toString(cash));

                    }

                }

            }

            repaint();

            if(cash<=0)

            {

                JOptionPane.showMessageDialog(null,"You broke...");

            System.exit(0);

            }

            }

        }  

    }

    /**
312
     * Leav App
313
     */

     class Walk implements ActionListener{

        public void actionPerformed(ActionEvent ae)

        {

            JOptionPane.showMessageDialog(null, "You played and walked away with $" + cash);
            System.exit(0);

        }

    }

    public static void main(String[] args) {

        new BlackJack();
    }
}
