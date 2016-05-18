package script.task;

import java.awt.Graphics;
import java.util.concurrent.Callable;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class SmeltDaggerTask extends Task<ClientContext> {

	public SmeltDaggerTask(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return !ctx.inventory.select().name("Bronze bar").isEmpty() 
				&& !ctx.inventory.select().name("Hammer").isEmpty() 
				&& !ctx.objects.select().name("Anvil").isEmpty();
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
		GameObject anvil = ctx.objects.nearest().poll();

		if(anvil.inViewport()) {
			anvil.interact("Smith");
	        
	        Condition.wait(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
			        return ctx.widgets.widget(312).valid();
				}
	        
	        });
	        
	        if(ctx.widgets.component(312, 2).visible()) {
		        ctx.widgets.component(312, 2).component(2).interact("Smith 1");
		        
		        Condition.wait(new Callable<Boolean>() {
	
					@Override
					public Boolean call() throws Exception {
				        return ctx.players.local().animation() == -1;
					}
		        
		        });
	        }
	        
		} else {
			ctx.movement.step(anvil);
			ctx.camera.turnTo(anvil);
		}
	}

}
