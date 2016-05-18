package script;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Varpbits;
import org.powerbot.script.rt4.Game.Tab;

public class ScriptState {

	public static boolean ironmanMode;
	public static String ironmanModeType;
	
	public static State stateCheck(ClientContext ctx) {
		Varpbits vb = ctx.varpbits;

		if (vb.varpbit(22) == 0) {
			return State.ACCEPT;
		} else if (vb.varpbit(281) == 1000 && vb.varpbit(21) == 67108864 && vb.varpbit(843) == 0) {
			return State.FINISHED;
		} else if (vb.varpbit(281) == 670) {
			return State.TALK_TO_MAGE;
		} else if (vb.varpbit(281) == 650) {
			return State.WIN_ON_CHICKEN;
		} else if (vb.varpbit(281) == 640 && vb.varpbit(406) == 20 && vb.varpbit(1021) == 0) {
			return State.CLICK_CONTINUE;
		} else if (vb.varpbit(281) == 630 && vb.varpbit(1021) == 7) {
			return State.CLICK_MAGIC;
		} else if (vb.varpbit(281) == 620 && vb.varpbit(406) == 18 && !ironmanMode) {
			return State.TALK_TO_MAGE;
		} else if (vb.varpbit(281) == 620 && vb.varpbit(406) == 18 && ironmanMode) {
			return State.TALK_TO_ADAM;
		} else if (vb.varpbit(281) == 610 && vb.varpbit(406) == 17) {
			return State.OPEN_DOOR_LEAVE_BRACE;
		} else if (vb.varpbit(281) == 600 && vb.varpbit(1021) == 0) {
			return State.TALK_TO_BRACE;
		} else if (vb.varpbit(281) == 590 && vb.varpbit(1021) == 10) {
			return State.CLICK_IGNORE;
		} else if (vb.varpbit(281) == 580 && vb.varpbit(1021) == 9) {
			return State.CLICK_FRIENDS;
		} else if (vb.varpbit(281) == 570 && vb.varpbit(1021) == 0) {
			return State.TALK_TO_BRACE;
		} else if (vb.varpbit(281) == 560 && vb.varpbit(1021) == 6) {
			return State.CLICK_PRAYER;
		} else if (vb.varpbit(281) == 550 && vb.varpbit(406) == 16) {
			return State.TALK_TO_BRACE;
		} else if (vb.varpbit(281) == 540) {
			return State.GO_TO_FINANCE_EXIT;
		} else if (vb.varpbit(281) == 530 && vb.varpbit(406) == 15) {
			return State.TALK_TO_FINANCE;
		} else if (vb.varpbit(281) == 525) {
			return State.GO_TO_FINANCE_DOOR;
		} else if (vb.varpbit(281) == 520) {
			return State.VISIT_POLL;
		} else if (vb.varpbit(281) == 510 && vb.varpbit(406) == 14) {
			if(ctx.players.local().tile().y() > 9000) {
				return State.GO_UP_LADDER;
			}
			return State.USE_BANK;
		} else if (vb.varpbit(281) == 500) {
			return State.GO_UP_LADDER;
		} else if (vb.varpbit(281) == 490) {
			return State.SHOOT_RAT;
		} else if (vb.varpbit(281) == 480 && vb.varpbit(843) == 3) {
			return State.SHOOT_RAT;
		} else if (vb.varpbit(281) == 480 && vb.varpbit(406) == 12) {
			return State.EQUI_BOW_ARROW;
		} else if (vb.varpbit(281) == 480) {
			return State.EQUI_BOW_ARROW;
		} else if (vb.varpbit(281) == 470) {
			return State.OPEN_GATE_TALK_TO_COMBAT;
		} else if (vb.varpbit(281) == 450) {
			return State.ATTACK_RAT;
		} else if (vb.varpbit(281) == 440 && vb.varpbit(406) == 11) {
			return State.CLICK_GATE_TO_RAT;
		} else if (vb.varpbit(281) == 430 && vb.varpbit(843) == 17 && vb.varpbit(1021) == 1) {
			return State.CLICK_COMBAT_TAB;
		} else if (vb.varpbit(281) == 420) {
			return State.EQUI_SWORD_SHIELD;
		} else if (vb.varpbit(281) == 410) {
			return State.TALK_TO_COMBAT;
		} else if (vb.varpbit(281) == 405) {
			return State.CLICK_DAGGER;
		} else if (vb.varpbit(281) == 400 && vb.varpbit(1021) == 0) {
			return State.CLICK_EQUI_STATS;
		} else if (vb.varpbit(281) == 390 && vb.varpbit(1021) == 5) {
			return State.CLICK_ARMOR;
		} else if (vb.varpbit(281) == 370 && vb.varpbit(406) == 10) {
			return State.TALK_TO_COMBAT;
		} else if (vb.varpbit(281) == 360) {
			return State.OPEN_GATE_TO_COMBAT;
		} else if (vb.varpbit(281) == 340 || vb.varpbit(281) == 350) {
			return State.SMELT_DAGGER;
		} else if (vb.varpbit(281) == 330) {
			return State.TALK_TO_MINE;
		} else if (vb.varpbit(281) == 320 && vb.varpbit(406) == 9) {
			return State.SMELT_BAR;
		} else if (vb.varpbit(281) == 310) {
			return State.MINE_COPPER;
		} else if (vb.varpbit(281) == 300) {
			return State.MINE_TIN;
		} else if (vb.varpbit(281) == 290) {
			return State.TALK_TO_MINE;
		} else if (vb.varpbit(281) == 280) {
			return State.PROS_COPPER;
		} else if (vb.varpbit(281) == 270) {
			return State.PROS_TIN;
		} else if (vb.varpbit(281) == 260 && vb.varpbit(23) == 64 && vb.varpbit(406) == 8) {
			return State.TALK_TO_MINE;
		} else if (vb.varpbit(281) == 250) {
			return State.CLICK_LADDER;
		} else if (vb.varpbit(281) == 240 && vb.varpbit(1021) == 0) {
			return State.TALK_TO_QG;
		} else if (vb.varpbit(281) == 230 && vb.varpbit(1021) == 3) {
			return State.CLICK_QUEST;
		} else if (vb.varpbit(281) == 220 && vb.varpbit(406) == 7) {
			return State.TALK_TO_QG;
		} else if (vb.varpbit(281) == 210) {
			// 173 is run bit
			if (vb.varpbit(173) == 0) {
				return State.CLICK_RUN;
			}
			return State.OPEN_DOOR_TO_QG;
		} else if (vb.varpbit(281) == 200 && vb.varpbit(1021) == 0) {
			return State.CLICK_RUN;
		} else if (vb.varpbit(281) == 190 && vb.varpbit(1021) == 12) {
			return State.CLICK_OPTIONS;
		} else if (vb.varpbit(281) == 187 && vb.varpbit(1021) == 0) {
			return State.TRY_EMOTE;
		} else if (vb.varpbit(281) == 183 && vb.varpbit(406) == 6 && vb.varpbit(1021) == 13) {
			return State.CLICK_EMOTES;
		} else if (vb.varpbit(281) == 180 && vb.varpbit(1021) == 0) {
			return State.EXIT_KITCHEN;
		} else if (vb.varpbit(406) == 5 && vb.varpbit(281) == 170 && vb.varpbit(1021) == 14) {
			return State.CLICK_HARP;
		} else if (vb.varpbit(281) == 160) {
			return State.COOK_DOUGH;
		} else if (vb.varpbit(281) == 150) {
			return State.MAKE_DOUGH;
		} else if (vb.varpbit(281) == 140) {
			return State.TALK_TO_CHEF;
		} else if (vb.varpbit(281) == 130) {
			return State.OPEN_DOOR_TO_CHEF;
		} else if (vb.varpbit(281) == 120) {
			return State.OPEN_GATE;
		} else if (vb.varpbit(281) == 110 || vb.varpbit(281) == 100) {
			if (ctx.inventory.select().name("Raw shrimps").isEmpty()) {
				return State.FISH;
			} else {
				if (ctx.objects.select().name("Fire").isEmpty()) {
					if (ctx.inventory.select().name("Logs").isEmpty()) {
						return State.CHOP_TREE;
					} else {
						return State.LIGHT_FIRE;
					}
				} else {
					return State.COOK;
				}
			}
		} else if (vb.varpbit(281) == 90) {
			if (ctx.inventory.select().name("Logs").isEmpty()) {
				ctx.game.tab(Tab.INVENTORY);
				return State.CHOP_TREE;
			} else {
				return State.LIGHT_FIRE;
			}
		} else if (vb.varpbit(406) == 3 && vb.varpbit(281) == 80) {
			return State.FISH;
		} else if (vb.varpbit(281) == 70 && vb.varpbit(1021) == 0) {
			return State.TALK_TO_SE;
		} else if (vb.varpbit(281) == 60 && vb.varpbit(1021) == 2) {
			return State.CLICK_STATS;
		} else if (vb.varpbit(281) == 50) {
			return State.LIGHT_FIRE;
		} else if (vb.varpbit(281) == 40 && vb.varpbit(1021) == 0) {
			return State.CHOP_TREE;
		} else if (vb.varpbit(281) == 30 && vb.varpbit(1021) == 4) {
			return State.CLICK_INVENTORY;
		} else if (vb.varpbit(281) == 30) {
			return State.TALK_TO_SE;
		} else if (vb.varpbit(281) == 20 && vb.varpbit(406) == 2) {
			return State.TALK_TO_SE;
		} else if (vb.varpbit(281) == 10) {
			return State.OPEN_FIRST_DOOR;
		} else if (vb.varpbit(281) == 7 && vb.varpbit(1021) == 0) {
			return State.TALK_TO_RSG;
		} else if (vb.varpbit(281) == 3 && vb.varpbit(1021) == 12) {
			return State.CLICK_OPTIONS;
		} else if (vb.varpbit(22) == 33554432) {
			return State.TALK_TO_RSG;
		}

		return null;
	}

