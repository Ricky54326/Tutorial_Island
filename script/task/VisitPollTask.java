package script.task;

import java.awt.Graphics;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class VisitPollTask extends Task<ClientContext> {

	private GameObject poll;
	private String name;
	private String task;

	public VisitPollTask(ClientContext ctx, String name, String task) {
		super(ctx);
		this.poll = null;
		this.name = name;
		this.task = task;
	}

	@Override
	public boolean activate() {
		return !ctx.objects.select().name(name).isEmpty() 
				&& !ctx.widgets.component(193, 2).visible() 
				&& !ctx.players.local().inMotion();
	}

	@Override
	public void execute() {

		poll = ctx.objects.nearest().poll();

		if (poll.inViewport()) {
						
			if(ctx.chat.canContinue()) {
				ctx.chat.clickContinue();
			}  else if (ctx.widgets.widget(12).valid()) {
				ctx.widgets.component(12, 3).component(3).click();
			} else {
				poll.interact(task);
			}
			
		} else {
			ctx.movement.step(poll);
			ctx.camera.turnTo(poll);
		}

	}

	@Override
	public boolean draw() {
		return poll != null;
	}

	@Override
	public void paint(Graphics g1) {
		poll.draw(g1);
	}

}