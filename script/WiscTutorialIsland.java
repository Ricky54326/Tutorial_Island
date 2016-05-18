package script;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.swing.JOptionPane;

import org.powerbot.script.Condition;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game.Tab;

import script.task.CastWindTask;
import script.task.ChopTask;
import script.task.CookDoughTask;
import script.task.CookShrimpTask;
import script.task.FishTask;
import script.task.LightFireTask;
import script.task.MakeDoughTask;
import script.task.OpenDoorTask;
import script.task.OreTask;
import script.task.SmeltBarTask;
import script.task.SmeltDaggerTask;
import script.task.TalkToGuideTask;
import script.task.Task;
import script.task.UseBankTask;
import script.task.VisitPollTask;

import org.powerbot.script.rt4.Npc;

@Script.Manifest(
		name = "Wisc Tutorial Island",
		description = "Superior Tutorial Island for OSRS!",
		properties="author=Naquadah; topic=XXXXXXX; client=4;"
		)

public class WiscTutorialIsland extends PollingScript<ClientContext> implements PaintListener {

	@SuppressWarnings("rawtypes")
	private List<Task> tasks = new ArrayList<Task>();
	
	private ScriptState.State currentState;
	
	private boolean outOfRatGate = false;
	private boolean braceFlag = false;
	
	private final int[] defaultBounds = {-32, 32, -64, 0, -32, 32};
	private final int[] firstDoorBounds = {-4, 28, -224, 0, 4, 120};
	private final int[] firstGateBounds = {-12, 8, -104, 0, -104, 112};
	private final int[] kitchenEntranceBounds = {0, 24, -208, 8, 20, 108};
	private final int[] kitchenExitBounds = {116, 136, -216, 0, 20, 128};
	private final int[] qestGuideBounds = {8, 112, -224, 12, 0, 20};
	private final int[] gateToCombatBounds = {112, 136, -196, 0, -88, 92};
	private final int[] doorToFinanceBounds = {0, 24, -208, 0, 16, 108};
	private final int[] doorExitFinanceBounds = {-8, 24, -196, 0, 12, 112};
	private final int[] doorLeaveBraceBounds = {12, 112, -212, -4, 120, 136};
	
