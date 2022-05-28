package tmbpg.czmd5.Util;

public class LogUtil {
  public static void log(String msg) {
    System.out.println(msg);
  }

  public static void log(String msg, TextColor color) {
    System.out.println(color.getColor() + msg + TextColor.Default.getColor());
  }

  public static void formatLog(String format, TextColor color, Object... args) {
    log(String.format(format, args), color);
  }

  public enum TextColor {
    Default("\u001B[0m"),
    GREEN("\033[32m"),
    YELLOW("\033[33m"),
    BLUE("\033[34m"),
    MAGENTA("\033[35m"),
    CYAN("\033[36m"),
    WHITE("\033[37m"),
    RED("\033[38;5;9m");

    private final String color;

    private TextColor(String color) {
      this.color = color;
    }

    public String getColor() {
      return color;
    }
  }
}
