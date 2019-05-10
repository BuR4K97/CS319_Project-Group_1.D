package SoundComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class SoundHandler {
	private static boolean played;
	private static File musicPath;
	private static AudioInputStream audio;
	private static Clip clip;
	private static long pauseTimePosition;
	
	
	public SoundHandler() {
		played = false;
	}
	
	public static void playMusic(String musicLoc) {
		try {
			musicPath = new File(musicLoc);
			if(!played) {
				if (musicPath.exists()) {
					audio = AudioSystem.getAudioInputStream(musicPath);
					clip = AudioSystem.getClip();
					clip.open(audio);
					clip.start();
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					played = true;
				} else {
					System.out.println("Music file not found");
				}
			}
			else{
				clip.getMicrosecondPosition();
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void pauseMusic() {
		pauseTimePosition = clip.getMicrosecondPosition();
		clip.stop();
	}
	
	public static void stopMusic() {
		clip.stop();
		played = false;
	} 

	public static boolean getPlayed() {
		return played;
	}

	public static void setPlayed() {
		played = false;
	}
	
	public static Clip getClip() {
		return clip;
	}
	
}
