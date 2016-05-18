package script.task;

import java.awt.Graphics;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class MakeDoughTask extends Task<ClientContext> {

	public MakeDoughTask(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return !ctx.inventory.select().name("Pot of flour").isEmpty() && !ctx.inventory.select().name("Bucket of water").isEmpty();
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
		Item water = ctx.inventory.poll();
		Item flour = ctx.inventory.select().name("Pot of flour").poll();
		
		water.click();
        Condition.sleep(Random.nextInt(100, 300));
		flour.click();
	}

}
