package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DeerCrash extends BaseCard{
    public static final String ID = makeID("DeerCrash");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2
    );

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;

    public DeerCrash() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c : p.hand.group) {
            if (c.type != CardType.ATTACK) {
                addToBot(new DiscardSpecificCardAction(c));
                addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DeerCrash();
    }
}
