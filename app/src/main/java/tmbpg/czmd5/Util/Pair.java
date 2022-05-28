package tmbpg.czmd5.Util;

public class Pair<M, N> {
  private M m;
  private N n;

  /**
   * 新建一个Pair
   */
  public Pair(M m, N n) {
    this.m = m;
    this.n = n;
  }

  /**
   * 获取第一个元素
   */
  public M getFirst() {
    return m;
  }

  /**
   * 获取第二个元素
   */
  public N getSecond() {
    return n;
  }

  /**
   * 快速构建一个Pair
   * 
   * @param m 第一个元素
   * @param n 第二个元素
   */
  public static <M, N> Pair<M, N> of(M m, N n) {
    return new Pair<M, N>(m, n);
  }
}
