package script.task;

import java.awt.Graphics;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class OpenDoorTask extends Task<ClientContext> {

	private GameObject door;
	private String doorName;
	private Tile near;
	
	private int[] bounds;
		
	public OpenDoorTask(ClientContext ctx, String doorName, Tile near, int[] bounds) {
		super(ctx);
		this.door = null;
		this.doorName = doorName;
		this.near = near;
		this.bounds = bounds;
	}

	@Override
	public boolean activate() {
		return !ctx.objects.select().name(doorName).isEmpty() && !ctx.players.local().inMotion();
	}

	@Override
	public void execute() {

		door = ctx.objects.name(doorName).nearest(near).each(GameObject.doSetBounds(bounds)).poll();
		
		if(door.inViewport()) {
			
			if(doorName.equals("Ladder")) {
				door.click();
			} else {
				door.interact("Open");
			}
			
			//ctx.camera.angle(90);
		} else {
			
			ctx.movement.step(door);
			ctx.camera.turnTo(door);
			
		}
		
	}

	@Override
	public void paint(Graphics g1) {
		//System.out.println("DRAWING DOOR: " + door.centerPoint().x + " " + door.centerPoint().y + " " + door.width() + " " + door.height() );
		door.draw(g1);
	}

	@Override
	public boolean draw() {
		return door != null;
	}
	
}
