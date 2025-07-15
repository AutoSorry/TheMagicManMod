package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.AllAdvantage;
import basicmod.powers.MagicFormPower;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MagicForm extends BaseCard{
    public static final String ID = makeID("MagicForm");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public MagicForm() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MagicFormPower(p, magicNumber)));
        if (!p.hasPower(AllAdvantage.POWER_ID)) {
            addToBot(new ApplyPowerAction(p, p, new AllAdvantage(p, 1)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagicForm();
    }
}
