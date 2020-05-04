package MusicalProject;

import java.util.Scanner;
import javax.sound.midi.*;
import java.io.*;
import java.nio.ByteBuffer;

public class MiniMiniMusicApp {
	
	public static void main(String[] args) {
		MiniMiniMusicApp mini = new MiniMiniMusicApp();
		Scanner sc = new Scanner(System.in);
		File file = new File("C:\\Users\\ACER\\Desktop\\Other\\csgo.txt");
		ByteBuffer bb = ByteBuffer.allocate(128);
		
		while (true) {
			String instrument = sc.nextLine();
			String note = sc.nextLine();
			if (!instrument.equals(null) & !note.equals(null)) {
				mini.play(instrument.charAt(0), note.charAt(0));
				mini.save(instrument.charAt(0), note.charAt(0), file, bb);
			}
		}
	}
	
	public void play(int instrument, int note) {
		try {
			Sequencer player = MidiSystem.getSequencer(); // getting the synthesizer and opening it
			player.open();
			Sequence seq = new Sequence(Sequence.PPQ, 4); // Setting the type of synchronization with a resolution of the fourth note
			Track track = seq.createTrack(); //creating an instance of the track class
			
			MidiEvent event = null;
			
			ShortMessage first = new ShortMessage(); //creating message and setting the values of the selected instrument
			first.setMessage(144, 1, instrument, 100);
			MidiEvent changeInstrument = new MidiEvent(first, 1);
			track.add(changeInstrument); // adding this message to track
			
			ShortMessage a = new ShortMessage(); // doing the same with the note
			a.setMessage(176, 1, note, 0);
			MidiEvent noteOn = new MidiEvent(a, 1);
			track.add(noteOn);
			
			ShortMessage b = new ShortMessage();
			b.setMessage(128, 1, note, 100);
			MidiEvent noteOff = new MidiEvent(b, 16);
			track.add(noteOff);
			
			player.setSequence(seq); //Setting this sequence
			player.start();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void save(char instrument, char note, File file, ByteBuffer bb) {
		try (FileOutputStream fos = new FileOutputStream(file)){
			bb.putChar(instrument);
			bb.putChar(note);
			fos.write(bb.array());
			fos.flush();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void reproduce(File file) {
		try (FileInputStream fis = new FileInputStream(file)){
			char instrument = 0;
			char note = 0;
			int k = 0;
			int size = 0;
			ByteBuffer bb = ByteBuffer.allocate(2048);
			while ((k = fis.read()) != -1) {
				bb.put((byte)k);
				size++;
			}
			bb.slice();
			bb.rewind();
			int sounds = size/4;
			System.out.println(sounds);
			while (sounds > 0) {
				instrument = bb.getChar();
				note = bb.getChar();
				play(instrument, note);
				sounds -= 2;
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
