package tmbpg.czmd5.Util;

public class DeadCycleException extends RuntimeException {
  public DeadCycleException() {
    super("程序内部因为未知原因出现了死循环");
  }
}
