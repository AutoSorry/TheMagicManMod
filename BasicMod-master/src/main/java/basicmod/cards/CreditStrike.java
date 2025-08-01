package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.HappyPower;
import basicmod.powers.SadPower;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CreditStrike extends BaseCard{
    public static final String ID = makeID("CreditStrike");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;

    public CreditStrike() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        float mb = (float) m.currentHealth / m.maxHealth;
        float pb = (float) p.currentHealth / p.maxHealth;
        if (pb > mb) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            addToBot(new ApplyPowerAction(p, p, new HappyPower(p, 1)));
        } else {
            addToBot(new ApplyPowerAction(p, p, new SadPower(p, 1)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CreditStrike();
    }
}
