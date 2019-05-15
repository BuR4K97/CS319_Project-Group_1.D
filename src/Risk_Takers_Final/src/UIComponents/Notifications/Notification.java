package UIComponents.Notifications;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import UIComponents.Notifications.notificationString.Rect;



public class Notification {
	String string = "";
	notificationString vs;
	Timer doneTimer;
	boolean done = false;
	ArrayList<Rect> list = new ArrayList<Rect>();
	public Notification(String string) {
		this.string = string;
	}
	public void initialize(int x, int y, int border) {
		vs = new notificationString(x, y, border, string, "notification");
	}
	public void reset(int x, int y, int border) {
		vs = new notificationString(x, y, border, string, "notification", true);
	}
	public void show() {
		doneTimer = new Timer(NotificationArea.showDuration, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				done = true;
				doneTimer.stop();
			}
		});
		doneTimer.start();
	}
}
