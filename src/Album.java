import java.util.ArrayList;

public class Album {

    private String albumName;
    private ArrayList<Song> songs = new ArrayList<Song>();

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public boolean addSongToAlbum(String name, double time){
        if(findSongInAlbum(name) == -1){
            songs.add(new Song(name, time));
            return true;
        }
        else{
            return false;
        }
    }

    public int findSongInAlbum(String name){
        for(int i=0; i<this.songs.size(); i++){
            if(this.songs.get(i).getTitle().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public Song findSong(String name){
        for(int i=0; i<this.songs.size(); i++){
            if(this.songs.get(i).getTitle().equals(name)){
                return songs.get(i);
            }
        }
        return null;
    }


}
