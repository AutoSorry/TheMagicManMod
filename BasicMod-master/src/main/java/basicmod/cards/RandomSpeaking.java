package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.HappyPower;
import basicmod.powers.Magics;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.badlogic.gdx.math.MathUtils.random;

public class RandomSpeaking extends BaseCard{
    public static final String ID = makeID("RandomSpeaking");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;
    private static final int MAGIC = 4;

    public RandomSpeaking() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt1 = random(1, magicNumber);
        int amt2 = random(1, magicNumber);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(p, p, new HappyPower(p, amt1)));
        addToBot(new ApplyPowerAction(p, p, new Magics(p, amt2)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RandomSpeaking();
    }
}
