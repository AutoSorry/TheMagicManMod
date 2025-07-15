package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RushRush extends BaseCard{
    public static final String ID = makeID("RushRush");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;

    public RushRush() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);

        cardsToPreview = new Rush();

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 2; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
        addToBot(new MakeTempCardInHandAction(new Rush(), 2));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RushRush();
    }
}
