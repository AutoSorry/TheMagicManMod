package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.KillLessThan16;
import basicmod.powers.Magics;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LightCast extends BaseCard{
    public static final String ID = makeID("LightCast");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ALL_ENEMY,
            2
    );

    private static final int DAMAGE = 16;
    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 4;

    public LightCast() {
        super(ID, info);

        setDamage(DAMAGE);
        isMultiDamage = true;
        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.LIGHTNING));
        if (p.getPower(Magics.POWER_ID).amount < magicNumber) {
            addToBot(new DamageRandomEnemyAction(new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        if (!p.hasPower(KillLessThan16.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new KillLessThan16(p, 1)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LightCast();
    }
}
