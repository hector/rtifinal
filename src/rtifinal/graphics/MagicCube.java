package rtifinal.graphics;

public class MagicCube extends Cube {
  
  Pyramid[] pyramids;
  int[] colors;
  
  public MagicCube(float size) {
    super(size);
    pyramids = new Pyramid[6];
    colors = new int[6];
    createColors();
  }
  
  private void createColors() {
    colors[0] = p5.color(255,0,0);
    colors[1] = p5.color(0,255,0);
    colors[2] = p5.color(0,0,255);
    colors[3] = p5.color(255,255,0);
    colors[4] = p5.color(255,0,255);
    colors[5] = p5.color(0,255,255);
  }
  
  // pyramid number goes from 0 to 5
  public void addPyramid(int pyramid) {
    if((pyramid <= 5) && (pyramid >= 0)) {
      pyramids[pyramid] = new Pyramid((Square3D)polygons[pyramid]);
      pyramids[pyramid].setColor(colors[pyramid]);
    }
  }
  
  public void removePyramid(int pyramid) {
    if((pyramid <= 5) && (pyramid >= 0)) {
      pyramids[pyramid] = null;
    }
  }  
  
  @Override
  public void selfDraw() {
    super.selfDraw();
    for(Pyramid pyramid : pyramids) {
      if(pyramid != null) pyramid.selfDraw();
    }
  }

}
