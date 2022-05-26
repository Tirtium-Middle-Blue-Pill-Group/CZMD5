package tmbpg.czmd5.Util.Enum;

public enum Subject {
  Chinese, Math, English, Physics, Chemistry, Biology, History, Geography, Politics, Music, Art, Sport, IT, Other;

  public static boolean isOneOf(Subject subject, Subject... subjects) {
    for (Subject s : subjects)
      if (s == subject)
        return true;
    return false;
  }
}
