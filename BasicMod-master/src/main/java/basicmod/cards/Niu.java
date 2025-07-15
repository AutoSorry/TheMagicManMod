package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static com.badlogic.gdx.math.MathUtils.random;

public class Niu extends BaseCard{
    public static final String ID = makeID("Niu");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );

    public Niu() {
        super(ID, info);

        setExhaust(true);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = p.powers.size();
        int pow = random(0, count - 1);
        if (upgraded) {
            while (p.powers.get(pow).type == AbstractPower.PowerType.DEBUFF) {
                pow = random(0, count - 1);
            }
            addToBot(new ApplyPowerAction(p, p, p.powers.get(pow)));
        } else {
            addToBot(new ApplyPowerAction(p, p, p.powers.get(pow)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Niu();
    }
}
