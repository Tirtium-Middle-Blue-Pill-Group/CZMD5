package tmbpg.czmd5.Util.Interface;

import tmbpg.czmd5.Util.People;

public interface SkillBase {
  /**
   * 效果空对象
   */
  public static final EffectBase[] EMPTY = new EffectBase[] {};

  /**
   * 获取技能名称
   */
  public String getName();

  /**
   * 获取技能概率
   */
  public int getTriggerProb();

  /**
   * 运行技能
   * 
   * @param source 执行技能的人物
   * @param target 技能目标
   */
  public int execute(People source, People target);

  /**
   * 获取给施加者技能效果
   * 
   * @param source 执行技能的人物
   * @param target 技能目标
   */
  default public EffectBase[] getEffectsForTarget(People source, People target) {
    return EMPTY;
  }

  /**
   * 获取被施加者技能效果
   * 
   * @param source 执行技能的人物
   * @param target 技能目标
   */
  default public EffectBase[] getEffectsForSource(People source, People target) {
    return EMPTY;
  }

  /**
   * 是否执行
   * 
   * @param source     执行技能的人物
   * @param target     技能目标
   * @param lastDamage 正常攻击伤害
   */
  public boolean shouldExecute(People source, People target, int lastDamage);

  /**
   * 获取打印到控制台的文本
   * 
   * @param source 执行技能的人物
   * @param target 技能目标
   */
  public String getMessage(People source, People target);

  public static interface ChineseSkill extends SkillBase {
  }

  public static interface MathSkill extends SkillBase {
  }

  public static interface EnglishSkill extends SkillBase {
  }

  public static interface PhysicsSkill extends SkillBase {
  }

  public static interface ChemistrySkill extends SkillBase {
  }

  public static interface BiologySkill extends SkillBase {
  }

  public static interface HistorySkill extends SkillBase {
  }

  public static interface GeographySkill extends SkillBase {
  }

  public static interface PoliticsSkill extends SkillBase {
  }

  public static interface MusicSkill extends SkillBase {
  }

  public static interface ArtSkill extends SkillBase {
  }

  public static interface SportSkill extends SkillBase {
  }

  public static interface ITSkill extends SkillBase {
  }
}
