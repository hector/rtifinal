package rtifinal.graphics;

public abstract class Polyedre extends Drawable {
  Polygon3D[] polygons;

  protected void setPolygons(Polygon3D[] polygons) {
    this.polygons = polygons;
  }

  @Override
  public void selfDraw() {
    super.selfDraw();
    for(Polygon3D polygon : polygons) {
      polygon.selfDraw();
    }
  }
  
  @Override
  public void setColor(int color) {
    super.setColor(color);
    for(Polygon3D polygon : polygons) {
      polygon.setColor(color);
    }    
  }
}
