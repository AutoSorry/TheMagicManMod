package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tags.CustomTags;

import static java.lang.Math.max;

public class SingleSix extends BaseCard{
    public static final String ID = makeID("SingleSix");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            6
    );

    private static final int DAMAGE = 6;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public SingleSix() {
        super(ID, info);

        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (cost >= magicNumber) {
            costForTurn = max(0, costForTurn - magicNumber);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 6; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SingleSix();
    }
}
