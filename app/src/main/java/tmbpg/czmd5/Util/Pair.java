package tmbpg.czmd5.Util;

public class Pair<M, N> {
  private M m;
  private N n;

  public Pair(M m, N n) {
    this.m = m;
    this.n = n;
  }

  public M getFirst() {
    return m;
  }

  public N getSecond() {
    return n;
  }

  public static <M, N> Pair<M, N> of(M m, N n) {
    return new Pair<M, N>(m, n);
  }
}
