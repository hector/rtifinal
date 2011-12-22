package rtifinal.instruments;

import rtifinal.effects.*;
import rtifinal.graphics.SpinnyCube;

public class Synthesizer extends Instrument {

  protected Delay delay;
  protected Distortion distortion;
  protected Reverb reverb;
  protected SpinnyCube cube;

  public Synthesizer() {
    delay = null;
    distortion = null;
    reverb = null;
    //instantiate cube
    cube = new SpinnyCube(200, 200, 200);
  }

  public void draw() {
    p5.translate(p5.mouseX,p5.mouseY);
    //automatic rotation
    cube.spinnyRotateX(p5.mouseX);
    cube.spinnyRotateY(p5.mouseY);
    //cube.spinnyRotateZ(p5.frameCount * p5.PI / 5);
    
    //draw cube
    p5.noStroke();
    cube.draw();
  }
}
