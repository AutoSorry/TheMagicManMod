package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.relics.MagicDice;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import tags.CustomTags;

public class MagicBigMove extends BaseCard{
    public static final String ID = makeID("MagicBigMove");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2
    );

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public MagicBigMove() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
        tags.add(CustomTags.MAGIC);

        cardsToPreview = new Bb();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MagicDice dice = (MagicDice) p.getRelic(MagicDice.ID);
        MagicDice.CheckResult res = dice.magicCheck();
        if (res == MagicDice.CheckResult.CRITICAL_SUCCESS) {
            for (AbstractMonster mon: AbstractDungeon.getMonsters().monsters) {
                addToBot(new ApplyPowerAction(mon, p, new VulnerablePower(mon, magicNumber * 2, false)));
                addToBot(new ApplyPowerAction(mon, p, new WeakPower(mon, magicNumber * 2, false)));
            }

        } else if (res == MagicDice.CheckResult.SUCCESS) {
            for (AbstractMonster mon: AbstractDungeon.getMonsters().monsters) {
                addToBot(new ApplyPowerAction(mon, p, new VulnerablePower(mon, magicNumber, false)));
                addToBot(new ApplyPowerAction(mon, p, new WeakPower(mon, magicNumber, false)));
            }
        } else {
            addToBot(new MakeTempCardInHandAction(new Bb(), magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagicBigMove();
    }
}
