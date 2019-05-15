package GameAssets;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundConstants {
	public static void menuClickSound() {
		try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("shortSounds//e.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e1) {
	        System.err.println(e1.getMessage());
	      }
	}
	public static void menuMouseOnButtonSound() {
		try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("shortSounds//f.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e1) {
	        System.err.println(e1.getMessage());
	      }
	}
	public static void gameMouseClickSound() {
		try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("shortSounds//g.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e1) {
	        System.err.println(e1.getMessage());
	      }
	}
	public static void gunReloadSound() {
		try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("shortSounds//gunReload.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e1) {
	        System.err.println(e1.getMessage());
	      }
	}
	public static void singleDiceSound() {
		try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("shortSounds//dice2.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e1) {
	        System.err.println(e1.getMessage());
	      }
	}
	public static void multiDiceSound() {
		try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("shortSounds//dice.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e1) {
	        System.err.println(e1.getMessage());
	      }
	}
	public static void getCardsSound() {
		try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("shortSounds//card2.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e1) {
	        System.err.println(e1.getMessage());
	      }
	}
	public static void activateCardsSound() {
		try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("shortSounds//card.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e1) {
	        System.err.println(e1.getMessage());
	      }
	}
	public static void notificationSound() {
		try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("shortSounds//notification.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e1) {
	        System.err.println(e1.getMessage());
	      }
	}
	public static void snapSound() {
		try {
	        Clip clip = AudioSystem.getClip();
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("shortSounds//snapSound.wav"));
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e1) {
	        System.err.println(e1.getMessage());
	      }
	}
}
