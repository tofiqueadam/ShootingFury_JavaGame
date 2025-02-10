package shootingfury;

import java.awt.geom.*;

public class MovingSprite extends Sprite
{

  public int xSpeed;
  public int ySpeed;

  public boolean collided(Rectangle2D.Double r)
  {
    return (!this.rectangle.createIntersection(r).isEmpty());
  }
}
