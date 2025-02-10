package shootingfury;

import java.awt.geom.*;
import java.awt.*;

public class Sprite
{
  //Image displayed
  public Image image;
  
  //Rectangle2D.Double describing size and location
  public Rectangle2D.Double rectangle;
  
  //boolean type saying whether object is visible
  public boolean isVisible = false;

/*  x x-coordinate of the upper-left corner of the rectangle
y y-coordinate of the upper-left corner of the rectangle
height- Width of the rectangle
width- Height of the rectangle
*/
/*myRectangle.getX();
myRectangle.getY();
myRectangle.getWidth();
mRectangle.getHeight();
*/
  /*myRectangle.setX(newX);
myRectangle.setY(newY);
myRectangle.setWidth(newWidth);
mRectangle.setHeight(newHeight);*/
  
  public void draw(Graphics2D g2D)
  {
    g2D.drawImage(this.image, (int) this.rectangle.getX(), (int) this.rectangle.getY(), null);
  }

  //Successive application of each of these steps for each image in our graphics
  //region results in a nice smooth animated motion
  
  public void move(int dx, int dy)
  {
    this.rectangle.setRect(this.rectangle.getX() + dx, this.rectangle.getY() + dy, this.rectangle.getWidth(), this.rectangle.getHeight());
  }
}
//keyword this to refer to the current object. Once the object is
//placed on g2D.