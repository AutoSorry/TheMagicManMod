package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FoodShare extends BaseCard{
    public static final String ID = makeID("FoodShare");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );

    private static final int MAGIC = 4;
    private static final int UPGRADE_MAGIC = 2;

    public FoodShare() {
        super(ID, info);

        setMagic(MAGIC, UPGRADE_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
        MagicDice.CheckResult res = dice.magicCheck();
        int magics = p.getPower(Magics.POWER_ID).amount;
        if (res == MagicDice.CheckResult.SUCCESS) {
            for (int i = 0; i < magicNumber; i++) {
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            }
        } else {
            int loseMagics = -((magics + 1) / 2);
            addToBot(new ApplyPowerAction(p, p, new Magics(p, loseMagics)));
        }
    }

    public void applyPowers() {

        int magics = AbstractDungeon.player.getPower(Magics.POWER_ID).amount;
        if (magics > 0) {
            baseDamage = magics;
            super.applyPowers();
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }

    }

    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription = rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new FoodShare();
    }
}
