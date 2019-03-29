import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static ArrayList<Album> albums = new ArrayList<Album>();

    private static LinkedList<Song> playlistSongs = new LinkedList<Song>();

    public static void main(String[] args) {

        boolean quit = false;

        while(!quit){

            System.out.println("Enter action: ");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action){

                case 0:
                    System.out.println("Print options: ");
                    printOptions();
                    break;
                case 1:
                    addAlbum();
                    break;
                case 2:
                    addSongAlbum();
                    break;
                case 3:
                    addSongToPlaylist();
                    break;
                case 4:
                    listSongs(playlistSongs);
                    break;
                case 5:
                    move(playlistSongs);
                    break;
                case 6:
                    System.out.println("Shouting down the system...");
                    quit = true;
                    break;

            }
        }
    }

    private static void printOptions(){
        System.out.println("Options: ");
        System.out.println("0 -> print options");
        System.out.println("1 -> add album");
        System.out.println("2 -> add song to album");
        System.out.println("3 -> add song to playlist");
        System.out.println("4 -> list songs on playlist");
        System.out.println("5 -> move to different song");
        System.out.println("6 -> shout down");

    }

    private static void addAlbum(){
        System.out.println("Enter name of album: ");
        String name = scanner.nextLine();

        if(findAlbum(name) == -1){
            System.out.println("Creating new album: " + name);
            albums.add(new Album(name));
        }
        else{
            return;
        }
    }

    private static int findAlbum(String name){
        for(int i=0; i<albums.size(); i++){
            if(albums.get(i).getAlbumName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    private static void addSongAlbum(){

        System.out.println("Enter name of album: ");
        String albumName = scanner.nextLine();

        int position = findAlbum(albumName);

        if(position >= 0 ){
            System.out.println("Enter name of song: ");
            String name = scanner.nextLine();
            System.out.println("Enter duration of song: ");
            double duration = scanner.nextDouble();

            Album existingAlbum = albums.get(position);

            existingAlbum.addSongToAlbum(name, duration);
            System.out.println("Adding to album: " + existingAlbum.getAlbumName() + " song: " + name);
        }
        else{
            return;
        }

    }

    private static void addSongToPlaylist(){

        System.out.println("Enter name of the song: ");
        String name = scanner.nextLine();

        for(int i=0; i<albums.size(); i++){

            Album musicAlbum = albums.get(i);

            Song searchedSong = musicAlbum.findSong(name);

            if(searchedSong != null){
                playlistSongs.add(searchedSong);
                System.out.println("Adding song: " + searchedSong.getTitle() + " to playlist");
            }
            else{
                return;
            }
        }

    }

    private static void listSongs(LinkedList<Song> playlistSongs){
        System.out.println("Your playlist: ");
        Iterator<Song> i = playlistSongs.iterator();
        while(i.hasNext()){
            System.out.println("Title: " + i.next().getTitle());
        }

    }

    private static void move(LinkedList songs){

        boolean quit = false;
        boolean goingForward = true;

        ListIterator<Song> songIterator = songs.listIterator();

        if(songs.isEmpty()){
            System.out.println("Your playlist is empty");
        }
        else{
            System.out.println("Currently playing: " + songIterator.next().getTitle());
            printOptionsForSkipping();
        }

        while(!quit){

            System.out.println("Enter moving action");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action) {
                case 0:
                    System.out.println("Quiting moving section");
                    quit = true;
                    break;
                case 1:
                    if(!goingForward){
                        if(songIterator.hasNext()){
                            songIterator.next();
                        }
                        goingForward = true;
                    }
                    if(songIterator.hasNext()){
                        System.out.println("Skipping forward to: " + songIterator.next().getTitle());
                    }
                    else{
                        System.out.println("You reached to the end of the playlist");
                        goingForward = false;
                    }
                    break;
                case 2:
                    if(goingForward){
                        if(songIterator.hasPrevious()){
                            songIterator.previous();
                        }
                        goingForward = false;
                    }
                    if(songIterator.hasPrevious()){
                        System.out.println("Skipping backwards to: " + songIterator.previous().getTitle());
                    }
                    else{
                        System.out.println("You reached beginning of your list");
                        goingForward = true;
                    }
                    break;
                case 3:
                    if(songIterator.hasPrevious()){
                        songIterator.previous();
                    }
                    else{
                        System.out.println("You cannot do that");
                        return;
                    }
                    if(songIterator.hasNext()){
                        System.out.println("Replaying song: " + songIterator.next().getTitle());
                    }
                    else{
                        System.out.println("You cannot do that");
                        return;
                    }
                    break;
                case 4:
                    System.out.println("Currently playing: " + songIterator.next().getTitle());

                    if(songIterator.hasPrevious()){
                        songIterator.previous();
                    }
                    else{
                        System.out.println("You cannot do that");
                        return;
                    }
                    if(songIterator.hasNext()){
                        System.out.println("Deleting song: ");
                        songs.remove(songIterator.next());
                    }
                    else{
                        System.out.println("You cannot do that");
                        return;
                    }

                    break;
            }
        }

    }

    private static void printOptionsForSkipping(){
        System.out.println("Options for playing different songs: ");
        System.out.println("0 -> shout down");
        System.out.println("1 -> skip song");
        System.out.println("2 -> play previous song");
        System.out.println("3 -> replay the song");
        System.out.println("4 -> remove song from playlist");
    }

}