	@Override
	public void start() {
		ScriptState.ironmanMode = JOptionPane.showConfirmDialog(null, "Activate Ironman Mode?", "Rob's Tutorial Island",
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		
		if(ScriptState.ironmanMode) {
			String[] ironmanModeTypes = {"Standard", "Ultimate"};
			ScriptState.ironmanModeType = (String) JOptionPane.showInputDialog(null, "Which type of Ironman Mode?", "Rob's Tutorial Island", JOptionPane.QUESTION_MESSAGE, null, ironmanModeTypes, "Standard");
		}
		  
		System.out.println("Script Started!");
		currentState = null;
	}
	
	private void setState() {
		ScriptState.State pollState = ScriptState.stateCheck(ctx);

		switch (pollState) {
		case ACCEPT:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			ctx.widgets.component(269, 100).click();
			break;
		case TALK_TO_RSG:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new TalkToGuideTask(ctx, "RuneScape Guide"));
			}
			break;
		case CLICK_OPTIONS:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			if (ctx.widgets.widget(231).valid()) {
				ctx.widgets.component(231, 2).click();
			}
			ctx.game.tab(Tab.OPTIONS);
			break;
		case OPEN_FIRST_DOOR:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OpenDoorTask(ctx, "Door", new Tile(3098, 3107, 0), firstDoorBounds));
			}
			break;
		case TALK_TO_SE:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new TalkToGuideTask(ctx, "Survival Expert"));
			}
			break;
		case CHOP_TREE:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new ChopTask(ctx));
			}
			break;
		case LIGHT_FIRE:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new LightFireTask(ctx));
			}
			break;
		case MAKE_DOUGH:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new MakeDoughTask(ctx));
			}
			break;
		case TALK_TO_CHEF:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new TalkToGuideTask(ctx, "Master Chef"));
			}
			break;
		case OPEN_GATE:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OpenDoorTask(ctx, "Gate", new Tile(3090, 3091, 0), firstGateBounds));
			}
			break;
		case COOK:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new CookShrimpTask(ctx));
			}
			break;
		case CLICK_INVENTORY:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			ctx.game.tab(Tab.INVENTORY);
			break;
		case CLICK_STATS:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			ctx.game.tab(Tab.STATS);
			break;
		case FISH:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new FishTask(ctx));
			}
			break;
		case OPEN_DOOR_TO_CHEF:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OpenDoorTask(ctx, "Door", new Tile(3079, 3084, 0), kitchenEntranceBounds));
			}
			break;
		case COOK_DOUGH:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new CookDoughTask(ctx));
			}
			if (ctx.widgets.contains(ctx.widgets.widget(229))) {
				ctx.widgets.component(229, 1).click();
			}
			break;
		case CLICK_HARP:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			if(ctx.widgets.component(229, 1).visible()) {
				ctx.widgets.component(229, 1).click();
			}
			ctx.game.tab(Tab.MUSIC);
			break;
		case EXIT_KITCHEN:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OpenDoorTask(ctx, "Door", new Tile(3074, 3089, 0), kitchenExitBounds));
			}
			if (ctx.widgets.component(229, 1).visible()) {
				ctx.widgets.component(229, 1).click();
			}
			break;
		case CLICK_EMOTES:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			ctx.game.tab(Tab.EMOTES);
			break;
		case TRY_EMOTE:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			ctx.game.tab(Tab.EMOTES);
			ctx.input.click(ctx.widgets.component(261, 1).centerPoint(), true);
			break;
		case CLICK_RUN:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			ctx.game.tab(Tab.OPTIONS);
			ctx.input.click(ctx.widgets.component(261, 65).centerPoint(), true);
			break;
		case OPEN_DOOR_TO_QG:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OpenDoorTask(ctx, "Door", new Tile(3086, 3126, 0), qestGuideBounds));
			}
			break;
		case TALK_TO_QG:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new TalkToGuideTask(ctx, "Quest Guide"));
			}
			break;
		case CLICK_QUEST:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			ctx.game.tab(Tab.QUESTS);
			break;
		case CLICK_LADDER:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OpenDoorTask(ctx, "Ladder", new Tile(3088, 3120, 0), defaultBounds));
			}
			break;
		case TALK_TO_MINE:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new TalkToGuideTask(ctx, "Mining Instructor"));
			}
			break;
		case PROS_TIN:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OreTask(ctx, 10080, "Rocks", "Prospect"));
			}
			break;
		case PROS_COPPER:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OreTask(ctx, 10079, "Rocks", "Prospect"));
			}
			break;
		case MINE_TIN:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OreTask(ctx, 10080, "Rocks", "Mine"));
			}
			break;
		case MINE_COPPER:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OreTask(ctx, 10079, "Rocks", "Mine"));
			}
			break;
		case SMELT_BAR:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new SmeltBarTask(ctx));
			}
			ctx.game.tab(Tab.INVENTORY);
			break;
		case TALK_TO_COMBAT:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new TalkToGuideTask(ctx, "Combat Instructor"));
			}
			if(ctx.widgets.component(219, 0).component(3).visible()) {
				ctx.widgets.component(219, 0).component(3).click();
			}
			break;
		case CLICK_DAGGER:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}

			if (ctx.widgets.contains(ctx.widgets.widget(84))) {
				ctx.input.click(ctx.widgets.component(84, 4).centerPoint(), true);
			}
			ctx.game.tab(Tab.INVENTORY);
			ctx.inventory.select().name("Bronze dagger").poll().click();
			break;
		case CLICK_EQUI_STATS:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			ctx.game.tab(Tab.EQUIPMENT);
			if(ctx.widgets.widget(182).valid()) {
				ctx.input.click(ctx.widgets.component(182, 8).centerPoint(), true);
				ctx.widgets.component(84, 4).click();
			}
			break;
		case CLICK_ARMOR:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			ctx.game.tab(Tab.EQUIPMENT);
			break;
		case SMELT_DAGGER:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new SmeltDaggerTask(ctx));
			}
			break;
		case OPEN_GATE_TO_COMBAT:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OpenDoorTask(ctx, "Gate", new Tile(3095, 9502, 0), gateToCombatBounds));
			}
			break;
		case EQUI_SWORD_SHIELD:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}

			ctx.game.tab(Tab.INVENTORY);
			ctx.inventory.select().name("Bronze sword").poll().click();
			ctx.inventory.select().name("Wooden shield").poll().click();
			break;
		case CLICK_COMBAT_TAB:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			ctx.game.tab(Tab.ATTACK);
			break;
		case CLICK_GATE_TO_RAT:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OpenDoorTask(ctx, "Gate", new Tile(3111, 9519, 0), defaultBounds));
			}
			break;
		case ATTACK_RAT:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			Npc rat = ctx.npcs.select().name("Giant rat").nearest().poll();
			rat.interact("Attack");
			Condition.wait(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return !ctx.players.local().inCombat();
				}

			});
			break;
		case TALK_TO_MAGE:
			if (ctx.chat.canContinue()) {
				ctx.chat.clickContinue();
			} else if (ctx.chat.chatting()) {
				ctx.chat.select().text("Yes.").poll().select();
			} else if (ctx.widgets.widget(193).valid()) {
				tasks.clear();
				ctx.widgets.component(193, 2).click();
			} else if (ctx.widgets.widget(231).valid()) {
				tasks.clear();
				ctx.widgets.component(231, 2).click();
			}

			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				if (ctx.players.local().tile().compareTo(new Tile(3130, 3088, 0)) < 0) {
					ctx.movement.step(new Tile(3130, 3088, 0));
				}

				tasks.add(new TalkToGuideTask(ctx, "Magic Instructor"));
			}

			break;
		case GO_UP_LADDER:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OpenDoorTask(ctx, "Ladder", new Tile(3111, 9527, 0), defaultBounds));
			}
			break;
		case OPEN_GATE_TALK_TO_COMBAT:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}

			if (ctx.players.local().tile().compareTo(new Tile(3110, 9519, 0)) > 0 && tasks.size() < 2) {
				tasks.clear();
				tasks.add(new TalkToGuideTask(ctx, "Combat Instructor"));
				outOfRatGate = true;
			} else if (!outOfRatGate && tasks.size() < 2) {
				tasks.clear();
				tasks.add(new OpenDoorTask(ctx, "Gate", new Tile(3111, 9519, 0), defaultBounds));
			}
			break;
		case TALK_TO_BRACE:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				if (ctx.players.local().tile().compareTo(new Tile(3133, 3115, 0)) < 0 && !braceFlag) {
					ctx.movement.step(new Tile(3133, 3115, 0));
					braceFlag = true;
				}
				tasks.add(new TalkToGuideTask(ctx, "Brother Brace"));
			}

			//System.out.println(ctx.objects.select().name("Large door").poll().orientation());
			
			if (ctx.objects.select().name("Large door").nearest(new Tile(3129,3107,0)).poll().orientation() == 0) {
				changeCurrent(null);
				tasks.clear();
				ctx.movement.step(ctx.objects.peek());
				ctx.objects.poll().interact("Open");
			}

			break;
		case EQUI_BOW_ARROW:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			ctx.game.tab(Tab.INVENTORY);
			ctx.inventory.select().name("Shortbow").poll().interact("Wield");
			ctx.inventory.select().name("Bronze arrow").poll().interact("Wield");
			break;
		case SHOOT_RAT:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			
			ctx.game.tab(Tab.INVENTORY);
			if(!ctx.inventory.select().name("Shortbow").isEmpty()) {
				ctx.inventory.poll().click();
			} 
			
			Npc ratRange = ctx.npcs.select().name("Giant rat").nearest().poll();
			
			if (ratRange.inViewport()) {
				ratRange.interact("Attack");
				
				Condition.wait(new Callable<Boolean>() {

					@Override
					public Boolean call() throws Exception {
						return !ctx.players.local().inCombat();
					}

				});
				
			} else {
				ctx.movement.step(ratRange);
				ctx.camera.turnTo(ratRange);
			}
			break;
		case VISIT_POLL:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new VisitPollTask(ctx, "Poll booth", "Use"));
			}

			if (ctx.widgets.component(193, 2).visible()) {
				ctx.widgets.component(193, 2).click();
			}
			break;
		case GO_TO_FINANCE_DOOR:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				if (ctx.widgets.widget(345).valid()) {
					ctx.widgets.component(345, 1).component(3).click();
				}
				tasks.add(new OpenDoorTask(ctx, "Door", new Tile(3124, 3124, 0), doorToFinanceBounds));
			}
			break;
		case TALK_TO_FINANCE:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new TalkToGuideTask(ctx, "Financial Advisor"));
			}
			break;
		case GO_TO_FINANCE_EXIT:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OpenDoorTask(ctx, "Door", new Tile(3129, 3124, 0), doorExitFinanceBounds));
			}
			break;
		case WIN_ON_CHICKEN:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new CastWindTask(ctx));
			}
			break;
		case CLICK_IGNORE:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			if(ctx.chat.canContinue()) {
				ctx.chat.clickContinue();
			}
			ctx.game.tab(Tab.IGNORED_LIST);
			break;
		case USE_BANK:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new UseBankTask(ctx, "Bank booth", "Use"));
			}
			break;
		case CLICK_PRAYER:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			if(ctx.chat.canContinue()) {
				ctx.chat.clickContinue();
			}
			ctx.game.tab(Tab.PRAYER);
			break;
		case CLICK_FRIENDS:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			if(ctx.chat.canContinue()) {
				ctx.chat.clickContinue();
			}
			ctx.game.tab(Tab.FRIENDS_LIST);
			break;
		case OPEN_DOOR_LEAVE_BRACE:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new OpenDoorTask(ctx, "Door", new Tile(3122, 3103, 0), doorLeaveBraceBounds));
			}
			break;
		case CLICK_CONTINUE:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			
			if (ctx.chat.canContinue()) {
				ctx.chat.clickContinue();
			} else if(ctx.varpbits.varpbit(281) == 640) {
				ctx.widgets.component(11, 3).click();
			}
			break;
		case CLICK_MAGIC:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
			}
			ctx.game.tab(Tab.MAGIC);
			if (ctx.widgets.widget(231).valid()) {
				ctx.widgets.component(231, 2).click();
			}
			break;
		case TALK_TO_ADAM:
			if (currentState != pollState) {
				tasks.clear();
				changeCurrent(pollState);
				tasks.add(new TalkToGuideTask(ctx, "Adam"));
			}
			if(ctx.widgets.component(219, 0).component(2).visible()) {
				ctx.widgets.component(219, 0).component(2).click();
			}
			break;
		case FINISHED:
			System.out.println("Tutotorial Island completed");
			ctx.controller.stop();
			break;
		default:
			System.out.println("Default case reached in setState(), this is an error.");
			break;
		}
	}
	
	public void changeCurrent(ScriptState.State pollState) {
		currentState = pollState;
		System.out.println("CURRENT STATE: " + pollState );
	}

	@Override
	public void poll() {
		setState();
		executeTasks();
		clickCheck();
	}
	
	private void executeTasks() {
		for (Task<?> task : tasks) {
			if (task.activate()) {
				task.execute();
			}
		}
	}

	private void clickCheck() {
		// remove a "Click to continue" message block
		if (ctx.widgets.widget(162).component(33).visible()) {
			ctx.widgets.component(162, 33).click();
		}
	}

	@Override
	public void stop() {
		System.out.println("Script Stopped!");
	}

	@Override
	public void repaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		drawMouse(g);

		//for debugging
//		 for (Task<?> task : guideTasks) {
//			 if (task.draw()) {
//				 task.paint(g);
//			 }
//		 }
	}

	private void drawMouse(Graphics2D g) {
		g.setColor(Color.CYAN);
		int mouseY = ctx.input.getLocation().y;
		int mouseX = ctx.input.getLocation().x;
		g.drawLine(mouseX - 5, mouseY + 5, mouseX + 5, mouseY - 5);
		g.drawLine(mouseX + 5, mouseY + 5, mouseX - 5, mouseY - 5);
	}

}