	public static enum State {
		MAKE_DOUGH, TALK_TO_CHEF, OPEN_GATE, COOK, ACCEPT, TALK_TO_RSG, CLICK_OPTIONS, CLICK_INVENTORY, CLICK_STATS, OPEN_FIRST_DOOR, TALK_TO_SE, CHOP_TREE, FISH, LIGHT_FIRE, COOK_DOUGH, CLICK_HARP, EXIT_KITCHEN, CLICK_EMOTES, TRY_EMOTE, CLICK_RUN, TALK_TO_QG, CLICK_QUEST, CLICK_LADDER, TALK_TO_MINE, PROS_TIN, PROS_COPPER, MINE_TIN, MINE_COPPER, SMELT_BAR, TALK_TO_COMBAT, CLICK_DAGGER, CLICK_EQUI_STATS, CLICK_ARMOR, SMELT_DAGGER, EQUI_SWORD_SHIELD, CLICK_COMBAT_TAB, CLICK_GATE, ATTACK_RAT, TALK_TO_MAGE, GO_UP_LADDER, OPEN_GATE_TALK_TO_COMBAT, TALK_TO_BRACE, EQUI_BOW_ARROW, SHOOT_RAT, VISIT_POLL, TALK_TO_FINANCE, WIN_ON_CHICKEN, CLICK_IGNORE, USE_BANK, CLICK_PRAYER, CLICK_FRIENDS, CLICK_CONTINUE, CLICK_MAGIC, OPEN_DOOR_TO_CHEF, OPEN_DOOR_TO_QG, OPEN_GATE_TO_COMBAT, CLICK_GATE_TO_RAT, GO_TO_FINANCE_DOOR, GO_TO_FINANCE_EXIT, OPEN_DOOR_LEAVE_BRACE, FINISHED, TALK_TO_ADAM
	}
}
