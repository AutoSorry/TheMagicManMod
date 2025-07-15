package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GoodMorning extends BaseCard{
    public static final String ID = makeID("GoodMorning");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            3
    );

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;

    public GoodMorning() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setInnate(true);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == CardType.ATTACK) {
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            addToBot(new GainEnergyAction(1));
        }

    }

    public void applyPowers() {

        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == CardType.ATTACK) {
                count++;
            }
        }
        if (count > 0) {
            baseMagicNumber = count;
            super.applyPowers();
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }

    }

    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new GoodMorning();
    }
}
