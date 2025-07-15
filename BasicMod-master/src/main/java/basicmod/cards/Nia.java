package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Nia extends BaseCard{
    public static final String ID = makeID("Nia");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0
    );

    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;

    public Nia() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public void triggerOnCardPlayed(AbstractCard c) {
        if (c.type == CardType.ATTACK && c.cost > 0) {
            addToBot(new DiscardToHandAction(this));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Nia();
    }
}
