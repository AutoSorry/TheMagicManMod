package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class LittleDrink extends BaseCard{
    public static final String ID = makeID("LittleDrink");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.SELF_AND_ENEMY,
            1
    );

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 5;
    private static final int MAGIC = 6;

    public LittleDrink() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(p, p, new Magics(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new LittleDrink();
    }
}
