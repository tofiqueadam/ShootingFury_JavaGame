/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shootingfury;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


class FuryPanel extends JPanel
{

//Systemm.out.println(player1Hits);
  public void paintComponent(Graphics g)
  {
    Graphics2D g2D = (Graphics2D) g;
    super.paintComponent(g2D);

    if (ShootingFury.gameButton.getText().equals("Stop Game"))
    {
      ShootingFury.player1.draw(g2D);
      ShootingFury.player2.draw(g2D);
      if (ShootingFury.shooterball1.isVisible)
      {
        ShootingFury.shooterball1.draw(g2D);
      }
      if (ShootingFury.shooterball2.isVisible)
      {
        ShootingFury.shooterball2.draw(g2D);
      }
      ShootingFury.shooterman1.draw(g2D);
      ShootingFury.shooterman2.draw(g2D);
    } else
    {
      if (ShootingFury.gameButton.isEnabled())
      {
        g2D.setFont(new Font("Arial", Font.BOLD, 27));
        g2D.setPaint(Color.YELLOW);
        g2D.drawString("Click New Game", 165, 180);
      } else
      {
        ShootingFury.optionsPanel.repaint();
      }
    }

    g2D.dispose();
  }
}
