import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

// A music object is made up 2 string; song name and artist
class Music {
	String song;
	String artist;

	public Music(String song, String artist) {
		this.song = song;
		this.artist = artist;
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	@Override
	public String toString() {
		return String.format("%-21s: %s", song, artist);
	}
}

// Each node will hold on to a song and the following song in the queue (the next node)
class Node {
	Music song;
	Node next;

	public Node(Music song) {
		this.song = song;
	}

	public Node() {
	}

	@Override
	public String toString() {
		if (song == null) {
			return "";
		}
		return song.toString();
	}
}

public class Spotify {
	Node current;
	Node last;

	/*
	 * TODO: You will take in a list of songs, in the form of arraylist, linkedlist,
	 * etc (Collection) and you will add that to the end of your queue
	 */
	public void addAll(Collection<Music> listOfSongs) {
		//System.out.println(this.toString());
		//System.out.println(this.last);
		int i = 0;
		for(Music song : listOfSongs) {
			if(current == null && last == null) {
				current = new Node(song); // sets pointer current to the first song
				last = current;
				i++;
			}
			Node temp = new Node(song);
			last.next = temp;
			last = last.next;
			last.next = current;
		}
		if(i == 1) {
			current = current.next;
		}
		
		
	}

	/*
	 * TODO: You will remove the song that is in the 1st position
	 */
	public void removeSong() {
		current = current.next;
		last.next = current;
	}

	/*
	 * TODO: You will add a song to the end of the queue
	 */
	public void addSong(Music song) {
		if(current == null) {
			current = new Node(song);
		}
		if(last == null) {
			last = new Node(song);
			current.next = last;
			last.next = current;
			current = current.next;
		}
		else {
		last.next = new Node(song);
		last = last.next;
		last.next = current;
		}
	}

	/*
	 * TODO: You will rotate the queue so that we are now listening to the next song
	 */
	public void nextSong() {
		Node temp = current;
		current = current.next;
		last = temp;
		last.next = current;
		
		
	}

	/*
	 * TODO: You will rotate the queue so that we are now listening to the previous
	 * song
	 */
	public void prevSong() {

		Node temp = current;
		int i = 1;
		while (temp.next != null && temp.next != last) {
			temp = temp.next;
			i++;
		}
		Node temp2 = last;
		last = temp;
		current = temp2;
		last.next = current;
	}

	/*
	 * TODO: You will tell us the song in the 1st position
	 */
	public Music currentSong() {
		if(current == null) {
			return null;
		}
		Music temp = current.song;
		return temp;
	}

	// use String.format( "%3s) %s\n", i, theCurrentNode );
	// i: the count starting at 1
	// theCurrentNode: current song node
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("=====[Current Queue]=====\n");
		if (current == null) {
			stringBuilder.append("no songs\n");
			return stringBuilder.toString();
		}
		Node temp = current;

		int i = 1;
		while (temp.next != null && temp.next != last.next) {
			stringBuilder.append(String.format("%3s) %s\n", i, temp));
			temp = temp.next;
			i++;
		}

		stringBuilder.append(String.format("%3s) %s\n", i, temp));

		return stringBuilder.toString();

	}

	public static void main(String[] args) {
		/*// Example 1
		ArrayList<Music> songs = new ArrayList<>();
		songs.add(new Music("Happy birthday", "shawn"));
		songs.add(new Music("Space Cadet", "Metro Boomin"));
		songs.add(new Music("100", "The Game"));
		Spotify spotify = new Spotify();
		spotify.addAll(songs);
		//////////////
		System.out.println(spotify.toString());
		//////////////
		String ans = "=====[Current Queue]=====\n" + "  1) Happy birthday       : shawn\n"
				+ "  2) Space Cadet          : Metro Boomin\n" + "  3) 100                  : The Game\n";

		String expectedAnswer = ans.replaceAll("\\s", "");
		String actualAnswer = spotify.toString().replaceAll("\\s", "");

		if (expectedAnswer.equals(actualAnswer)) {
			System.out.println("Example 1 working");
		} else {
			System.out.println("Issue in example 1");
		}*/
		
		
		/*ArrayList<Music> songs = new ArrayList<>();
		songs.add(new Music("Happy birthday", "shawn"));
		songs.add(new Music("Space Cadet", "Metro Boomin"));
		songs.add(new Music("100", "The Game"));
		songs.add(new Music("L@D", "A@AP Rocky"));
		songs.add(new Music("Hot Man", "Bobby Shmurda"));
		songs.add(new Music("Men in Paris", "Kanye East and Jay-Y"));
		songs.add(new Music("Im Sad", "Aubrey"));
		

		String inputString = "[new Music(\"Happy birthday\", \"shawn\"),"
				+ "new Music(\"Space Cadet\", \"Metro Boomin\"), new Music(\"100\", \"The Game\"), "
				+ "new Music(\"L@D\", \"A@AP Rocky\"), new Music(\"Hot Man\", \"Bobby Shmurda\"), "
				+ "new Music(\"Men in Paris\", \"Kanye East and Jay-Y\"), new Music(\"Im Sad\", \"Aubrey\"), "
				+ "new Music(\"Happy birthday\", \"shawn\")]";

		Spotify spotify = new Spotify();

		spotify.addAll(songs);
		System.out.println(spotify.toString());
		spotify.prevSong();
		System.out.println(spotify.toString());
		/*for (int i = 0; i < 3; i++) {
			spotify.prevSong();
		}*/
		
		ArrayList<Music> songs = new ArrayList<>();
		songs.add(new Music("Happy birthday", "shawn"));
		songs.add(new Music("Space Cadet", "Metro Boomin"));
		songs.add(new Music("100", "The Game"));
		songs.add(new Music("L@D", "A@AP Rocky"));
		songs.add(new Music("Hot Man", "Bobby Shmurda"));
		songs.add(new Music("Men in Paris", "Kanye East and Jay-Y"));
		songs.add(new Music("Im Sad", "Aubrey "));
		
		String inputString = "[new Music(\"Happy birthday\", \"shawn\"), "
				+ "new Music(\"Space Cadet\", \"Metro Boomin\"), new Music(\"100\", \"The Game\"), "
				+ "new Music(\"L@D\", \"A@AP Rocky\"), new Music(\"Hot Man\", \"Bobby Shmurda\"), "
				+ "new Music(\"Men in Paris\", \"Kanye East and Jay-Y\"), new Music(\"Im Sad\", \"Aubrey \")]";

		Spotify spotify = new Spotify();
		spotify.addAll(songs);
		System.out.println(spotify.toString());
		System.out.println(spotify.currentSong().toString());
		
	}
}
