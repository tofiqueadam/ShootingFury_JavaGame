/*
 * ShootingFury.java
 */
package shootingfury;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.util.Random;
import java.net.URL;
import java.applet.*;

import com.ctreber.aclib.image.ico.ICOFile;

public class ShootingFury extends JFrame
{

  JLabel player1Label = new JLabel();
  JLabel player1HitsLabel = new JLabel();
  JTextField player1HitsTextField = new JTextField();
  JLabel player1LeftLabel = new JLabel();
  JTextField player1LeftTextField = new JTextField();
  JTextArea player1TextArea = new JTextArea();
  JLabel player2Label = new JLabel();
  JLabel player2HitsLabel = new JLabel();
  JTextField player2HitsTextField = new JTextField();
  JLabel player2LeftLabel = new JLabel();
  JTextField player2LeftTextField = new JTextField();
  JTextArea player2TextArea = new JTextArea();
  FuryPanel furyPanel = new FuryPanel();
  static JPanel optionsPanel = new JPanel();
  JPanel playersPanel = new JPanel();
  ButtonGroup playersButtons = new ButtonGroup();
  JRadioButton onePlayerRadioButton = new JRadioButton();
  JRadioButton twoPlayersRadioButton = new JRadioButton();
  JPanel difficultyPanel = new JPanel();
  ButtonGroup difficultyButtons = new ButtonGroup();
  JRadioButton easiestRadioButton = new JRadioButton();
  JRadioButton easyRadioButton = new JRadioButton();
  JRadioButton hardRadioButton = new JRadioButton();
  JRadioButton hardestRadioButton = new JRadioButton();
  JButton okButton = new JButton();
  static JButton gameButton = new JButton();
  JButton optionsButton = new JButton();
  JButton exitButton = new JButton();
  
  ImageIcon icn;
  
  int numberPlayers, difficulty;
  static Sprite player1, player2;
  public int player1Hits, player2Hits, player1Left, player2Left;
  final int maximumBalls = 20;
  final int playerIncrement = 5;
  static MovingSprite shooterball1, shooterball2;
  final int furyballSpeed = 20;
  static MovingSprite shooterman1, shooterman2;
  Random myRandom = new Random();
  int computerRandom, computerTime;

  Timer gameTimer;
  Timer computerTimer;

  AudioClip throwSound;
  AudioClip splatSound;
  AudioClip ouchSound;
  AudioClip gameOverSound;

  public static void main(String args[])
  {
    // create frame
    new ShootingFury();
  }

