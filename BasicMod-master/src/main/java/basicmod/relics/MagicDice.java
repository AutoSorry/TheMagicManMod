package basicmod.relics;

import basicmod.character.MyCharacter;
import basicmod.powers.*;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import static basicmod.BasicMod.makeID;
import static com.badlogic.gdx.math.MathUtils.random;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class MagicDice extends BaseRelic {
    private static final String NAME = "MagicDice";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.SPECIAL;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static final UIStrings uiStrings1 = CardCrawlGame.languagePack.getUIString(makeID("MagicCheck"));
    public static final String[] TEXT1 = uiStrings1.TEXT;
    private static final UIStrings uiStrings2 = CardCrawlGame.languagePack.getUIString(makeID("SavingCheck"));
    public static final String[] TEXT2 = uiStrings2.TEXT;
    private static final int START_MAGIC = 10;
    public enum CheckResult{
        SUCCESS, CRITICAL_SUCCESS, FAILURE, CRITICAL_FAILURE
    }
    public enum CheckCondition{
        ADVANTAGE, NORMAL, DISADVANTAGE
    }

    public MagicDice() {
        super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atPreBattle() {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new RelicAboveCreatureAction(p, this));
        addToBot(new ApplyPowerAction(p, p, new Magics(p, START_MAGIC)));
    }

    private int getBonus(AbstractCreature p) {
        int bonus = 0;
        if (p.hasPower(HappyPower.POWER_ID)) {
            bonus += p.getPower(HappyPower.POWER_ID).amount;
        }
        if (p.hasPower(SadPower.POWER_ID)) {
            bonus -= p.getPower(SadPower.POWER_ID).amount;
        }
        if (p.hasPower(Tired.POWER_ID)) {
            bonus--;
            addToBot(new ReducePowerAction(p, p, Tired.POWER_ID, 1));
        }
        if (p.hasPower(Speeding.POWER_ID)) {
            bonus += random(1, 20);
            addToBot(new RemoveSpecificPowerAction(p, p, Speeding.POWER_ID));
        }
        return bonus;
    }

    public CheckResult magicCheck() {
        return magicCheck(0, processCondition());
    }

    public CheckResult magicCheck(CheckCondition c) {
        return magicCheck(0, processCondition(c));
    }

    private CheckCondition processCondition() {
        return processCondition(CheckCondition.NORMAL);
    }

    private CheckCondition processCondition(CheckCondition c) {
        int advantage = 0;
        switch (c) {
            case ADVANTAGE:
                advantage++;
                break;
            case DISADVANTAGE:
                advantage--;
                break;
        }
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(AllAdvantage.POWER_ID)) {
            advantage++;
        }
        if (p.hasPower(NextDisadvantage.POWER_ID)) {
            advantage--;
            addToBot(new ReducePowerAction(p, p, NextDisadvantage.POWER_ID, 1));
        }
        if (advantage == 0) {
            return CheckCondition.NORMAL;
        } else if (advantage > 0) {
            return CheckCondition.ADVANTAGE;
        } else {
            return CheckCondition.DISADVANTAGE;
        }
    }

    public CheckResult magicCheck(int delta, CheckCondition con) {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(WulPower.POWER_ID)) {
            addToBot(new GainBlockAction(p, p.getPower(WulPower.POWER_ID).amount));
        }
        CheckResult magicResult;
        int dc = p.getPower(Magics.POWER_ID).amount + delta;
        int bonus = getBonus(p);
        int rs = random(1, 20);
        if (con == CheckCondition.ADVANTAGE) {
            rs = max(rs, random(1, 20));
        } else if (con == CheckCondition.DISADVANTAGE) {
            rs = min(rs, random(1, 20));
        }
        int result = rs + bonus;
        int resultTextIdx;
        if (p.hasPower(NextAlwaysCriticalSuccess.POWER_ID)) {
            if (rs + bonus < dc || rs == 1) {
                rs = 20;
            }
            if (p.getPower(NextAlwaysCriticalSuccess.POWER_ID).amount == 1) {
                addToBot(new RemoveSpecificPowerAction(p, p, NextAlwaysCriticalSuccess.POWER_ID));
            } else {
                addToBot(new ReducePowerAction(p, p, NextAlwaysCriticalSuccess.POWER_ID, 1));
            }
        } else if (p.hasPower(NextAlwaysSuccess.POWER_ID)) {
            if (rs + bonus < dc || rs == 1) {
                result = dc;
                rs = 2;
            }
            if (p.getPower(NextAlwaysSuccess.POWER_ID).amount == 1) {
                addToBot(new RemoveSpecificPowerAction(p, p, NextAlwaysSuccess.POWER_ID));
            } else {
                addToBot(new ReducePowerAction(p, p, NextAlwaysSuccess.POWER_ID, 1));
            }
        }
        if ((rs == 1 || result < dc) && p.hasRelic(MiaowtainTop.ID)) {
            rs = random(1, 20);
            AbstractRelic r = p.getRelic(MiaowtainTop.ID);
            r.flash();
        }
        if (rs == 1) {
            magicResult = CheckResult.CRITICAL_FAILURE;
            addToBot(new DamageAction(p, new DamageInfo(p, 6, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            resultTextIdx = 6;
            result = 1;
        } else if (rs == 20) {
            if (p.hasPower(Plus1.POWER_ID)) {
                addToBot(new DrawCardAction(p, p.getPower(Plus1.POWER_ID).amount));
            }
            if (p.hasPower(Plus1Up.POWER_ID)) {
                addToBot(new DrawCardAction(p, p.getPower(Plus1Up.POWER_ID).amount));
            }
            magicResult = CheckResult.CRITICAL_SUCCESS;
            resultTextIdx = 4;
            result = 20;
        } else if (result < dc) {
            magicResult = CheckResult.FAILURE;
            resultTextIdx = 5;
        } else {
            if (p.hasPower(Plus1Up.POWER_ID)) {
                addToBot(new DrawCardAction(p, p.getPower(Plus1Up.POWER_ID).amount));
            }
            magicResult = CheckResult.SUCCESS;
            resultTextIdx = 3;
        }
        if ((magicResult == CheckResult.CRITICAL_FAILURE || magicResult == CheckResult.FAILURE)) {
            if (p.hasPower(CannotBeng.POWER_ID) && p.getPower(Magics.POWER_ID).amount > 1) {
                addToBot(new ReducePowerAction(p, p, Magics.POWER_ID, 1));
            }
            if (p.hasPower(LifeFlavorPower.POWER_ID)) {
                addToBot(new AddTemporaryHPAction(p, p, p.getPower(LifeFlavorPower.POWER_ID).amount));
            }
        }
        String text = TEXT1[0] + dc + TEXT1[1] + result + TEXT1[2] + TEXT1[resultTextIdx];
        AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, text, true));
        return magicResult;
    }

    public CheckResult savingCheck(AbstractMonster m) {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(DetectMagic.POWER_ID)) {
            addToBot(new DamageAction(m, new DamageInfo(p, p.getPower(DetectMagic.POWER_ID).amount, DamageInfo.DamageType.THORNS)));
        }
        CheckResult magicResult;
        int dc = p.getPower(Magics.POWER_ID).amount;
        int bonus = getBonus(m);
        int rs = random(1, 20);
        int result;
        int resultTextIdx;
        if (rs == 20) {
            magicResult = CheckResult.SUCCESS;
            result = 20;
            resultTextIdx = 3;
        } else if (rs == 1) {
            magicResult = CheckResult.FAILURE;
            result = 1;
            resultTextIdx = 4;
        } else if (rs + bonus < dc) {
            magicResult = CheckResult.FAILURE;
            result = rs + bonus;
            resultTextIdx = 4;
        } else {
            magicResult = CheckResult.SUCCESS;
            result = rs + bonus;
            resultTextIdx = 3;
        }
        String text = TEXT2[0] + dc + TEXT2[1] + result + TEXT2[2] + TEXT2[resultTextIdx];
        AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, text, true));
        return magicResult;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + START_MAGIC + DESCRIPTIONS[1];
    }
}