package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.BombPower;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class ThisBombBro extends BaseCard{
    public static final String ID = makeID("ThisBombBro");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            -1
    );

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;

    public ThisBombBro() {
        super(ID, info);

        setExhaust(true);
        setDamage(DAMAGE, UPG_DAMAGE);
        isMultiDamage = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = energyOnUse;
        }
        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }
        for (int i = 0; i < effect; i++) {
            addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        if (!freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
        if (effect > 0)
            addToBot(new ApplyPowerAction(p, p, new BombPower(p, effect)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ThisBombBro();
    }
}
