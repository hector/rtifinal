package rtifinal.graphics;

public class MagicCube extends Cube {
  
  Pyramid[] pyramids;
  int[] colors;
  
  public MagicCube(float size) {
    super(size);
    colors = new int[6];
    createColors();
    pyramids = new Pyramid[6];
    createPyramids();
  }
  
  private void createColors() {
    colors[0] = p5.color(255,0,0);
    colors[1] = p5.color(0,255,0);
    colors[2] = p5.color(0,0,255);
    colors[3] = p5.color(255,255,0);
    colors[4] = p5.color(255,0,255);
    colors[5] = p5.color(0,255,255);
  }
  
  private void createPyramids() {
    for(int i=0; i < pyramids.length; i++) {
      pyramids[i] = new Pyramid((Square3D)polygons[i]);
      pyramids[i].visible(false);
      pyramids[i].setColor(colors[i]);
    }
  }
  
  public void pyramidVisible(boolean visible, int pyramid) {
    pyramids[pyramid].visible(visible);
  }
  
  public void pyramidHeight(float percentage, int pyramid) {
    Pyramid p = pyramids[pyramid];
    if(p != null) p.setHeightPercentage(percentage);
  }
  
  @Override
  public void selfDraw() {
    if(!visible) return;
    super.selfDraw();
    for(Pyramid pyramid : pyramids) {
      if(pyramid != null) pyramid.selfDraw();
    }
  }

  @Override
  public void setAlpha(float alpha) {
    super.setAlpha(alpha);
    for(Pyramid pyramid : pyramids) {
      pyramid.setAlpha(alpha);
    }
  }

}
