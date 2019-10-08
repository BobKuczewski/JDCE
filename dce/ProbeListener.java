package dce;

public interface ProbeListener {
  public void reset(int numProbes);
  public void addLabel(String text);
  public void setSignal(int probe,int value);
  public void updateDisplay();
  }
