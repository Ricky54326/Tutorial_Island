package script.task;

import java.awt.Graphics;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class LightFireTask extends Task<ClientContext> {

	public LightFireTask(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return !ctx.inventory.select().name("Tinderbox").isEmpty() && !ctx.inventory.select().name("Logs").isEmpty();
	}

	@Override
	public boolean draw() {
		return false;
	}

	@Override
	public void paint(Graphics g1) {		
	}

	@Override
	public void execute() {
		Item log = ctx.inventory.poll();
		Item tinder = ctx.inventory.select().name("Tinderbox").poll();
		
		tinder.click();
        Condition.sleep(Random.nextInt(100, 300));
		log.click();
	}

}