  public ShootingFury()
  {
    // frame constructor
    setTitle("Shooting Fury");
    icn = new ImageIcon("icon.png");
    
    setIconImage(icn.getImage());
    
    getContentPane().setBackground(new Color(246, 246, 244));
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    getContentPane().setLayout(new GridBagLayout());
    GridBagConstraints gridConstraints;

    Font myFont = new Font("Arial", Font.BOLD, 16);

    player1Label.setText("You:");
    player1Label.setFont(myFont);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 0;
    gridConstraints.gridwidth = 2;
    gridConstraints.insets = new Insets(10, 10, 0, 0);
    gridConstraints.anchor = GridBagConstraints.WEST;
    getContentPane().add(player1Label, gridConstraints);

    player1HitsLabel.setText("Hits");
    player1HitsLabel.setFont(myFont);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 1;
    gridConstraints.insets = new Insets(10, 10, 0, 0);
    gridConstraints.anchor = GridBagConstraints.WEST;
    getContentPane().add(player1HitsLabel, gridConstraints);

    player1HitsTextField.setPreferredSize(new Dimension(50, 25));
    player1HitsTextField.setText("0");
    player1HitsTextField.setFont(myFont);
    player1HitsTextField.setEditable(false);
    player1HitsTextField.setBackground(Color.WHITE);
    player1HitsTextField.setHorizontalAlignment(SwingConstants.CENTER);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 1;
    gridConstraints.gridy = 1;
    gridConstraints.insets = new Insets(10, 10, 0, 0);
    getContentPane().add(player1HitsTextField, gridConstraints);

    player1LeftLabel.setText("Left");
    player1LeftLabel.setFont(myFont);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 2;
    gridConstraints.insets = new Insets(10, 10, 0, 0);
    gridConstraints.anchor = GridBagConstraints.WEST;
    getContentPane().add(player1LeftLabel, gridConstraints);

    player1LeftTextField.setPreferredSize(new Dimension(50, 25));
    player1LeftTextField.setText("20");
    player1LeftTextField.setFont(myFont);
    player1LeftTextField.setEditable(false);
    player1LeftTextField.setBackground(Color.WHITE);
    player1LeftTextField.setHorizontalAlignment(SwingConstants.CENTER);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 1;
    gridConstraints.gridy = 2;
    gridConstraints.insets = new Insets(10, 10, 0, 0);
    getContentPane().add(player1LeftTextField, gridConstraints);

    player1TextArea.setPreferredSize(new Dimension(160, 60));
    player1TextArea.setText("A Key - Move Up\nZ Key - Move Down\nS Key - Toss");
    player1TextArea.setFont(new Font("Arial", Font.PLAIN, 14));
    player1TextArea.setEditable(false);
    player1TextArea.setBackground(getContentPane().getBackground());
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 2;
    gridConstraints.gridy = 1;
    gridConstraints.gridheight = 2;
    gridConstraints.insets = new Insets(10, 20, 0, 0);
    getContentPane().add(player1TextArea, gridConstraints);

    player2Label.setText("Computer:");
    player2Label.setFont(myFont);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 3;
    gridConstraints.gridy = 0;
    gridConstraints.gridwidth = 2;
    gridConstraints.insets = new Insets(10, 10, 0, 0);
    gridConstraints.anchor = GridBagConstraints.WEST;
    getContentPane().add(player2Label, gridConstraints);

    player2HitsLabel.setText("Hits");
    player2HitsLabel.setFont(myFont);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 3;
    gridConstraints.gridy = 1;
    gridConstraints.insets = new Insets(10, 10, 0, 0);
    gridConstraints.anchor = GridBagConstraints.WEST;
    getContentPane().add(player2HitsLabel, gridConstraints);

    player2HitsTextField.setPreferredSize(new Dimension(50, 25));
    player2HitsTextField.setText("0");
    player2HitsTextField.setFont(myFont);
    player2HitsTextField.setEditable(false);
    player2HitsTextField.setBackground(Color.WHITE);
    player2HitsTextField.setHorizontalAlignment(SwingConstants.CENTER);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 4;
    gridConstraints.gridy = 1;
    gridConstraints.insets = new Insets(10, 10, 0, 0);
    getContentPane().add(player2HitsTextField, gridConstraints);

    player2LeftLabel.setText("Left");
    player2LeftLabel.setFont(myFont);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 3;
    gridConstraints.gridy = 2;
    gridConstraints.insets = new Insets(10, 10, 0, 0);
    gridConstraints.anchor = GridBagConstraints.WEST;
    getContentPane().add(player2LeftLabel, gridConstraints);

    player2LeftTextField.setPreferredSize(new Dimension(50, 25));
    player2LeftTextField.setText("20");
    player2LeftTextField.setFont(myFont);
    player2LeftTextField.setEditable(false);
    player2LeftTextField.setBackground(Color.WHITE);
    player2LeftTextField.setHorizontalAlignment(SwingConstants.CENTER);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 4;
    gridConstraints.gridy = 2;
    gridConstraints.insets = new Insets(10, 10, 0, 0);
    getContentPane().add(player2LeftTextField, gridConstraints);

    player2TextArea.setPreferredSize(new Dimension(140, 60));
    player2TextArea.setText("K Key - Move Up\nM Key - Move Down\nJ Key - Toss");
    player2TextArea.setFont(new Font("Arial", Font.PLAIN, 14));
    player2TextArea.setEditable(false);
    player2TextArea.setBackground(getContentPane().getBackground());
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 5;
    gridConstraints.gridy = 1;
    gridConstraints.gridheight = 2;
    gridConstraints.insets = new Insets(10, 20, 0, 0);
    getContentPane().add(player2TextArea, gridConstraints);

///////////////the panel/////////////////////////////////////////////////////////////
    furyPanel.setPreferredSize(new Dimension(550, 350));
    furyPanel.setBackground(Color.GRAY);
    furyPanel.setLayout(new GridBagLayout());
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 3;
    gridConstraints.gridwidth = 6;
    gridConstraints.insets = new Insets(10, 10, 10, 10);
    getContentPane().add(furyPanel, gridConstraints);
    furyPanel.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        furyPanelKeyPressed(e);
      }
    });

    optionsPanel.setPreferredSize(new Dimension(220, 280));
    optionsPanel.setBackground(new Color(255, 255, 192));
    optionsPanel.setVisible(false);
    optionsPanel.setLayout(new GridBagLayout());
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 0;
    furyPanel.add(optionsPanel, gridConstraints);

    playersPanel.setPreferredSize(new Dimension(140, 55));
    playersPanel.setBorder(BorderFactory.createTitledBorder("Number of Players"));
    playersPanel.setBackground(new Color(255, 255, 192));
    playersPanel.setLayout(new GridBagLayout());
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 0;
    optionsPanel.add(playersPanel, gridConstraints);

    onePlayerRadioButton.setText("One");
    onePlayerRadioButton.setBackground(new Color(255, 255, 192));
    onePlayerRadioButton.setSelected(true);
    onePlayerRadioButton.setLayout(new GridBagLayout());
    playersButtons.add(onePlayerRadioButton);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 0;
    playersPanel.add(onePlayerRadioButton, gridConstraints);
    onePlayerRadioButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        playersRadioButtonActionPerformed(e);
      }
    });

    twoPlayersRadioButton.setText("Two");
    twoPlayersRadioButton.setBackground(new Color(255, 255, 192));
    twoPlayersRadioButton.setLayout(new GridBagLayout());
    playersButtons.add(twoPlayersRadioButton);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 1;
    gridConstraints.gridy = 0;
    playersPanel.add(twoPlayersRadioButton, gridConstraints);
    twoPlayersRadioButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        playersRadioButtonActionPerformed(e);
      }
    });

    difficultyPanel.setPreferredSize(new Dimension(140, 140));
    difficultyPanel.setBorder(BorderFactory.createTitledBorder("Difficulty"));
    difficultyPanel.setBackground(new Color(255, 255, 192));
    difficultyPanel.setLayout(new GridBagLayout());
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 1;
    optionsPanel.add(difficultyPanel, gridConstraints);

    easiestRadioButton.setText("Easiest");
    easiestRadioButton.setBackground(new Color(255, 255, 192));
    easiestRadioButton.setSelected(true);
    easiestRadioButton.setLayout(new GridBagLayout());
    difficultyButtons.add(easiestRadioButton);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 0;
    gridConstraints.anchor = GridBagConstraints.WEST;
    difficultyPanel.add(easiestRadioButton, gridConstraints);
    easiestRadioButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        difficultyRadioButtonActionPerformed(e);
      }
    });

    easyRadioButton.setText("Easy");
    easyRadioButton.setBackground(new Color(255, 255, 192));
    easyRadioButton.setLayout(new GridBagLayout());
    difficultyButtons.add(easyRadioButton);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 1;
    gridConstraints.anchor = GridBagConstraints.WEST;
    difficultyPanel.add(easyRadioButton, gridConstraints);
    easyRadioButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        difficultyRadioButtonActionPerformed(e);
      }
    });

    hardRadioButton.setText("Hard");
    hardRadioButton.setBackground(new Color(255, 255, 192));
    hardRadioButton.setLayout(new GridBagLayout());
    difficultyButtons.add(hardRadioButton);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 2;
    gridConstraints.anchor = GridBagConstraints.WEST;
    difficultyPanel.add(hardRadioButton, gridConstraints);
    hardRadioButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        difficultyRadioButtonActionPerformed(e);
      }
    });

    hardestRadioButton.setText("Hardest");
    hardestRadioButton.setBackground(new Color(255, 255, 192));
    hardestRadioButton.setLayout(new GridBagLayout());
    difficultyButtons.add(hardestRadioButton);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 3;
    gridConstraints.anchor = GridBagConstraints.WEST;
    difficultyPanel.add(hardestRadioButton, gridConstraints);
    hardestRadioButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        difficultyRadioButtonActionPerformed(e);
      }
    });

    okButton.setText("OK");
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 2;
    gridConstraints.insets = new Insets(10, 0, 0, 0);
    optionsPanel.add(okButton, gridConstraints);
    okButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        okButtonActionPerformed(e);
      }
    });

    gameButton.setText("New Game");
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 4;
    gridConstraints.gridwidth = 2;
    gridConstraints.insets = new Insets(0, 10, 10, 0);
    getContentPane().add(gameButton, gridConstraints);
    gameButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        gameButtonActionPerformed(e);
      }
    });

    optionsButton.setText("Options");
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 2;
    gridConstraints.gridy = 4;
    gridConstraints.insets = new Insets(0, 0, 10, 0);
    getContentPane().add(optionsButton, gridConstraints);
    optionsButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        optionsButtonActionPerformed(e);
      }
    });

    exitButton.setText("Exit");
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 3;
    gridConstraints.gridy = 4;
    gridConstraints.gridwidth = 2;
    gridConstraints.insets = new Insets(0, 0, 10, 0);
    getContentPane().add(exitButton, gridConstraints);
    exitButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        exitButtonActionPerformed(e);
      }
    });

    gameTimer = new Timer(50, new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        gameTimerActionPerformed(e);
      }
    });

    computerTimer = new Timer(1000, new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        computerTimerActionPerformed(e);
      }
    });

    setVisible(true);
    
    pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());

    try
    {
      BufferedReader inputFile = new BufferedReader(new FileReader("snowball.ini"));
      numberPlayers = Integer.valueOf(inputFile.readLine()).intValue();
      difficulty = Integer.valueOf(inputFile.readLine()).intValue();
      inputFile.close();
    } catch (Exception ex)
    {
      numberPlayers = 1;
      difficulty = 1;
    }
    if (difficulty == 1)
    {
      easiestRadioButton.doClick();
    } else if (difficulty == 2)
    {
      easyRadioButton.doClick();
    } else if (difficulty == 3)
    {
      hardRadioButton.doClick();
    } else
    {
      hardestRadioButton.doClick();
    }
    if (numberPlayers == 1)
    {
      onePlayerRadioButton.doClick();
    } else
    {
      twoPlayersRadioButton.doClick();
    }

    player1Left = maximumBalls;
    player2Left = maximumBalls;
    player1LeftTextField.setText(String.valueOf(player1Left));
    player2LeftTextField.setText(String.valueOf(player2Left));

    // create sprites
    player1 = new Sprite();
    player2 = new Sprite();
    shooterball1 = new MovingSprite();
    shooterball2 = new MovingSprite();
    shooterman1 = new MovingSprite();
    shooterman2 = new MovingSprite();
    
    // read in icon files and sounds
    try
    {
      player1.image = new ICOFile("player1.ico").getDescriptor(0).getImageRGB();
      player2.image = new ICOFile("player2.ico").getDescriptor(0).getImageRGB();
      shooterball1.image = new ICOFile("player1ball.ico").getDescriptor(0).getImageRGB();
      shooterball2.image = new ICOFile("player2ball.ico").getDescriptor(0).getImageRGB();
      shooterman1.image = new ICOFile("snowman.ico").getDescriptor(0).getImageRGB();
      shooterman2.image = new ICOFile("snowman.ico").getDescriptor(0).getImageRGB();
      throwSound = Applet.newAudioClip(new URL("file:" + "throw.wav"));
      splatSound = Applet.newAudioClip(new URL("file:" + "splat.wav"));
      ouchSound = Applet.newAudioClip(new URL("file:" + "ouch.wav"));
      gameOverSound = Applet.newAudioClip(new URL("file:" + "gameover.wav"));
    } catch (Exception ex)
    {
  
    }

  }

  private void exitForm(WindowEvent evt)
  {
    try
    {
      PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter("furyball.ini")));
      outputFile.println(numberPlayers);
      outputFile.println(difficulty);
      outputFile.flush();
      outputFile.close();
    } catch (Exception ex)
    {
    }
    System.exit(0);
  }

  private void playersRadioButtonActionPerformed(ActionEvent e)
  {
    if (e.getActionCommand().equals("One"))
    {
      numberPlayers = 1;
      player1Label.setText("You:");
      player2Label.setText("Computer:");
      player2TextArea.setVisible(false);
      difficultyPanel.setVisible(true);
    } else
    {
      numberPlayers = 2;
      player1Label.setText("Player 1:");
      player2Label.setText("Player 2:");
      player2TextArea.setVisible(true);
      difficultyPanel.setVisible(false);
    }
  }

  private void difficultyRadioButtonActionPerformed(ActionEvent e)
  {
    String s = e.getActionCommand();
    if (s.equals("Easiest"))
    {
      difficulty = 1;
      computerRandom = 100;
      computerTime = 1000;
    } else if (s.equals("Easy"))
    {
      difficulty = 2;
      computerRandom = 75;
      computerTime = 750;
    } else if (s.equals("Hard"))
    {
      difficulty = 3;
      computerRandom = 50;
      computerTime = 500;
    } else if (s.equals("Hardest"))
    {
      difficulty = 4;
      computerRandom = 25;
      computerTime = 250;
    }
    computerTimer.setDelay(computerTime);
  }

  private void okButtonActionPerformed(ActionEvent e)
  {
    gameButton.setEnabled(true);
    optionsButton.setEnabled(true);
    exitButton.setEnabled(true);
    optionsPanel.setVisible(false);
  }

  private void gameButtonActionPerformed(ActionEvent e)
  {
    if (gameButton.getText().equals("New Game"))
    {
      gameButton.setText("Stop Game");
      optionsButton.setEnabled(false);
      exitButton.setEnabled(false);
      player1Hits = 0;
      player2Hits = 0;
      player1HitsTextField.setText("0");
      player2HitsTextField.setText("0");
      player1Left = maximumBalls;
      player2Left = maximumBalls;
      player1LeftTextField.setText(String.valueOf(player1Left));
      player2LeftTextField.setText(String.valueOf(player2Left));
      player1.rectangle = new Rectangle2D.Double(5, 0.5 * furyPanel.getHeight() - 0.5 * player1.image.getHeight(null), player1.image.getWidth(null), player1.image.getHeight(null));
      player2.rectangle = new Rectangle2D.Double(furyPanel.getWidth() - player2.image.getWidth(null) - 5, 0.5 * furyPanel.getHeight() - 0.5 * player2.image.getHeight(null), player2.image.getWidth(null), player2.image.getHeight(null));
      player1.isVisible = true;
      player2.isVisible = true;
      shooterman1.rectangle = new Rectangle2D.Double(0.5 * furyPanel.getWidth() - shooterman1.image.getWidth(null), myRandom.nextInt(furyPanel.getHeight()), shooterman1.image.getWidth(null), shooterman1.image.getHeight(null));
      shooterman2.rectangle = new Rectangle2D.Double(0.5 * furyPanel.getWidth(), myRandom.nextInt(furyPanel.getHeight()), shooterman2.image.getWidth(null), shooterman2.image.getHeight(null));
      shooterman1.ySpeed = shieldSpeed();
      shooterman2.ySpeed = shieldSpeed();
      shooterman1.isVisible = true;
      shooterman2.isVisible = true;
      furyPanel.repaint();
      gameTimer.start();
      if (numberPlayers == 1)
      {
        computerTimer.start();
      }
      furyPanel.requestFocus();
    } else
    {
      gameTimer.stop();
      computerTimer.stop();
      player1.isVisible = false;
      player2.isVisible = false;
      shooterball1.isVisible = false;
      shooterball2.isVisible = false;
      shooterman1.isVisible = false;
      shooterman2.isVisible = false;
      gameButton.setText("New Game");
      optionsButton.setEnabled(true);
      exitButton.setEnabled(true);
      furyPanel.repaint();
    }
  }

  private void optionsButtonActionPerformed(ActionEvent e)
  {
    gameButton.setEnabled(false);
    optionsButton.setEnabled(false);
    exitButton.setEnabled(false);
    optionsPanel.setVisible(true);
  }

  private void exitButtonActionPerformed(ActionEvent e)
  {
    exitForm(null);
  }

  private void furyPanelKeyPressed(KeyEvent e)
  {
    if (gameButton.getText().equals("New Game"))
    {
      return;
    }
    // get current location for possible update
    double newY1 = player1.rectangle.getY(), newY2 = player2.rectangle.getY();
    if (e.getKeyCode() == e.VK_A)
    {
      newY1 -= playerIncrement;
      if (newY1 < 0)
      {
        newY1 = 0;
      }
    } else if (e.getKeyCode() == e.VK_Z)
    {
      newY1 += playerIncrement;
      if (newY1 > furyPanel.getHeight() - player1.rectangle.getHeight())
      {
        newY1 = furyPanel.getHeight() - player1.rectangle.getHeight();
      }
    } else if (e.getKeyCode() == e.VK_S)
    {
      if (!shooterball1.isVisible && player1Left > 0)
      {
        throwSound.play();
        player1Left--;
        player1LeftTextField.setText(String.valueOf(player1Left));
        shooterball1.rectangle = new Rectangle2D.Double(player1.rectangle.getX() + player1.rectangle.getWidth(), player1.rectangle.getY(), shooterball1.image.getWidth(null), shooterball1.image.getHeight(null));
        shooterball1.isVisible = true;
      }
    } else if (e.getKeyCode() == e.VK_K)
    {
      newY2 -= playerIncrement;
      if (newY2 < 0)
      {
        newY2 = 0;
      }
    } else if (e.getKeyCode() == e.VK_M)
    {
      newY2 += playerIncrement;
      if (newY2 > furyPanel.getHeight() - player2.rectangle.getHeight())
      {
        newY2 = furyPanel.getHeight() - player2.rectangle.getHeight();
      }
    } else if (e.getKeyCode() == e.VK_J)
    {
      if (!shooterball2.isVisible && player2Left > 0)
      {
        throwSound.play();
        player2Left--;
        player2LeftTextField.setText(String.valueOf(player2Left));
        shooterball2.rectangle = new Rectangle2D.Double(player2.rectangle.getX() - shooterball2.image.getWidth(null), player2.rectangle.getY(), shooterball2.image.getWidth(null), shooterball2.image.getHeight(null));
        shooterball2.isVisible = true;
      }
    }
    player1.rectangle = new Rectangle2D.Double(player1.rectangle.getX(), newY1, player1.rectangle.getWidth(), player1.rectangle.getHeight());
    player2.rectangle = new Rectangle2D.Double(player2.rectangle.getX(), newY2, player2.rectangle.getWidth(), player2.rectangle.getHeight());
    furyPanel.repaint();
  }

  private void gameTimerActionPerformed(ActionEvent e)
  {
    // move snowmen
    shooterman1.move(0, shooterman1.ySpeed);
    if (shooterman1.rectangle.getY() < -shooterman1.image.getHeight(null) || shooterman1.rectangle.getY() > furyPanel.getHeight())
    {
      // recompute speed
      shooterman1.ySpeed = shieldSpeed();
      if (shooterman1.ySpeed > 0)
      {
        shooterman1.rectangle = new Rectangle2D.Double(shooterman1.rectangle.getX(), -shooterman1.image.getHeight(null), shooterman1.rectangle.getWidth(), shooterman1.rectangle.getHeight());
      } else
      {
        shooterman1.rectangle = new Rectangle2D.Double(shooterman1.rectangle.getX(), furyPanel.getHeight(), shooterman1.rectangle.getWidth(), shooterman1.rectangle.getHeight());
      }
    }
    shooterman2.move(0, shooterman2.ySpeed);
    if (shooterman2.rectangle.getY() < -shooterman2.image.getHeight(null) || shooterman2.rectangle.getY() > furyPanel.getHeight())
    {
      // recompute speed
      shooterman2.ySpeed = shieldSpeed();
      if (shooterman2.ySpeed > 0)
      {
        shooterman2.rectangle = new Rectangle2D.Double(shooterman2.rectangle.getX(), -shooterman2.image.getHeight(null), shooterman2.rectangle.getWidth(), shooterman2.rectangle.getHeight());
      } else
      {
        shooterman2.rectangle = new Rectangle2D.Double(shooterman2.rectangle.getX(), furyPanel.getHeight(), shooterman2.rectangle.getWidth(), shooterman2.rectangle.getHeight());
      }
    }
    // status of player 1 snowball
    if (shooterball1.isVisible)
    {
      shooterball1.move(furyballSpeed, 0);
      if (shooterball1.rectangle.getX() > furyPanel.getWidth())
      {
        shooterball1.isVisible = false; // off screen
      } else if (shooterball1.collided(player2.rectangle))
      {
        ouchSound.play();
        player1Hits++;
        player1HitsTextField.setText(String.valueOf(player1Hits));
        shooterball1.isVisible = false;
      } else if (shooterball1.collided(shooterman1.rectangle) || shooterball1.collided(shooterman2.rectangle))
      {
        splatSound.play();
        shooterball1.isVisible = false;
      }
    }
    // status of player 2 snowball
    if (shooterball2.isVisible)
    {
      shooterball2.move(-furyballSpeed, 0);
      if (shooterball2.rectangle.getX() < 0)
      {
        shooterball2.isVisible = false; // off screen
      } else if (shooterball2.collided(player1.rectangle))
      {
        ouchSound.play();
        player2Hits++;
        player2HitsTextField.setText(String.valueOf(player2Hits));
        shooterball2.isVisible = false;
      } else if (shooterball2.collided(shooterman1.rectangle) || shooterball2.collided(shooterman2.rectangle))
      {
        splatSound.play();
        shooterball2.isVisible = false;
      }
    }
    furyPanel.repaint();
    // check status of game
    if (!shooterball1.isVisible && player1Left == 0 && !shooterball2.isVisible && player2Left == 0)
    {
      gameOverSound.play();
      gameButton.doClick();
    }
  }

  private void computerTimerActionPerformed(ActionEvent e)
  {
    int i;
    if (myRandom.nextInt(100) < computerRandom)
    {
      i = myRandom.nextInt(5); // random move
      if (i == 0)
      {
        if (player2Left >= player1Left)
        {
          robotKeyPress(KeyEvent.VK_J); // take toss
        }
      } else if (i <= 2)
      {
        robotKeyPress(KeyEvent.VK_K); // move up
      } else
      {
        robotKeyPress(KeyEvent.VK_M); // move down
      }
    } else
    {
      if (Math.abs(player1.rectangle.getY() - player2.rectangle.getY()) < (int) (0.8 * player1.image.getHeight(null)) && player2Left >= player1Left)
      {
        robotKeyPress(KeyEvent.VK_J); // take toss
      }
      if (shooterball1.isVisible || player2Left == 0)
      {
        if (player1.rectangle.getY() - player2.rectangle.getY() < 0)
        {
          robotKeyPress(KeyEvent.VK_M); // move down
        } else
        {
          robotKeyPress(KeyEvent.VK_K); // move up
        }
      } else
      {
        if (player1.rectangle.getY() - player2.rectangle.getY() < 0)
        {
          robotKeyPress(KeyEvent.VK_K); // move up
        } else
        {
          robotKeyPress(KeyEvent.VK_M); // move down
        }
      }
    }
  }

  private int shieldSpeed()
  {
    final int speedMin = 1;
    final int speedMax = 4;
    int speed;
    speed = myRandom.nextInt(speedMax - speedMin + 1) + speedMin;
    if (myRandom.nextInt(2) == 0)
    {
      speed = -speed;
    }
    return (speed);
  }

  private void robotKeyPress(int k)
  {
    try
    {
      Robot robot = new Robot();
      robot.keyPress(k);
    } catch (Exception ex)
    {
    }
  }

}

