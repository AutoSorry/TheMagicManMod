package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AutoplayCardAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tags.CustomTags;

public class BrilliantAppearance extends BaseCard{
    public static final String ID = makeID("BrilliantAppearance");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            0
    );

    private static final int DAMAGE = 18;
    private static final int UPG_DAMAGE = 6;
    private static final int MAGIC = 3;
    private static final int UPE_MAGIC = 2;

    public BrilliantAppearance() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        isMultiDamage = true;
        setMagic(MAGIC, UPE_MAGIC);
        setExhaust(true);
        setInnate(true);
        tags.add(CustomTags.MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
        MagicDice.CheckResult res = dice.magicCheck();
        if (res == MagicDice.CheckResult.SUCCESS) {
            addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        } else if (res == MagicDice.CheckResult.CRITICAL_SUCCESS){
            addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        } else {
            addToBot(new ApplyPowerAction(p, p, new Magics(p, magicNumber)));
        }
        addToBot(new ApplyPowerAction(p, p, new Magics(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BrilliantAppearance();
    }
}
