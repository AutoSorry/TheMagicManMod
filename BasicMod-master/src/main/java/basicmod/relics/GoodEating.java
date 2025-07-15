package basicmod.relics;

import basicmod.character.MyCharacter;
import basicmod.powers.HappyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import tags.CustomTags;

import static basicmod.BasicMod.makeID;

public class GoodEating extends BaseRelic{
    private static final String NAME = "GoodEating";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.FLAT;

    public GoodEating() {
        super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        int count = 0;
        for (AbstractCard c: p.masterDeck.group) {
            if (c.hasTag(CustomTags.MAGIC)) {
                count++;
            }
        }
        flash();
        addToBot(new RelicAboveCreatureAction(p, this));
        addToBot(new ApplyPowerAction(p, p, new HappyPower(p, count / 2)));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
