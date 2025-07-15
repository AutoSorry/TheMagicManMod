package basicmod.relics;

import basicmod.character.MyCharacter;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static basicmod.BasicMod.makeID;

public class EasternLeaf extends BaseRelic{
    private static final String NAME = "EasternLeaf";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.FLAT;

    public EasternLeaf() {
        super(ID, NAME, MyCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new RelicAboveCreatureAction(p, this));
        if (p.currentHealth < p.maxHealth / 2 && p.gold >= 10) {
            addToBot(new HealAction(p, p, 10));
            p.loseGold(10);
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
