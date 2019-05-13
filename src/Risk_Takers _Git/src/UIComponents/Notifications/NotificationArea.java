package UIComponents.Notifications;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import GameAssets.GameConstants;
import GameAssets.SoundConstants;

public class NotificationArea {
	int xTopLeft = 20;
	int yTopLeft = 100;
	public static int border = 3;	// 3 is ideal		// length of border of visual strings
	public static int distanceBetwNotifications = 5;   // distance between notifications
	int showedNotificationNumber = 3;					// showed notification numbers
	static int showDuration = 3000;						// duration of one notification
	public static int startAnimationSpeed = 13;			// loading speed to screen of one notification
	public static int opacityChanger = 8;				// opacity changing of one disappearing notification
	public static double leftTorightMovement = 8;		// speed to left of one disappearing notification
	ArrayList<Notification> notificationList = new ArrayList<Notification>();
	ArrayList<Notification> showedNotificationList = new ArrayList<Notification>();
	Box disappearNotification = new Box(xTopLeft, yTopLeft, ">");
	Timer initializeNotifications;
	public static int notificationHeight = border * 5;
	public NotificationArea() {
		initializeNotifications = new Timer(16, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showedNotificationList.size() < showedNotificationNumber && notificationList.size() > 0) {
					showedNotificationList.add(new Notification(notificationList.get(0).string));
					SoundConstants.notificationSound();
					notificationList.remove(0);
					if(showedNotificationList.size() == 1) {
						showedNotificationList.get(0).initialize(
								xTopLeft,
								yTopLeft + notificationHeight + distanceBetwNotifications
								, border);
					showedNotificationList.get(0).show();
					}
					else {
						showedNotificationList.get(showedNotificationList.size()-1).initialize(
								showedNotificationList.get(showedNotificationList.size()-2).vs.x
								, showedNotificationList.get(showedNotificationList.size()-2).vs.y + notificationHeight + distanceBetwNotifications,
								border);
						showedNotificationList.get(showedNotificationList.size()-1).show();
					}
				}
				if(showedNotificationList.size() > 0) {
					if(showedNotificationList.get(0).done && disappearNotification != null && disappearNotification.getDone()) {
						disappearNotification = new Box(xTopLeft, yTopLeft, showedNotificationList.get(0).string);
						showedNotificationList.remove(0);
						for(int j = 0; j < showedNotificationList.size(); j++) {
							showedNotificationList.get(j).reset(xTopLeft, yTopLeft + (notificationHeight + distanceBetwNotifications)*(j+1), border);
						}
					}	
				}
			}
		});
		initializeNotifications.start();
	}
	public void stopTimer() {
		initializeNotifications.stop();
		for(int i = 0; i < showedNotificationList.size(); i++) {
			showedNotificationList.get(i).doneTimer.stop();
		}
	}
	public void startTimer() {
		initializeNotifications.start();
		for(int i = 0; i < showedNotificationList.size(); i++) {
			showedNotificationList.get(i).doneTimer.start();
		}
	}
	public void addNotification(String s) {
		notificationList.add(new Notification(s));
	}
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		for(int i = 0; i < showedNotificationList.size(); i++) {
			if(showedNotificationList.get(i).vs != null)
				showedNotificationList.get(i).vs.paint(g);
		}
		if(disappearNotification != null)
			disappearNotification.paint(g);
	}
}
