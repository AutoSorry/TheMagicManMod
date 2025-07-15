package basicmod.cards;

import basicmod.character.MyCharacter;
import basicmod.powers.Magics;
import basicmod.util.CardStats;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;

public class Reply extends BaseCard{
    public static final String ID = makeID("Reply");
    private static final CardStats info = new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    public static final int MAGIC = 2;
    public static final int UPG_MAGIC = 1;

    public Reply() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard[] list = new AbstractCard[]{new Bb(), new Bx(), new Yx(), new Wul(), new BangBangT(), new Azgc()};

        for (int i = 0; i < magicNumber; i++) {
            AbstractCard tmp = list[AbstractDungeon.cardRandomRng.random(list.length - 1)].makeCopy();
            tmp.upgrade();
            addToBot(new MakeTempCardInHandAction(tmp));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Reply();
    }
}
