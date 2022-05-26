package tmbpg.czmd5.Util.Interface;

import tmbpg.czmd5.Util.Enum.Subject;

public interface SkillBase {
  public int getDamage(Subject target);

  public int getKnockback();

  public EffectBase[] getEffects();

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
