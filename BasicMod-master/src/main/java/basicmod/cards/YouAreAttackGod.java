package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class YouAreAttackGod extends BaseCard{
    public static final String ID = makeID("YouAreAttackGod");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public YouAreAttackGod() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.getIntentBaseDmg() >= 0) {
            MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
            MagicDice.CheckResult res = dice.magicCheck();
            if (res == MagicDice.CheckResult.CRITICAL_SUCCESS) {
                addToBot(new ApplyPowerAction(p, p, new BufferPower(p, magicNumber * 2)));
            } else if (res == MagicDice.CheckResult.SUCCESS) {
                addToBot(new ApplyPowerAction(p, p, new BufferPower(p, magicNumber)));
            } else {
                addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false)));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new YouAreAttackGod();
    }
}
