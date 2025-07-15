package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import tags.CustomTags;

public class MagicStrike extends BaseCard{
    public static final String ID = makeID("MagicStrike");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            0
    );

    public MagicStrike() {
        super(ID, info);

        tags.add(CardTags.STRIKE);
        tags.add(CustomTags.MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
        MagicDice.CheckResult res = upgraded ? dice.magicCheck(MagicDice.CheckCondition.ADVANTAGE) : dice.magicCheck();
        baseDamage = p.getPower(Magics.POWER_ID).amount;
        calculateCardDamage(m);
        if (res == MagicDice.CheckResult.CRITICAL_SUCCESS) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage * 2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        } else if (res == MagicDice.CheckResult.SUCCESS) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    public void applyPowers() {

        int magics = AbstractDungeon.player.getPower(Magics.POWER_ID).amount;
        if (magics > 0) {
            baseDamage = magics;
            super.applyPowers();
            String desc = upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
            rawDescription = desc + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }

    }

    public void onMoveToDiscard() {
        rawDescription = upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        String desc = upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
        rawDescription = desc + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagicStrike();
    }
}
